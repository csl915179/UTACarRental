package com.uta.utacarrental.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_users);

        List<User> userList = LitePal.where("username = ?", "jeffGomez").find(User.class);
        userList.get(0).setStreet("zhichunRoad");
        userList.get(0).updateAll("username = ?", "jeffGomez");
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
        if (userList.get(0).isPrivilege()) {
            privilegeStatus.setText("yes");
        } else {
            privilegeStatus.setText("no");
        }
    }
}
