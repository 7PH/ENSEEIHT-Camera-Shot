package fr.enseeiht.braymond.enseeiht_camera_shot;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ENSEEIHT-Camera-Shot";

    protected CameraPreview cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraPreview = findViewById(R.id.cameraPreview);

        CameraHelper.askForCamera(this);
        Camera camera = CameraHelper.getFrontFacingCamera();

        //Camera camera = Camera.open();
        if (camera == null) {
            // fallback?
            Log.e(MainActivity.TAG, "Unable to retrieve the camera");
            return;
        }

        Log.e(MainActivity.TAG, "Gonna load the camera into the preview");
        cameraPreview.setCamera(camera);
    }
}
