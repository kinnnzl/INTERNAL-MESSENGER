package com.example.abi_spare.oainternalmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class INActivity extends AppCompatActivity {

    static List<INActivity.IN> inList_from_QRCode;
    RecyclerView recyclerView;

    //Get inList from TestNewQRScan
    public static void setArrayList(List<IN> inList) {
        inList_from_QRCode = inList;
    }

    public static class IN {
        private int id;
        private String txtDocID, txtSendBy, txtSendTo, txtNumID;

        public IN(int id, String txtDocID, String txtSendBy, String txtSendTo) {
            this.id = id;
            txtNumID = Integer.toString(id);
            this.txtDocID = txtDocID;
            this.txtSendBy = txtSendBy;
            this.txtSendTo = txtSendTo;
        }

        public int getId() {
            return id;
        }

        public String getTxtNumID() { return txtNumID; }

        public String getTxtDocID() {
            return txtDocID;
        }

        public String getTxtSendBy() {
            return txtSendBy;
        }

        public String getTxtSendTo() {
            return txtSendTo;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

        recyclerView = findViewById(R.id.REIN);
        INAdapter adapter = new INAdapter(this, inList_from_QRCode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
