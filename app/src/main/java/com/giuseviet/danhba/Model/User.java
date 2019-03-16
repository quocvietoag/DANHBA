package com.giuseviet.danhba.Model;

public class User {
    private int mIdUser;
    private String mTenTk;
    private String mPass;
    private String mHoTen;
    private String mGioiTinh;
    private String mChucVu;
    private String mEmail;
    private byte[] mAnh;

    public User(int mIdUser, String mTenTk, String mPass, String mHoTen, String mGioiTinh, String mChucVu, String mEmail, byte[] mAnh) {
        this.mIdUser = mIdUser;
        this.mTenTk = mTenTk;
        this.mPass = mPass;
        this.mHoTen = mHoTen;
        this.mGioiTinh = mGioiTinh;
        this.mChucVu = mChucVu;
        this.mEmail = mEmail;
        this.mAnh = mAnh;
    }


    public int getmIdUser() {
        return mIdUser;
    }

    public void setmIdUser(int mIdUser) {
        this.mIdUser = mIdUser;
    }

    public String getmTenTk() {
        return mTenTk;
    }

    public void setmTenTk(String mTenTk) {
        this.mTenTk = mTenTk;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmHoTen() {
        return mHoTen;
    }

    public void setmHoTen(String mHoTen) {
        this.mHoTen = mHoTen;
    }

    public String getmGioiTinh() {
        return mGioiTinh;
    }

    public void setmGioiTinh(String mGioiTinh) {
        this.mGioiTinh = mGioiTinh;
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

    public byte[] getmAnh() {
        return mAnh;
    }

    public void setmAnh(byte[] mAnh) {
        this.mAnh = mAnh;
    }
}
