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

import com.giuseviet.danhba.MainActivity;
import com.giuseviet.danhba.Model.User;
import com.giuseviet.danhba.Navigation;
import com.giuseviet.danhba.R;

import java.util.ArrayList;

public class AdapterUser extends BaseAdapter {

    Activity context;
    ArrayList<User> list;

    public AdapterUser(Activity context, ArrayList<User> list) {
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

        View row=inflater.inflate(R.layout.list,null);

        ImageView imvAvatar=row.findViewById(R.id.imv_avarta);
        TextView tvID=row.findViewById(R.id.tv_id);
        TextView tvTenTK=row.findViewById(R.id.tv_ten);
        TextView tvHoTen=row.findViewById(R.id.tv_sdt);
       //Button btnSua=row.findViewById(R.id.btn_sua);
        //Button btnXoa=row.findViewById(R.id.btn_xoa);

        //Đưa lên giao diện
        final User user=list.get(position);
        tvID.setText(user.getmIdUser()+"");
        tvTenTK.setText(user.getmTenTk());
        tvHoTen.setText(user.getmHoTen());
        final Bitmap bmAvatar= BitmapFactory.decodeByteArray(user.getmAnh(),0,user.getmAnh().length);
        imvAvatar.setImageBitmap(bmAvatar);

        return row;
    }
}
