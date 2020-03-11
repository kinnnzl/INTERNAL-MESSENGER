package com.example.abi_spare.oainternalmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReturnToMenuActivity extends AppCompatActivity {

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.startCamera();
        zXingScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {

                zXingScannerView.stopCamera();
                String resultString = result.getText().toString();
                Toast.makeText(ReturnToMenuActivity.this, resultString, Toast.LENGTH_LONG).show();
                Log.d("12MarchV1", "QR code ==> " + resultString);
                finish();
            }
        });
    }
}
