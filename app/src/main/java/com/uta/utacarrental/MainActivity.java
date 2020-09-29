package com.uta.utacarrental;

import android.content.Intent;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "UTA Car Rental System";

    SQLiteDatabase db = Connector.getDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("UTA Car Rental");


//        User user1 = new User();
//        user1.setName("dzl");
//        user1.setPassword("123");
//        user1.setRole("user");
//        user1.setUTAID("1001888813");
//        user1.save();
    }

    public void checkValidUser(View view) {
        String username=((EditText) findViewById(R.id.username)).getText().toString().trim();
        String password=((EditText) findViewById(R.id.password)).getText().toString().trim();


        if (username.length() != 0 && password.length() != 0){
            List<User> userList = LitePal.where("name = ? and password = ?",username,password).find(User.class);
            List<User> all_user = LitePal.findAll(User.class);
            if(userList.isEmpty()){
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }else {
                //传递user对象
                User user = userList.get(0);
                Bundle bundle=new Bundle();
                bundle.putSerializable("user", user);//序列化
                System.out.println(user.getUsername());
                Intent intent = new Intent(this, UserHomepage.class);
                intent.putExtras(bundle);
                //清空activity，无法再返回到此界面
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }else {
            Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_SHORT).show();
        }
    }
    public void registerDetails(View view) {
        //Intent intent = new Intent(this, Register.class);
        //Bundle bundle=new Bundle();
        //bundle.putSerializable("user", user);//序列化
        //intent.putExtra("extraKey", new User("aa","bb","cc"));
        startActivity(new Intent(this, Register.class));
    }
    public void forgotPassword(View view) {
        startActivity(new Intent(this, ForgotPasswordScreen.class));
    }

}
