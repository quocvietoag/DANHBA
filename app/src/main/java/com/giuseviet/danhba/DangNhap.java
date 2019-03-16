package com.giuseviet.danhba;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.giuseviet.danhba.Data.Database;
import com.giuseviet.danhba.Model.User;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {

    final String DATABASE_NAME = "data.sqlite";//Tên phải cho đúng với csdl truyền vào
    SQLiteDatabase database;//khởi tạo biến database để lấy các phương thức truy vấn....v..v.v

    //Khởi tạo một listvieew để chứa danh sách đọc từ csdl
    ListView lvDanhSachUser;
    ArrayList<User> listUser;
    ArrayList<User> list;

    EditText edtTenTK;
    EditText edtPass;
    Button btnDangNhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_dang_nhap);

        //thêm điều khiển
        addControl();


        database = Database.initDatabase(this, DATABASE_NAME);//Đọc dữ liệu vào

        //Phương thức đọc dữ liệu từ data.sqlite
        readData();

        //Phương thức Add sự kiện vào
        addEvent();
    }


    private void addEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentk = edtTenTK.getText().toString();//Lấy tên từ edittext
                String pass = edtPass.getText().toString();//lấy password
                Intent intent = new Intent(DangNhap.this, Navigation.class);
                if (tentk.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(DangNhap.this, "Bạn cần nhập đầy đủ tk và mk1", Toast.LENGTH_SHORT).show();
                } else {
                    int t = KiemTraTk(tentk, pass);
                    if (t >= 0) {
                        //gởi dữ liệu sang activity  Main
                        intent.putExtra("TK", String.valueOf(t));
                        startActivity(intent);
                        //Hủy luôn cái activity
                        finish();
                    } else {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc password sai!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //Biến dữ liệu báo
    int a = 0;
    private int KiemTraTk(String Tentk, String pass) {
        // int i=0;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getmTenTk().equals(Tentk) && listUser.get(i).getmPass().equals(pass)) {
                a = i;
                break;
            } else {
                a = -1;
            }
        }
        return a;
    }

    private void addControl() {
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        edtTenTK = findViewById(R.id.edt_tai_khoan);
        edtPass = findViewById(R.id.edt_mat_khau);
        listUser = new ArrayList<>();//khởi tạo một mảng ds
        list = new ArrayList<>();
    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);//Đọc dữ liệu vào
        //Khởi tạo biến trỏ đến từng bản và từng thuộc tính
        /*Tại sao sử dụng rawQuery vì thứ nhất: có câu truy vấn, thứ 2: có điều kiện truy vấn*/
        Cursor cursor = database.rawQuery("SELECT * FROM User", null);
        //dọn danh sách để trống và gán lại từ đầu vì sao, vì để lúc danh sách có sự thay đổi thì
        //List sẽ phát hiện
        listUser.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String TenTk = cursor.getString(1);
            String Pass = cursor.getString(2);
            String Ten = cursor.getString(3);
            String GioiTinh = cursor.getString(4);
            String ChucVu = cursor.getString(5);
            String Email = cursor.getString(6);
            byte[] Anh = cursor.getBlob(7);
            //Đưa list vào danh sách
            listUser.add(new User(id, TenTk, Pass, Ten, GioiTinh, ChucVu, Email, Anh));
        }
    }
}
