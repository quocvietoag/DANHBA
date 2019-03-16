package com.giuseviet.danhba.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.giuseviet.danhba.Model.NhanVien;
import com.giuseviet.danhba.Model.User;
import com.giuseviet.danhba.R;

import java.util.ArrayList;

public class AdapterNhanVien extends BaseAdapter {

    Activity context;
    ArrayList<NhanVien> list;
    public AdapterNhanVien(Activity context, ArrayList<NhanVien> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row=inflater.inflate(R.layout.item_nhanvien,null);

        ImageView imvAvatar=row.findViewById(R.id.nv_avatar);
        TextView tvTen=row.findViewById(R.id.nv_Hoten);
        TextView tvSdt=row.findViewById(R.id.nv_sdt);
        TextView tvChucvu=row.findViewById(R.id.nv_chucvu);
        TextView tvEmail=row.findViewById(R.id.nv_email);
        //Button btnSua=row.findViewById(R.id.btn_sua);
        //Button btnXoa=row.findViewById(R.id.btn_xoa);

        //Đưa lên giao diện
        final NhanVien nhanVien=list.get(position);
        tvTen.setText("Họ và Tên: "+nhanVien.getmTenNV());
        tvSdt.setText("Di động: "+nhanVien.getmSDT());
        tvChucvu.setText("Chức vụ: "+nhanVien.getmChucVu());
        tvEmail.setText("Email: "+nhanVien.getmEmail());

        final Bitmap bmAvatar= BitmapFactory.decodeByteArray(nhanVien.getmAnh(),0,nhanVien.getmAnh().length);
        imvAvatar.setImageBitmap(bmAvatar);
        return row;
    }
}
