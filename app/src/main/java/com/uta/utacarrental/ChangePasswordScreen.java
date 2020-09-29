package com.uta.utacarrental;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class ChangePasswordScreen extends AppCompatActivity {

    SQLiteDatabase db = Connector.getDatabase();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);
        setTitle("Change Password");

        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");
        System.out.println(user.getUsername());
    }

    public void updatePassword(View view){

        String oldPassword=((EditText) findViewById(R.id.oldpassword)).getText().toString().trim();
        String newPassword=((EditText) findViewById(R.id.newpassword)).getText().toString().trim();
        String confirmNewPassword=((EditText) findViewById(R.id.confirmnewpassword)).getText().toString().trim();

        List<User> userList = LitePal.where("name = ? and password = ?",user.getUsername(),oldPassword).find(User.class);
        if (oldPassword.length() != 0 && newPassword.length() != 0 && confirmNewPassword.length() != 0){
            if (oldPassword.equals(newPassword)){
                Toast.makeText(getApplicationContext(), "The new password is the same as the old one.", Toast.LENGTH_SHORT).show();
            }else if(!newPassword.equals(confirmNewPassword)){
                Toast.makeText(getApplicationContext(), "Password confirmation doesn't match the new password", Toast.LENGTH_SHORT).show();
            }else if(userList.isEmpty()){
                Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
            }else {
                //更新数据库中的密码
                user.setPassword(newPassword);
                user.updateAll("name = ? and password = ?",user.getUsername(),oldPassword);
                //清空activity，无法再返回到此界面
                Intent intent = new Intent();
                intent.setClass(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Password updated successfully, please log in again", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_SHORT).show();
        }
    }
}