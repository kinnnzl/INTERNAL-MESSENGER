package com.example.abi_spare.oainternalmessenger;

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

public class INAdapter extends RecyclerView.Adapter <INAdapter.INViewHolder>{
    private Context mCtx;
    private List<INActivity.IN> inList_from_QRCode;

    public INAdapter(Context mCtx, List<INActivity.IN> inList) {
        this.mCtx = mCtx;
        inList_from_QRCode = inList;
    }

    @NonNull
    @Override
    public INViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mCtx).inflate(R.layout.list_layout_of_in, viewGroup, false);
        return new INViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull INAdapter.INViewHolder inViewHolder, final int i) {

        INActivity.IN IN = inList_from_QRCode.get(i);
        inViewHolder.txtNumID.setText(IN.getTxtNumID());
        inViewHolder.txtDocID.setText(IN.getTxtDocID());
        inViewHolder.txtSendBy.setText(IN.getTxtSendBy());
        inViewHolder.txtSendTo.setText(IN.getTxtSendTo());
        inViewHolder.layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (i == 0){
                    Intent intent = new Intent(mCtx, INActivity.class);
                    mCtx.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(mCtx, OUTActivity.class);
                    mCtx.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return inList_from_QRCode.size();
    }

    class INViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNumID, txtDocID, txtSendBy, txtSendTo;
        private LinearLayout layout;

        public INViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumID = itemView.findViewById(R.id.inID);
            txtDocID = itemView.findViewById(R.id.QRNo);
            txtSendBy = itemView.findViewById(R.id.Sendder);
            txtSendTo = itemView.findViewById(R.id.SendTo);
            layout = itemView.findViewById(R.id.llIN);
        }
    }
}
