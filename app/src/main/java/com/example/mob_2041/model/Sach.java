package com.example.mob_2041.model;

public class Sach {
    private int MaSach;
    private String TenSach;
    private int GiaThue;

    private int MaLoai;
    private String TenLoai;
    private int SoLanMuon;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai, String tenLoai) {
        MaSach = maSach;
        TenSach = tenSach;
        GiaThue = giaThue;
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }

    public Sach(int maSach, String tenSach, int soLanMuon) {
        MaSach = maSach;
        TenSach = tenSach;
        SoLanMuon = soLanMuon;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(int giaThue) {
        GiaThue = giaThue;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public int getSoLanMuon() {
        return SoLanMuon;
    }

    public void setSoLanMuon(int soLanMuon) {
        SoLanMuon = soLanMuon;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}

