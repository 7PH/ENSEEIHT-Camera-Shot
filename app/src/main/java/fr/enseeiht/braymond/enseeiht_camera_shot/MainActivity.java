package fr.enseeiht.braymond.enseeiht_camera_shot;

import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Camera.PictureCallback {

    public static final String TAG = "ENSEEIHT-Camera-Shot";

    private Camera camera;

    private ImageView imageView;

    private CameraPreview cameraPreview;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        cameraPreview = findViewById(R.id.cameraPreview);
        button = findViewById(R.id.button);

        /* Handle camera */
        CameraHelper.askForCamera(this);
        camera = CameraHelper.getFrontFacingCamera();
        // Camera camera = Camera.open();
        if (camera == null) {
            // fallback?
            Log.e(MainActivity.TAG, "Unable to retrieve the camera");
            return;
        }
        cameraPreview.setCamera(camera);

        /* Handle button click */
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        camera.stopPreview(); // not necessary because 'takePicture' stops preview
        camera.takePicture(null, null, this);
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        camera.startPreview();
    }
}
