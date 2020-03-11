package com.example.abi_spare.oainternalmessenger;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;

public class TestNewQRScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;
    private String result, txtDocID, txtSendBy, txtSendTo;
    private int id = 1;
    List<INActivity.IN> inList;
    private boolean CheckTayFirst = false;
    private boolean CheckTayIn = false;
    private boolean CheckTayOut = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_new_qrscan);
        //setContentView(mScannerView);

        inList = new ArrayList<>();
        mScannerView = new ZXingScannerView(this);
        LinearLayout s1 = findViewById(R.id.LiScanQR);
        s1.addView(mScannerView);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                //Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }

        //ForTest
        Button btnSubmitQR = findViewById(R.id.btnSubmitQR);
        btnSubmitQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestNewQRScan.this, SummaryActivity.class);
                startActivity(intent);
                finish();
            }
        });


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
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(TestNewQRScan.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
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
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }

    public class Header{
        private String Type, Value;
        private List<String> Detail;

        public Header(){
            super();
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getType() {
            return Type;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public String getValue() {
            return Value;
        }

        public void setDetail(List<String> Detail) {
            this.Detail = Detail;
        }

        public List<String> getDetail() {
            return Detail;
        }

        public void reset(){
            Type = "";
            Value = "";
        }
    }


    List<Header> Header = new ArrayList<>();

    Header hd = new Header();
    List<String> de = new ArrayList<>();

    @Override
    public void handleResult(Result rawResult) {
        result = rawResult.getText();
        OKDialog("Scan Result", result);

        // Tokenizer "."
        StringTokenizer st1 = new StringTokenizer(result, ".");
        for (int i = 1; st1.hasMoreTokens(); i++) {
            switch (i) {
                case 1: txtDocID = st1.nextToken();
                case 2: txtSendBy = st1.nextToken();
                case 3: txtSendTo = st1.nextToken();
            }
        }

        // Add data from QRCode to inList
        inList.add(
                new INActivity.IN(
                        id,
                        txtDocID,
                        txtSendBy,
                        txtSendTo));
        id++;

        // Send inList to INActivity
        INActivity.setArrayList(inList);

        //final String[] arr = result.split("-");

/*        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Scan สำเร็จ");
        dlg.setMessage(result);
        final AlertDialog alert1 = dlg.create();
        alert1.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        alert1.dismiss();
                        mScannerView.resumeCameraPreview(TestNewQRScan.this);
                    }
                },
                1500);*/


//        if (!CheckTayOut && !CheckTayIn)
//        {
//            if (!CheckTayFirst){
//                if (arr[1].equalsIgnoreCase("OUT")){
//                    OKAndSubmnitDialog(result, "OUT");
//                    hd.setType("OUT");
//                    hd.setValue(result);
//                    Header.add(hd);
//                }else if(arr[1].equalsIgnoreCase("IN")){
//                    OKAndSubmnitDialog(result, "IN");
//                    hd.setType("IN");
//                    hd.setValue(result);
//                    Header.add(hd);
//                }else{
//                    OKDialog("แจ้งเตือน", "กรุณา Scan เทเอกสารก่อน");
//                }
//            }else{
//                if (arr[1].equalsIgnoreCase("OUT")){
//                    OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                }else if(arr[1].equalsIgnoreCase("IN")){
//                    OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                }else{
//                    OKAndSubmnitDialog(result, "");
//                    de.add(result);
//                }
//            }
//        }
//        else
//        {
//            if (CheckTayOut && !CheckTayIn)
//            {
//                if (!CheckTayFirst){
//                    if (arr[1].equalsIgnoreCase("OUT")){
//                        OKDialog("แจ้งเตือน", "ไม่สามารถ Scan เทเอกสารขาออกซ้ำได้");
//                    }else if(arr[1].equalsIgnoreCase("IN")){
//                        OKAndSubmnitDialog(result, "IN");
//                        hd.setType("IN");
//                        hd.setValue(result);
//                        Header.add(hd);
//                    }else{
//                        OKDialog("แจ้งเตือน", "กรุณา Scan เทเอกสารก่อน");
//                    }
//                }else{
//                    if (arr[1].equalsIgnoreCase("OUT")){
//                        OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                    }else if(arr[1].equalsIgnoreCase("IN")){
//                        OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                    }else{
//                        OKAndSubmnitDialog(result, "");
//                        de.add(result);
//                    }
//                }
//            }
//            else if(!CheckTayOut && CheckTayIn)
//            {
//                if (!CheckTayFirst){
//                    if (arr[1].equalsIgnoreCase("OUT")){
//                        OKAndSubmnitDialog(result, "OUT");
//                        hd.setType("OUT");
//                        hd.setValue(result);
//                        Header.add(hd);
//                    }else if(arr[1].equalsIgnoreCase("IN")){
//                        OKDialog("แจ้งเตือน", "ไม่สามารถ Scan เทเอกสารขาออกซ้ำได้");
//
//                    }else{
//                        OKDialog("แจ้งเตือน", "กรุณา Scan เทเอกสารก่อน");
//                    }
//                }else{
//                    if (arr[1].equalsIgnoreCase("OUT")){
//                        OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                    }else if(arr[1].equalsIgnoreCase("IN")){
//                        OKDialog("แจ้งเตือน", "กรุณา Scan ซองเอกสาร");
//                    }else{
//                        OKAndSubmnitDialog(result, "");
//                        de.add(result);
//                    }
//                }
//            }
//        }

        Button btnSubmitQR = findViewById(R.id.btnSubmitQR);
        btnSubmitQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ForTest
                Intent intent = new Intent(TestNewQRScan.this, SummaryActivity.class);
                startActivity(intent);

//                hd.setDetail(de);
//                Header.add(hd);
//
//                hd.reset();
//                de = new ArrayList<>();
//
//                CheckTayFirst = false;
//
//                if (arr[1].equalsIgnoreCase("OUT") && CheckTayOut == false){
//                    CheckTayOut = true;
//                }else if(arr[1].equalsIgnoreCase("IN") && CheckTayIn == false){
//                    CheckTayIn = true;
//                }
//
//                if (CheckTayOut && CheckTayIn){
//                    //finish();
//                    Intent intent = new Intent(TestNewQRScan.this, SummaryActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    mScannerView.resumeCameraPreview(TestNewQRScan.this);
//                }
            }

        });

    }

    public void OKAndSubmnitDialog(String result, final String TayType){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
/*        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TayType.equalsIgnoreCase("OUT")){
                    CheckTayFirst = true;
                    CheckTayOut = true;
                }else if(TayType.equalsIgnoreCase("IN")){
                    CheckTayFirst = true;
                    CheckTayIn = true;
                }
                mScannerView.resumeCameraPreview(TestNewQRScan.this);
            }
        });
        builder.setNeutralButton("จบการ Scan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CheckTayFirst = false;

                if (TayType.equalsIgnoreCase("OUT") && CheckTayOut == false){
                    CheckTayOut = true;
                }else if(TayType.equalsIgnoreCase("IN") && CheckTayIn == false){
                    CheckTayIn = true;
                }

                if (CheckTayOut && CheckTayIn){
                    finish();
                }else{
                    mScannerView.resumeCameraPreview(TestNewQRScan.this);
                }
            }
        });*/
        builder.setMessage(result);
        final AlertDialog alert1 = builder.create();
        alert1.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (TayType.equalsIgnoreCase("OUT")){
                            CheckTayFirst = true;
                            CheckTayOut = true;
                        }else if(TayType.equalsIgnoreCase("IN")){
                            CheckTayFirst = true;
                            CheckTayIn = true;
                        }

                        alert1.dismiss();
                        mScannerView.resumeCameraPreview(TestNewQRScan.this);
                    }
                },
                1500);
    }

    public void OKDialog(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setMessage(Message);

/*        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScannerView.resumeCameraPreview(TestNewQRScan.this);
            }
        });*/
        final AlertDialog alert1 = builder.create();
        alert1.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        alert1.dismiss();
                        mScannerView.resumeCameraPreview(TestNewQRScan.this);
                    }
                },
                3000);
    }
}
