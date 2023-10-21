package com.example.mob_2041.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob_2041.R;
import com.example.mob_2041.adapter.Sach_adapter;
import com.example.mob_2041.dao.LoaiSachDao;
import com.example.mob_2041.dao.SachDao;
import com.example.mob_2041.model.LoaiSach;
import com.example.mob_2041.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class fragment_sach extends Fragment {

    public fragment_sach() {
        // Required empty public constructor
    }

    RecyclerView rcv;
    FloatingActionButton fltAdd;
    ArrayList<Sach> list;
    ArrayList<Sach> listPhu;
    Sach_adapter sachAdapter;
    SachDao sachDao;
    Sach sach;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        rcv = view.findViewById(R.id.rcv_S);
        fltAdd = view.findViewById(R.id.add_S);
        sachDao = new SachDao(getContext());

        loadData();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAllSach();
            }
        });

        return view;


    }

    @SuppressLint("MissingInflatedId")
    private void dialogAllSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_sach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenSach = view.findViewById(R.id.in_addTenS);
        TextInputLayout in_GiaThue = view.findViewById(R.id.in_addGiaThue);
        TextInputLayout in_NamXuatBan = view.findViewById(R.id.in_addNamXuatBan);
        TextInputEditText ed_TenSach = view.findViewById(R.id.ed_addTenS);
        TextInputEditText ed_GiaThue = view.findViewById(R.id.ed_addGiaThue);
        TextInputEditText ed_NamXuatBan = view.findViewById(R.id.ed_addNamXuatBan);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        Button add = view.findViewById(R.id.S_add);
        Button tang = view.findViewById(R.id.btnTang);
        Button giam = view.findViewById(R.id.btnGiam);
        EditText edtTimKiem = view.findViewById(R.id.edtTimKiem);



        ed_TenSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenSach.setError("Vui lòng không để trống tên sách");
                }else{
                    in_TenSach.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_GiaThue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_GiaThue.setError("Vui lòng không để trống giá thuê");
                }else{
                    in_GiaThue.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_NamXuatBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_NamXuatBan.setError("Vui lòng không để trống giá thuê");
                }else{
                    in_NamXuatBan.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"TenLoai"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = ed_TenSach.getText().toString();
                String checktien = ed_GiaThue.getText().toString();;
                String checknamxuatban = ed_NamXuatBan.getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maloai = (int) hs.get("MaLoai");


                if(tensach.isEmpty() || checktien.isEmpty()){
                    if(tensach.equals("")){
                        in_TenSach.setError("Vui lòng không để trống tên sách");
                    }else{
                        in_TenSach.setError(null);
                    }

                    if(checktien.equals("")){
                        in_GiaThue.setError("Vui lòng không để trống giá thuê");
                    }else{
                        in_GiaThue.setError(null);
                    }

                    if(checknamxuatban.equals("")){
                        in_NamXuatBan.setError("Vui lòng không để trống giá thuê");
                    }else{
                        in_NamXuatBan.setError(null);
                    }
                }else{
                    int tien = Integer.parseInt(checktien);
                    int namxuatban = Integer.parseInt(checknamxuatban);
                    boolean check = sachDao.insert(tensach,tien,namxuatban,maloai);
                    if(check){
                        loadData();
                        Toast.makeText(getContext(), "Thêm thành công sách", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm không thành công sách", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list.clear();
                String searchText = s.toString().toLowerCase();
                for(Sach sach: listPhu){
                    String tenSach = sach.getTenSach().toLowerCase();
                    if (tenSach.contains(searchText)){
                        list.add(sach);
                    }
                }
               rcv.setAdapter(sachAdapter);
                sachAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    sapXepTang(list);
    rcv.setAdapter(sachAdapter);
    sachAdapter.notifyDataSetChanged();
            }
        });
        giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sapXepGiam(list);
                rcv.setAdapter(sachAdapter);
                sachAdapter.notifyDataSetChanged();
            }
        });

    }

    private ArrayList<HashMap<String , Object>> getDSLoaiSach(){
        LoaiSachDao loaisach = new LoaiSachDao(getContext());
        ArrayList<LoaiSach> list1 = loaisach.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSach ls : list1){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("MaLoai", ls.getMaLS());
            hs.put("TenLoai", ls.getTenLS());
            listHM.add(hs);
        }
        return listHM;
    }
    private void sapXepTang(List<Sach> ds){
        ds.sort(Comparator.comparing(Sach::getGiaThue));
    }
    private void sapXepGiam(List<Sach> ds){
        ds.sort(Comparator.comparing(Sach::getGiaThue).reversed());
    }
    private void loadData(){
        list = sachDao.getDSSach();
        listPhu = sachDao.getDSSach();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Sach_adapter adapter = new Sach_adapter(getContext(),list, getDSLoaiSach(), sachDao);
        rcv.setAdapter(adapter);
    }
}
