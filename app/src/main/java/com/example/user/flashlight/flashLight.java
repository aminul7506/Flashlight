package com.example.user.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.security.Policy;

public class flashLight extends AppCompatActivity {
    Button button1;
    private  Camera camera = null;
    private boolean isFlashOn;
    private boolean hasFlash;
    Parameters params;
    MediaPlayer mp;
    int i = 0;
    private String on = "TURN ON",off = "TURN OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isFlashOn = false;
        button1 = (Button) findViewById(R.id.button);
        button1.setText(on);
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        getCamera();
        if (!hasFlash) {
            // device doesn't support flash
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(flashLight.this)
                    .create();
            alert.setTitle("Error!");
            alert.setMessage("Sorry, your device doesn't support flash light!");
            alert.setButton("EXIT", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                }
            });
            alert.show();
            return;
        }


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                //if(button1.getText().equals(on)) {
               /* if (i % 2 == 0) {
                    if (!isFlashOn) {
                        try {

                            //playSound();

                            params = camera.getParameters();
                            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                            camera.setParameters(params);
                            camera.startPreview();
                            isFlashOn = true;
                            button1.setText(off);
                            i++;



                        } catch (RuntimeException e) {

                        }
                    }
                }
                // else if(button1.getText().equals(off)) {
                else if (i % 2 == 1) {
                    if (isFlashOn) {
                        // play sound
                        // playSound();

                        params = camera.getParameters();
                        params.setFlashMode(Parameters.FLASH_MODE_OFF);
                        camera.setParameters(params);
                        camera.stopPreview();
                        isFlashOn = false;
                        camera = null;
                        params = null;
                        i++;
                        button1.setText(on);
                    }
                }*/
                if (isFlashOn) {
                    // turn off flash
                    turnOffFlash();
                } else {
                    // turn on flash
                    turnOnFlash();
                }
            }
        });
    }

        @Override
        public void onBackPressed() {
            button1.setText(on);
            if (isFlashOn) {
                if (camera == null || params == null) {
                    System.exit(0);
                }
                // play sound
                // playSound();

                params = camera.getParameters();
                params.setFlashMode(Parameters.FLASH_MODE_OFF);
                camera.setParameters(params);
                camera.stopPreview();
                isFlashOn = false;
                camera = null;
                params = null;
                button1.setText(on);
                System.exit(0);
            }
            else System.exit(0);



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
           // playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

            // changing button/switch image
            //toggleButtonImage();
            button1.setText(off);
        }

    }


    // Turning Off flash
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
            //playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
            button1.setText(on);

            // changing button/switch image
            //toggleButtonImage();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        button1.setText(on);
    }

    @Override
    protected void onPause() {
        super.onPause();
        button1.setText(on);

        // on pause turn off the flash
        //turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        button1.setText(on);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button1.setText(on);

        // on resume turn on the flash
        //if(hasFlash)
          //  turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        getCamera();
        button1.setText(on);
    }

    @Override
    protected void onStop() {
        super.onStop();
        button1.setText(on);

        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error. ", e.getMessage());
            }
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_light, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
