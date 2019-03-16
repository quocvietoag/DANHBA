package com.giuseviet.danhba;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.giuseviet.danhba.Adapter.AdapterUser;
import com.giuseviet.danhba.Data.Database;
import com.giuseviet.danhba.Model.User;

import java.util.ArrayList;

public class Profile_Fragment extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawer;

    ArrayList<User> list;
    AdapterUser adapter;

    final String DATABASE_NAME="data.sqlite";//Khởi tạo biến tên csdl
    SQLiteDatabase database;//

    ImageView avatar;
    TextView Profile_ID;
    TextView Profile_Ten;
    TextView Profile_GioiTinh;
    TextView Profile_ChucVu;
    TextView Profile_Email;


    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        addControl();

        readData();

        GetViewData();

       // ActionBar actionBar = getActionBar();
       // actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.setLogo(R.mipmap.ic_launcher);    //Icon muốn hiện thị
       // actionBar.setDisplayUseLogoEnabled(true);
        //ActionBar actionBar=getActionBar();
        //actionBar.setDisplayShowHomeEnabled(true);
        //toolbar=findViewById(R.id.toolbar1);
       // setSupportActionBar(toolbar);
       // toolbar=findViewById(R.id.toolbar1);
       // toolbar.setNavigationIcon(R.drawable.ic_back);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Fragment.this,Navigation.class);
                intent.putExtra("TK",id);
                startActivity(intent);
            }
        });*/

        //Thêm vào để quay trở về
        //android:parentActivityName=".Navigation"
    }

    private void GetViewData() {
        Intent intent=getIntent();
        id =intent.getStringExtra("ID");
        //Gán giá trị kiểu String
        String Pid=String.valueOf(list.get(Integer.valueOf(id)).getmIdUser());
        String PiTen=String.valueOf(list.get(Integer.valueOf(id)).getmHoTen());
        String PGioiTinh=String.valueOf(list.get(Integer.valueOf(id)).getmGioiTinh());
        String PChucVu=String.valueOf(list.get(Integer.valueOf(id)).getmChucVu());
        String PEmail=String.valueOf(list.get(Integer.valueOf(id)).getmEmail());

        //Đưa ra màn hình
        Profile_ID.setText("ID: "+Pid);
        Profile_Ten.setText("Họ và Tên: "+PiTen);
        Profile_GioiTinh.setText("Giới Tính: "+PGioiTinh);
        Profile_ChucVu.setText("Chức Vụ: "+PChucVu);
        Profile_Email.setText("Email: "+PEmail);
        final Bitmap bmAvatar= BitmapFactory.decodeByteArray(list.get(Integer.valueOf(id)).getmAnh(),0,list.get(Integer.valueOf(id)).getmAnh().length);
        avatar.setImageBitmap(bmAvatar);

    }

    private void readData() {
        database= Database.initDatabase(this,DATABASE_NAME);//Đọc dữ liệu tự file dữ liệu
        Cursor cursor=database.rawQuery("SELECT * FROM User",null);//dùng con
        //trỏ curcor để rút trích dữ liệu bằng câu truy vấn sql

        list.clear();//dọn danh sách sau mỗi lần cập nhật
        //Phần tử cuối trong list trống nên lỗi.
        //Cho nên ta -1 một để bỏ phần tử rỗng
        for(int i=0;i<cursor.getCount()-1;i++){//sử dụng vòng lập để đi đến từng đối tượng
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);//dùng con trỏ trỏ vào các thuộc tính
            String Ten=cursor.getString(1);
            String Pass=cursor.getString(2);
            String Hoten=cursor.getString(3);
            String Gioitinh=cursor.getString(4);
            String ChucVu=cursor.getString(5);
            String Email=cursor.getString(6);
            byte[] anh=cursor.getBlob(7);
            //tạo ra nhân viên mới vào mảng list vì không thể lấy trực tiếp từ csdl
            list.add(new User(id,Ten,Pass,Hoten,Gioitinh,ChucVu,Email,anh));//thêm nhân viên từ csdl vào trong mảng list
        }
        adapter.notifyDataSetChanged();//phát hiện sự thay đổi của dữ liệu*/
    }

    private void addControl() {
        avatar=findViewById(R.id.Profile_Imv);
        Profile_ID=findViewById(R.id.Profile_ID);
        Profile_Ten=findViewById(R.id.Profile_Name);
        Profile_GioiTinh=findViewById(R.id.Profile_GioiTinh);
        Profile_ChucVu=findViewById(R.id.Profile_ChucVu);
        Profile_Email=findViewById(R.id.Profile_Email);

        list=new ArrayList<>();
        adapter=new AdapterUser(this,list);


    }

}
