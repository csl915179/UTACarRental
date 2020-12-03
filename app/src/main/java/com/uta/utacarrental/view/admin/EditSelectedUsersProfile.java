package com.uta.utacarrental.view.admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;

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

    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selected_user_profile);

        // get User from viewSelectedUser page.
        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");

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

        username.setText(user.getUsername());
        lastname.setText(user.getLastname());
        firstname.setText(user.getFirstname());
        role.setText(user.getRole());
        password.setText(user.getPassword());
        zipcode.setText(user.getPhoneoremail());
        phoneoremail.setText(user.getPhoneoremail());
        address.setText(user.getStreet());
        city.setText(user.getCity());
        utaId.setText(user.getUTAID());
        state.setText(user.getState());
        if (user.isIsmember()) {
            clubMemberStatus.setText("yes");
        } else {
            clubMemberStatus.setText("no");
        }
        if (user.isPrivilege()) {
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
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
