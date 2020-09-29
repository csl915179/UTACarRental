package com.uta.utacarrental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "UTA Car Rental System";

    SQLiteDatabase db = Connector.getDatabase();

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //连接SQLiteStudio以查看数据
        SQLiteStudioService.instance().start(this);

        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

        List<User> userList = LitePal.where("username = ? and password = ?",sharedpreferences.getString("username",""),sharedpreferences.getString("password","")).find(User.class);

        if(!userList.isEmpty()) {
            loginSuccess(userList.get(0));
        }else {

            setContentView(R.layout.activity_main);

            setTitle("UTA Car Rental");
        }



    }

    public void checkValidUser(View view) {
        String username=((EditText) findViewById(R.id.username)).getText().toString().trim();
        String password=((EditText) findViewById(R.id.password)).getText().toString().trim();


        if (username.length() != 0 && password.length() != 0){
            List<User> userList = LitePal.where("username = ? and password = ?",username,password).find(User.class);
            List<User> all_user = LitePal.findAll(User.class);
            if(userList.isEmpty()){
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }else {
                loginSuccess(userList.get(0));
                //通过输入账号密码登陆成功后，将账号密码保存下来
                SharedPreferences.Editor session = sharedpreferences.edit();
                session.putString("username", username);
                session.putString("password", password);
                session.commit();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginSuccess(User user){
        //传递user对象
        Bundle bundle=new Bundle();
        bundle.putSerializable("user", user);//序列化
        Intent intent = new Intent(this, UserHomepage.class);
        intent.putExtras(bundle);
        //清空activity，无法再返回到此界面
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void registerDetails(View view) {
        startActivity(new Intent(this, Register.class));
    }
    public void forgotPassword(View view) {
        startActivity(new Intent(this, ForgotPasswordScreen.class));
    }

}
