package com.example.abi_spare.oainternalmessenger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SummarryAdapter extends RecyclerView.Adapter <SummarryAdapter.SummarryViewHolder> {

    private Context mCtx;
    private List<SummaryActivity.Summarry> SumList;
    private String name;


    public SummarryAdapter(Context mCtx, List<SummaryActivity.Summarry> sumList) {
        this.mCtx = mCtx;
        SumList = sumList;
    }

    @NonNull
    @Override
    public SummarryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mCtx).inflate(R.layout.list_layout_of_menu, viewGroup, false);
        return new SummarryAdapter.SummarryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SummarryAdapter.SummarryViewHolder summarryViewHolder, final int i) {

        if (i != 2) {
            SummaryActivity.Summarry sum = SumList.get(i);
            summarryViewHolder.txtSumL.setText(sum.getTxtSumL());
            summarryViewHolder.txtSumR.setText(sum.getTxtSumR());
            summarryViewHolder.imSumL.setImageResource(sum.getImSumL());
            summarryViewHolder.layout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    if (i == 0){
                        Intent intent = new Intent(mCtx, INActivity.class);
                        mCtx.startActivity(intent);
                    }
                    else if (i == 1)
                    {
                        Intent intent = new Intent(mCtx, OUTActivity.class);
                        mCtx.startActivity(intent);
                    }

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return SumList.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    class SummarryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imSumL;
        private TextView txtSumL, txtSumR;
        private LinearLayout layout;

        public SummarryViewHolder(@NonNull View itemView) {
            super(itemView);

            imSumL = itemView.findViewById(R.id.imSumL);
            txtSumL = itemView.findViewById(R.id.txtSumL);
            txtSumR = itemView.findViewById(R.id.txtSumR);
            layout = itemView.findViewById(R.id.llSummarry);
        }
    }
}
