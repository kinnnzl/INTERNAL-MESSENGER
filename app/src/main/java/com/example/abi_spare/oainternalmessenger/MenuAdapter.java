package com.example.abi_spare.oainternalmessenger;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter <MenuAdapter.MenuViewHolder> {

    private Context mCtx;
    private Activity act;
    private List<Menu> MenuList;

    private static final int REQUEST_CAMERA = 0;

    public MenuAdapter(Activity act, Context mCtx, List<Menu> menuList) {
        this.mCtx = mCtx;
        this.act = act;
        MenuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mCtx).inflate(R.layout.list_layout_of_menu_item, viewGroup, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Menu menu = MenuList.get(i);
        menuViewHolder.textViewTitle.setText(menu.getTitle());
        menuViewHolder.textViewDesc.setText(menu.getShortdesc());
        menuViewHolder.imageView.setImageResource(menu.getImage());
        menuViewHolder.layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //requestForCameraPermission();
                OpenQRActivity();
            }
        });

    }

    public void OpenQRActivity()
    {
        Intent intent = new Intent(mCtx, TestNewQRScan.class);
        mCtx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return MenuList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewTitle, textViewDesc;
        private LinearLayout layout;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imSumL);
            textViewTitle = itemView.findViewById(R.id.txtSumL);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            layout = itemView.findViewById(R.id.OpenScanQR);
        }
    }

    public void requestForCameraPermission() {
        final String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(act, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(act, permission)) {
                showPermissionRationaleDialog("กรุณาอนุญาติให้เราใช้งานกล้องของคุณ", permission);
            } else {
                requestForPermission(permission);
            }
            runCamera();
        } else {
            runCamera();
        }
    }

    private void showPermissionRationaleDialog(final String message, final String permission) {
        new AlertDialog.Builder(act)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestForPermission(permission);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        act.finish();
                    }
                })
                .create()
                .show();
    }

    private void  runCamera(){
        final String permission = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(act, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(act, permission)) {
                showPermissionRationaleDialog("กรุณาอนุญาติให้เราใช้งานกล้องของคุณ", permission);
            } else {
                requestForPermission(permission);
            }
            runCamera();
        } else {

            Intent intent = new Intent(mCtx, ReturnToMenuActivity.class);
            mCtx.startActivity(intent);
        }
    }

    private void requestForPermission(final String permission) {
        ActivityCompat.requestPermissions(act, new String[]{permission}, REQUEST_CAMERA);
    }

}
