package fr.enseeiht.braymond.enseeiht_camera_shot;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

public class CameraHelper {
    private CameraHelper() { }

    /** Tells if the camera is available on this device
     * @param context
     * @return Weather the camera is available
     */
    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /** Get the Camera instance
     * @return Camera instance
     */
    public static Camera getInstance(int id) {
        try {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(id, info);
            Log.e(MainActivity.TAG, "trying to open camera " + id + " facing " + info.facing);
            return Camera.open(id);
        } catch (Exception e) {
            Log.e(MainActivity.TAG, "well, that did not worked");
            return null;
        }
    }

    /** Get the first found camera on the device
     * @return Camera instance
     */
    public static Camera getInstance() {
        return getInstance(0);
    }

    /** Get the id of the first found camera facing 'facing'
     * @param facing Expected side of the camera
     * @return The id of the camera or '-1' if not found
     */
    public static int getFacingCameraId(int facing) {
        for (int i = 0; i < Camera.getNumberOfCameras(); ++ i) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == facing)
                return i;
        }
        return -1;
    }

    /** Return the camera instance of the first back facing camera found. Null if not found.
     * @return Camera instance
     */
    public static Camera getBackFacingCameraId() {
        int id = getFacingCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
        return id == -1 ? null : getInstance(id);
    }

    /** Return the camera instance of the first back facing camera found. Null if not found.
     * @return Camera instance
     */
    public static Camera getFrontFacingCameraId() {
        int id = getFacingCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
        return id == -1 ? null : getInstance(id);
    }
}
