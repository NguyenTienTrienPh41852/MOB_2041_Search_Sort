package com.example.mob_2041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mob_2041.fragment.fragment_add_user;
import com.example.mob_2041.fragment.fragment_doanh_thu;
import com.example.mob_2041.fragment.fragment_doi_mk;
import com.example.mob_2041.fragment.fragment_loai_sach;
import com.example.mob_2041.fragment.fragment_phieu_muon;
import com.example.mob_2041.fragment.fragment_sach;
import com.example.mob_2041.fragment.fragment_thanh_vien;
import com.example.mob_2041.fragment.fragment_top_10;
import com.example.mob_2041.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Context context = this;
    View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        header = navigationView.getHeaderView(0);
        Intent intent = getIntent();
        String thuThu = intent.getStringExtra("MaTT");
        dataHeader(thuThu);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menuQLPM){
                    fragment_phieu_muon frgPM = new fragment_phieu_muon();
                    relaceFrg(frgPM);
                    toolbar.setTitle("Quản lý phiếu mượn");
                }else if(item.getItemId() == R.id.menuQLLS){
                    fragment_loai_sach frgLS =  new fragment_loai_sach();
                    relaceFrg(frgLS);
                    toolbar.setTitle("Quản lý loại sách");
                }else if(item.getItemId() == R.id.menuQLS){
                    fragment_sach frgS = new fragment_sach();
                    relaceFrg(frgS);
                    toolbar.setTitle("Quản lý sách");
                }else if(item.getItemId() == R.id.menuQLTV){
                    fragment_thanh_vien frgTV = new fragment_thanh_vien();
                    relaceFrg(frgTV);
                    toolbar.setTitle("Quản lý thành viên");
                }else if(item.getItemId() == R.id.menuTop){
                    fragment_top_10 frgt = new fragment_top_10();
                    relaceFrg(frgt);
                    toolbar.setTitle("Top 10 sách mượn nhiều nhất");
                }else if(item.getItemId() == R.id.menuTND){
                    fragment_add_user frgTND = new fragment_add_user();
                    relaceFrg(frgTND);
                    toolbar.setTitle("Thêm người dùng");
                }else if(item.getItemId() == R.id.menuDMK){
                    fragment_doi_mk frgCP = new fragment_doi_mk();
                    relaceFrg(frgCP);
                    toolbar.setTitle("Đổi mật khẩu");
                }else if(item.getItemId() == R.id.menuDX){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chăn muướn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy",null);
                    builder.create().show();
                }else if(item.getItemId() == R.id.menuDT){
                    fragment_doanh_thu frgDT = new fragment_doanh_thu();
                    relaceFrg(frgDT);
                    toolbar.setTitle("Doanh thu");
                }
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }

    private void dataHeader(String thuThu) {
        TextView ten = header.findViewById(R.id.tenTTT);
        ten.setText(thuThu);
    }

    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}