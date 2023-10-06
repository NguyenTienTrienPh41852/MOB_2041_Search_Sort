package com.example.mob_2041.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mob_2041.R;
import com.example.mob_2041.adapter.Top10_adapter;
import com.example.mob_2041.dao.ThongKeDao;
import com.example.mob_2041.model.Sach;

import java.util.ArrayList;


public class fragment_top_10 extends Fragment {

    public fragment_top_10() {
        // Required empty public constructor
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_10, container, false);
        RecyclerView rcv = view.findViewById(R.id.rcv_Top);

        ThongKeDao thongKeDao = new ThongKeDao(getContext());
        ArrayList<Sach> list = thongKeDao.getTop10();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Top10_adapter adapter = new Top10_adapter(getContext(),list);
        rcv.setAdapter(adapter);

        return view;
    }
}