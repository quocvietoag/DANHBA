package com.giuseviet.danhba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.giuseviet.danhba.Adapter.AdapterUser;
import com.giuseviet.danhba.Data.Database;
import com.giuseviet.danhba.Model.User;

import java.util.ArrayList;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawer;

    final String DATABASE_NAME="data.sqlite";//Khởi tạo biến tên csdl
    SQLiteDatabase database;//Biến để lấy dữ liệu từ nguồn thông qua hàm

    //Biến tên
    ImageView avatar;
    TextView ten;
    TextView email;

    ArrayList<User> list;
    AdapterUser adapter;

    String id;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.gd_navigation);


        addControl();

        readData();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Nhận dữ liêu từ đăng nhận
        Intent intent=getIntent();
        //Gán giá trị từ trang đăng nhập
        id =intent.getStringExtra("TK");

        drawer=findViewById(R.id.drawer_layout);

        //Khởi tạo sự kiện gọi thanh menu
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        //Công phu lắm à nghe ơ mệt quá!!!!
        //Từ layout bên kia
        View v = navigationView.getHeaderView(0);
        TextView hoten = v.findViewById(R.id.tv_nav_header_name);
        TextView email = v.findViewById(R.id.tv_nav_header_mail);
        ImageView anh=v.findViewById(R.id.imv_nav_header);
        hoten.setText(list.get(Integer.valueOf(id)).getmHoTen());
        email.setText(list.get(Integer.valueOf(id)).getmEmail());
        //anh.setImageBitmap(list.get(Integer.valueOf(id)).getmAnh());

        final Bitmap bmAvatar= BitmapFactory.decodeByteArray(list.get(Integer.valueOf(id)).getmAnh(),0,list.get(Integer.valueOf(id)).getmAnh().length);
        anh.setImageBitmap(bmAvatar);





        //Có nút vẽ
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        /*
        if(saveInstanceState==null){
        //Cấu hình fragment mặc định khi bắt đầu
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_contactphone);}

           */
        //String id = "0";
        //String hoten=String.valueOf(list.get(Integer.valueOf(id)).getmHoTen());






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
        list=new ArrayList<>();
        adapter=new AdapterUser(this,list);

        avatar=findViewById(R.id.imv_nav_header);
        ten=findViewById(R.id.tv_nav_header_name);
        email=findViewById(R.id.tv_nav_header_mail);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId()) {
            case R.id.nav_acount:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Profile_Fragment()).commit();
                Intent intent=new Intent(Navigation.this,Profile_Fragment.class);
                intent.putExtra("ID",id);
                startActivity(intent);
                break;
            case R.id.nav_contactphone:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
                Intent intent1=new Intent(Navigation.this,Contact_Fragment.class);
                startActivity(intent1);
                break;
            case R.id.nav_Phan_khoa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DivContactFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Sự kiến nhấn vào biểu tượng
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
