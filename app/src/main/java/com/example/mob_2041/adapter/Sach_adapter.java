package com.example.mob_2041.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_2041.R;
import com.example.mob_2041.dao.SachDao;
import com.example.mob_2041.model.Sach;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class Sach_adapter extends RecyclerView.Adapter<Sach_adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SachDao sachDao;

    public Sach_adapter(Context context, ArrayList<Sach> list, ArrayList<HashMap<String, Object>> listHM,SachDao sachDao) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDao = sachDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText(list.get(position).getTenSach());
        holder.txtGiaThue.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtNamXuatBan.setText(String.valueOf(list.get(position).getNamXuatBan()));
        holder.txtLoaiSach.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txtTenLS.setText(list.get(position).getTenLoai());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogAddSach(list.get(holder.getAdapterPosition()));
                return true;
            }
        });

        holder.Sach_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa Sách");
                builder.setMessage("Bạn có chắc muốn xóa sách này chứ ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = sachDao.delete(list.get(holder.getAdapterPosition()).getMaSach());
                        switch (check){
                            case 1:
                                loadData();
                                Toast.makeText(context, "Xóa thành công sách", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công sách", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sách này vì đang còn tồn tại trong phiếu mượn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy",null);
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach, txtGiaThue,txtNamXuatBan, txtLoaiSach, txtTenLS;
        ImageView Sach_Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.MaSach);
            txtTenSach = itemView.findViewById(R.id.TenS);
            txtGiaThue = itemView.findViewById(R.id.Sach_GiaThue);
            txtNamXuatBan = itemView.findViewById(R.id.Sach_NamXuatBan);
            txtLoaiSach = itemView.findViewById(R.id.Sach_LS);
            txtTenLS = itemView.findViewById(R.id.Sach_TenLS);
            Sach_Delete = itemView.findViewById(R.id.S_Delete);
        }
    }

    @SuppressLint("MissingInflatedId")
    private void dialogAddSach(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenSach = view.findViewById(R.id.in_updateTenS);
        TextInputLayout in_GiaThue = view.findViewById(R.id.in_updateGiaThue);
        TextInputLayout in_NamXuatBan = view.findViewById(R.id.in_updateNamXuatBan);
        TextInputEditText ed_TenSach = view.findViewById(R.id.ed_updateTenS);
        TextInputEditText ed_GiaThue = view.findViewById(R.id.ed_updateGiaThue);
        TextInputEditText ed_NamXuatBan = view.findViewById(R.id.ed_updateNamXuatBan);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        Button add = view.findViewById(R.id.S_update);


        ed_TenSach.setText(sach.getTenSach());
        ed_GiaThue.setText(String.valueOf(sach.getGiaThue()));
        ed_NamXuatBan.setText((String.valueOf(sach.getNamXuatBan())));

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
                    in_NamXuatBan.setError("Vui lòng không để trống năm xuất bản");
                }else{
                    in_NamXuatBan.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                (android.R.layout.simple_list_item_1),
                new String[]{"TenLoai"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);
        int index = 0;
        int position = -1;

        for(HashMap<String, Object> item : listHM){
            if((int) item.get("MaLoai") == sach.getMaLoai()){
                position = index;
            }
            index ++;
        }
        spnSach.setSelection(position);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = ed_TenSach.getText().toString();
                String checktien = ed_GiaThue.getText().toString();;
                String checknamxuatban = ed_NamXuatBan.getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maloai = (int) hs.get("MaLoai");


                if(tensach.isEmpty() || checktien.isEmpty() || checknamxuatban.isEmpty()){
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
                        in_GiaThue.setError("Vui lòng không để trống năm xuất bản");
                    }else{
                        in_GiaThue.setError(null);
                    }
                }else{
                    int tien = Integer.parseInt(checktien);
                    int namxuatban = Integer.parseInt(checknamxuatban);
                    boolean check = sachDao.update(sach.getMaSach(),tensach,tien,namxuatban,maloai);
                    if(check){
                        loadData();
                        Toast.makeText(context, "Cập nhật thành công sách", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật không thành công sách", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private void loadData(){
        list.clear();
        list = sachDao.getDSSach();
        notifyDataSetChanged();
    }
}
