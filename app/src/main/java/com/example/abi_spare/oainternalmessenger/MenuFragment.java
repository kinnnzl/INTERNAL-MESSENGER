package com.example.abi_spare.oainternalmessenger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    List<Menu> menuList;

    public MenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_menu, container,false);
        recyclerView = v.findViewById(R.id.Rec1);
        MenuAdapter adapter = new MenuAdapter(getActivity() ,getContext(), menuList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuList = new ArrayList<>();

        menuList.add(
                new Menu(
                        1,
                        "Scan QR Code",
                        "Scan QR Code ซองเอกสาร และ เทเอกสาร",
                        R.drawable.qrcode));
    }
}
