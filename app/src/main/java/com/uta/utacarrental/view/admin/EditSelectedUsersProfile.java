package com.uta.utacarrental.view.admin;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;

import java.util.List;

public class EditSelectedUsersProfile extends AppCompatActivity {

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
    Button confirmBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_edit_selected_user_profile);

        // get User from viewSelectedUser page.
        List<User> userList = (List<User>)getIntent().getSerializableExtra("user");

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
        confirmBtn = findViewById(R.id.confirmBtn);

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

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User updatedUser = new User();
                updatedUser.setUsername(username.getText().toString());
                updatedUser.setLastname(lastname.getText().toString());
                updatedUser.setFirstname(firstname.getText().toString());
                updatedUser.setRole(role.getText().toString());
                updatedUser.setPassword(password.getText().toString());
                updatedUser.setZipcode(zipcode.getText().toString());
                updatedUser.setPhoneoremail(phoneoremail.getText().toString());
                updatedUser.setStreet(address.getText().toString());
                updatedUser.setCity(city.getText().toString());
                updatedUser.setUTAID(utaId.getText().toString());
                updatedUser.setState(state.getText().toString());
                if (privilegeStatus.getText().toString().equals("yes")) {
                    updatedUser.setPrivilege(true);
                } else {
                    updatedUser.setPrivilege(false);
                }
                if (clubMemberStatus.getText().toString().equals("yes")) {
                    updatedUser.setIsmember(true);
                } else {
                    updatedUser.setIsmember(false);
                }
                updatedUser.updateAll("username = ?", username.getText().toString());
            }
        });
    }
}
