package com.uta.utacarrental.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.profile.ProfileFragment;
import com.uta.utacarrental.view.profile.UpdateProfile;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class RevokeUser extends AppCompatActivity {
    SQLiteDatabase db = Connector.getDatabase();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revoke_user);

        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");

        TextView username = findViewById(R.id.username);
        TextView role = findViewById(R.id.role);
        final CheckBox privilage = findViewById(R.id.privilegeStatus);
        Button revoke = findViewById(R.id.revoke);
        final List<User> userList = LitePal.where("username = ? ",user.getUsername()).find(User.class);
        user = userList.get(0);
        username.setText(userList.get(0).getUsername());
        role.setText(userList.get(0).getRole());
        if (userList.get(0).isPrivilege()){
            privilage.setChecked(true);
        }else {
            privilage.setChecked(false);
        }
        revoke.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {

                if (privilage.isChecked()){
                    user.setPrivilege(true);
                }else {
                    user.setPrivilege(false);
                }
                user.updateAll("username = ?",user.getUsername());

                ContentValues values = new ContentValues();
                values.put("privilege", user.isPrivilege());
                LitePal.updateAll(User.class, values, "username = ?", user.getUsername());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("username", user.getUsername());
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                intent.setClass(RevokeUser.this, ViewSelectedUsers.class);
                startActivity(intent);

            }

        });
    }
}