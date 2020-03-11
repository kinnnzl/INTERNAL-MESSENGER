package com.example.abi_spare.oainternalmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    public class Summarry {
        private int id;
        private String txtSumL, txtSumR;
        private int imSumL;

        public Summarry(int id, String txtSumL, String txtSumR, int imSumL) {
            this.id = id;
            this.txtSumL = txtSumL;
            this.txtSumR = txtSumR;
            this.imSumL = imSumL;
        }

        public int getId() {
            return id;
        }

        public String getTxtSumL() {
            return txtSumL;
        }

        public String getTxtSumR() {
            return txtSumR;
        }

        public int getImSumL() {
            return imSumL;
        }

    }

    List<Summarry> sumList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        sumList = new ArrayList<>();

        sumList.add(
                new Summarry(
                        0,
                        "เอกสารขาเข้า",
                        "(CDG/IAD/ABI-IN-01)",
                        R.drawable.inbox));

        sumList.add(
                new Summarry(
                        1,
                        "เอกสารขาออก",
                        "(CDG/IAD/ABI-OUT-01)",
                        R.drawable.outbox));


        recyclerView = findViewById(R.id.rvSummarry);
        SummarryAdapter adapter = new SummarryAdapter(this, sumList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button btn = findViewById(R.id.btnSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
