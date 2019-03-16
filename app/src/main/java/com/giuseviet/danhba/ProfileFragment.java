package com.giuseviet.danhba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giuseviet.danhba.Adapter.AdapterUser;
import com.giuseviet.danhba.Data.Database;
import com.giuseviet.danhba.Model.User;

import java.util.ArrayList;

public class ProfileFragment extends Fragment  {

    final String DATABASE_NAME="data.sqlite";//Khởi tạo biến tên csdl
    SQLiteDatabase database;//Biến để lấy dữ liệu từ nguồn thông qua hàm
    ArrayList<User> list;
    AdapterUser adapter;

    ImageView avatar;
    TextView Profile_ID;
    TextView Profile_Ten;
    TextView Profile_GioiTinh;
    TextView Profile_ChucVu;
    TextView Profile_Email;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        addControl();

        readData();



        return view;
       // return inflater.inflate(R.layout.fragment_profile,container,false);


    }

    private void readData() {
       // database= Database.initDatabase(this,DATABASE_NAME);//Đọc dữ liệu tự file dữ liệu
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
      //  adapter=new AdapterUser(,list);
        Profile_ID= Profile_ID.findViewById(R.id.Profile_ID);
        Profile_Ten= Profile_Ten.findViewById(R.id.Profile_Name);
        Profile_GioiTinh=Profile_GioiTinh.findViewById(R.id.Profile_GioiTinh);
        Profile_ChucVu=Profile_ChucVu.findViewById(R.id.Profile_ChucVu);
        Profile_Email=Profile_Email.findViewById(R.id.Profile_Email);
    }
}
