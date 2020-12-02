package com.uta.utacarrental.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_users);

        Bundle bundle = getIntent().getExtras();
        String un = bundle.getString("username");
        List<User> userList = LitePal.where("username = ?", un).find(User.class);
        final User user = userList.get(0);

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
        editBtn = findViewById(R.id.edit);

        Button revoke = findViewById(R.id.revoke);

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

        // jump to edit page.
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSelectedUsers.this, EditSelectedUsersProfile.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        revoke.setOnClickListener(new Button.OnClickListener() {
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
