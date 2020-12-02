package com.uta.utacarrental.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.profile.ProfileFragment;
import com.uta.utacarrental.view.profile.UpdateProfile;

import org.litepal.LitePal;

import java.util.List;

public class ViewSelectedUsers extends AppCompatActivity {

    TextView username;
    TextView lastname;
    TextView firstname;
    TextView utaId;
    TextView role;
    TextView password;
    TextView zipcode;
    TextView phoneoremail;
    TextView address;
    TextView city;
    TextView state;
    TextView privilegeStatus;
    TextView clubMemberStatus;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_users);

        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");
        Bundle bundle=getIntent().getExtras();
        String un=bundle.getString("username");
        //List<User> userList = LitePal.where("username = ?", "jeffGomez").find(User.class);
        List<User> userList = LitePal.where("username = ?", un).find(User.class);
        //final User user=userList.get(0);
        //userList.get(0).setStreet("zhichunRoad");
        //userList.get(0).updateAll("username = ?", "jeffGomez");
        username = findViewById(R.id.username);
        lastname = findViewById(R.id.lastname);
        firstname = findViewById(R.id.firstname);
        role = findViewById(R.id.role);
        password = findViewById(R.id.password);
        zipcode = findViewById(R.id.zipcode);
        phoneoremail = findViewById(R.id.phoneoremail);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        utaId = findViewById(R.id.utaid);
        state = findViewById(R.id.state);
        privilegeStatus = findViewById(R.id.privilegeStatus);
        clubMemberStatus = findViewById(R.id.clubmemberStatus);
        Button revoke = findViewById(R.id.revoke);
        username.setText(userList.get(0).getUsername());
        lastname.setText(userList.get(0).getLastname());
        firstname.setText(userList.get(0).getFirstname());
        role.setText(userList.get(0).getRole());
        password.setText(userList.get(0).getPassword());
        zipcode.setText(userList.get(0).getPhoneoremail());
        phoneoremail.setText(userList.get(0).getPhoneoremail());
        address.setText(userList.get(0).getStreet());
        city.setText(userList.get(0).getCity());
        utaId.setText(userList.get(0).getUTAID());
        state.setText(userList.get(0).getState());
        if (userList.get(0).isIsmember()) {
            clubMemberStatus.setText("yes");
        } else {
            clubMemberStatus.setText("no");
        }
        if (user.isPrivilege()) {
            privilegeStatus.setText("yes");
        } else {
            privilegeStatus.setText("no");
        }

        revoke.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {


                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                intent.setClass(ViewSelectedUsers.this, RevokeUser.class);
                startActivity(intent);

            }

        });
    }
}
