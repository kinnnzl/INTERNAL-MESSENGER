package com.example.abi_spare.oainternalmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn;

//    private String MessageID;
//    private String TayType;
//    private String TayCode;
    private DocItem Doc;
    private List<DocItem> oDoc;

    private InsertDataModel Model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtUserID = findViewById(R.id.txtUserID);
        final EditText txtPassword = findViewById(R.id.txtPassword);
        btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                boolean DevMode = true;
//                String AppCde = "PETTYCASH3";
//                String UserID = txtUserID.getText().toString().trim();
//                String Password = txtPassword.getText().toString().trim();
//                String DBServer = "10.254.15.11";
//
//
//                if (UserID.isEmpty()){
//                    txtUserID.setError("กรุณาใส่รหัสพนักงาน");
//                    txtUserID.requestFocus();
//                    return;
//                }
//
//                if(Password.isEmpty()){
//                    txtPassword.setError("กรุณาใส่รหัสผ่าน");
//                    txtPassword.requestFocus();
//                    return;
//                }

//                MessageID = "005070";
//                TayType = "OUT";
//                TayCode = "CDG/IAD/ABI-OUT-01";
//
                Doc = new DocItem();
                Doc.setDocCode("GT//NOS-1-000155-129");
                oDoc = new ArrayList<>();
                oDoc.add(Doc);
//                int a = oDoc.size();
//                for (int i = 0; i < a; i++)
//                {
//                    Log.d("CountArray", oDoc.get(i).toString());
//                }

//                JSONObject Header = new JSONObject();
//                JSONObject Detail = new JSONObject();
//
//                JSONArray oDetail = new JSONArray();
//
//                try {
//                    Header.put("MessengerID", "005070");
//                    Header.put("TayType", "OUT");
//                    Header.put("TayCode", "CDG/IAD/ABI-OUT-01");
//                    Detail.put("DocCode", "TS/TS/SS-1-000010-73");
//                    oDetail.put(Detail);
//                    Header.put("Doc", oDetail);
//
//
//                }catch (Exception e){
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//                Log.d("Output", Header.toString());

                Model = new InsertDataModel();
                Model.setMessengerID("005070");
                Model.setTayType("IN");
                Model.setTayCode("CDG/IAD/ABI-IN-01");
                Model.setDoc(oDoc);

//                String a = "";

                Call<ResposeInsert> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .VerifyUserLogin(Model);
                Log.d("Output", oDoc.toString());

                call.enqueue(new Callback<ResposeInsert>() {

                    @Override
                    public void onResponse(Call<ResposeInsert> call, Response<ResposeInsert> response) {


                        String a = "";
                        Log.d("Output", response.toString());


                       boolean Status = response.body().isStatus();
                        String Error = response.body().getMessage();
                        if (Status){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }else {
                            Log.d("CountArray", Error);
                            Toast.makeText(MainActivity.this, Error, Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResposeInsert> call, Throwable t) {
                        Log.d("Output", t.getMessage());
                        Toast.makeText(MainActivity.this, "jajajajajajaja", Toast.LENGTH_LONG).show();
                    }
                });

/*                Call<VerifyUserLoginModel> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .VerifyUserLogin(DevMode, AppCde, UserID, Password, DBServer);


                call.enqueue(new Callback<VerifyUserLoginModel>() {
                    @Override
                    public void onResponse(Call<VerifyUserLoginModel> call, Response<VerifyUserLoginModel> response) {

                        String s = response.body().getLogin();

                        if (!s.isEmpty() && s.equalsIgnoreCase("PASS"))
                        {
                            Toast.makeText(MainActivity.this, "Log-in สำเร็จ", Toast.LENGTH_LONG).show();
                            OpenQRActivity();

                        }else {
                            Toast.makeText(MainActivity.this, "Log-in ไม่สำเร็จกรุณาตรวจสอบ User และ Password อีกครั้ง", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyUserLoginModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });*/

            }
        });
    }

    public void OpenQRActivity()
    {
        Intent intent = new Intent(MainActivity.this, CenterActivity.class);
        startActivity(intent);
    }
}
