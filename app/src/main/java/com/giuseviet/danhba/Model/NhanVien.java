package com.giuseviet.danhba.Model;

public class NhanVien {
    private int mIdNV;
    private String mTenNV;
    private String mGioiTinh;
    private String mSDT;
    private String mChucVu;
    private String mEmail;
    private String mMaP;
    private byte[] mAnh;

    public NhanVien(int mIdNV, String mTenNV, String mGioiTinh, String mSDT, String mChucVu, String mEmail, String mMaP, byte[] mAnh) {
        this.mIdNV = mIdNV;
        this.mTenNV = mTenNV;
        this.mGioiTinh = mGioiTinh;
        this.mSDT = mSDT;
        this.mChucVu = mChucVu;
        this.mEmail = mEmail;
        this.mMaP = mMaP;
        this.mAnh = mAnh;
    }

    public int getmIdNV() {
        return mIdNV;
    }

    public void setmIdNV(int mIdNV) {
        this.mIdNV = mIdNV;
    }

    public String getmTenNV() {
        return mTenNV;
    }

    public void setmTenNV(String mTenNV) {
        this.mTenNV = mTenNV;
    }

    public String getmGioiTinh() {
        return mGioiTinh;
    }

    public void setmGioiTinh(String mGioiTinh) {
        this.mGioiTinh = mGioiTinh;
    }

    public String getmSDT() {
        return mSDT;
    }

    public void setmSDT(String mSDT) {
        this.mSDT = mSDT;
    }

    public String getmChucVu() {
        return mChucVu;
    }

    public void setmChucVu(String mChucVu) {
        this.mChucVu = mChucVu;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmMaP() {
        return mMaP;
    }

    public void setmMaP(String mMaP) {
        this.mMaP = mMaP;
    }

    public byte[] getmAnh() {
        return mAnh;
    }

    public void setmAnh(byte[] mAnh) {
        this.mAnh = mAnh;
    }
}
