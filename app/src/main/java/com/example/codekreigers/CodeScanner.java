package com.example.codekreigers;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class CodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;
    private static CodeScanner instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;


        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                //Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();

            } else {
                requestPermission();
            }
        }
    }


    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        //Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                Toast.makeText(CodeScanner.this,"You need to allow access to the permission",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(mScannerView == null) {
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void handleResult(Result rawResult) {

        final String result = rawResult.getText();
        Log.d("QRCodeScanner", result);

        SharedPreferences getLocation = CodeScanner.scannerInstance().getSharedPreferences("getLocation",Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = getLocation.edit();
        myEditor.putString("location",result);
        myEditor.apply();

        SharedPreferences cameraOPenedOnce = getSharedPreferences("cameraOpened",MODE_PRIVATE);
        SharedPreferences.Editor openEditor = cameraOPenedOnce.edit();
        openEditor.putBoolean("openedCamera",true);
        openEditor.apply();


        mScannerView.stopCamera();
        startActivity(new Intent(CodeScanner.this, StartGame.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

    public static CodeScanner scannerInstance(){
        return instance;
    }
}
