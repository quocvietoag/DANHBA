package com.giuseviet.danhba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.giuseviet.danhba.Adapter.AdapterNhanVien;
import com.giuseviet.danhba.Adapter.AdapterUser;
import com.giuseviet.danhba.Data.Database;
import com.giuseviet.danhba.Model.NhanVien;
import com.giuseviet.danhba.Model.User;

import java.util.ArrayList;

public class Contact_Fragment extends AppCompatActivity {

    final String DATABASE_NAME="data.sqlite";//Khởi tạo biến tên csdl
    SQLiteDatabase database;//Biến để lấy dữ liệu từ nguồn thông qua hàm

    ArrayList<NhanVien> list;
    AdapterNhanVien adapter;

    TextView id,ten;
    ListView contact_list;
    EditText edtTim;
    Button btnTim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact);

        addControl();
        readData();
        addEvent();





    }

    private void addEvent() {
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=edtTim.getText().toString();
                if(ten.isEmpty()){
                    readData();
                }else {
                    readDatadk(ten);
                }
            }
        });
    }

    private void readDatadk(String ten) {
        database= Database.initDatabase(this,DATABASE_NAME);//Đọc dữ liệu tự file dữ liệu
        Cursor cursor=database.rawQuery("SELECT * FROM NhanVien where Ten like ?",new String[]{ten+"%"});//dùng con
        //trỏ curcor để rút trích dữ liệu bằng câu truy vấn sql

        list.clear();//dọn danh sách sau mỗi lần cập nhật
        //Phần tử cuối trong list trống nên lỗi.
        //Cho nên ta -1 một để bỏ phần tử rỗng
        for(int i=0;i<cursor.getCount();i++){//sử dụng vòng lập để đi đến từng đối tượng
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);//dùng con trỏ trỏ vào các thuộc tính
            String Ten=cursor.getString(1);
            String Gioitinh=cursor.getString(2);
            String SDT=cursor.getString(3);
            String ChucVu=cursor.getString(4);
            String Email=cursor.getString(5);
            String MaP=cursor.getString(7);
            byte[] anh=cursor.getBlob(6);
            //tạo ra nhân viên mới vào mảng list vì không thể lấy trực tiếp từ csdl
            list.add(new NhanVien(id,Ten,Gioitinh,SDT,ChucVu,Email,MaP,anh));//thêm nhân viên từ csdl vào trong mảng list
        }
        adapter.notifyDataSetChanged();//phát hiện sự thay đổi của dữ liệu*/
    }

    private void readData() {
        database= Database.initDatabase(this,DATABASE_NAME);//Đọc dữ liệu tự file dữ liệu
        Cursor cursor=database.rawQuery("SELECT * FROM NhanVien",null);//dùng con
        //trỏ curcor để rút trích dữ liệu bằng câu truy vấn sql

        list.clear();//dọn danh sách sau mỗi lần cập nhật
        //Phần tử cuối trong list trống nên lỗi.
        //Cho nên ta -1 một để bỏ phần tử rỗng
        for(int i=0;i<cursor.getCount();i++){//sử dụng vòng lập để đi đến từng đối tượng
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);//dùng con trỏ trỏ vào các thuộc tính
            String Ten=cursor.getString(1);
            String Gioitinh=cursor.getString(2);
            String SDT=cursor.getString(3);
            String ChucVu=cursor.getString(4);
            String Email=cursor.getString(5);
            String MaP=cursor.getString(7);
            byte[] anh=cursor.getBlob(6);
            //tạo ra nhân viên mới vào mảng list vì không thể lấy trực tiếp từ csdl
            list.add(new NhanVien(id,Ten,Gioitinh,SDT,ChucVu,Email,MaP,anh));//thêm nhân viên từ csdl vào trong mảng list
        }
        adapter.notifyDataSetChanged();//phát hiện sự thay đổi của dữ liệu*/
    }

    private void addControl() {
        list=new ArrayList<>();
        adapter=new AdapterNhanVien(this,list);
      //  id=findViewById(R.id.Contact_id);
        contact_list=findViewById(R.id.contact_list);
        contact_list.setAdapter(adapter);

        edtTim=findViewById(R.id.edt_timkiem_nhanvien);
        btnTim=findViewById(R.id.btn_nv_loc);
    }
}
