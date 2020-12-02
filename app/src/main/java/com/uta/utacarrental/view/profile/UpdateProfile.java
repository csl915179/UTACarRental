package com.uta.utacarrental.view.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.homepage.AdminHomepage;

import org.litepal.LitePal;

import java.util.List;

import static com.uta.utacarrental.view.reservation.ReservationAdapter.inflater;

public class UpdateProfile extends AppCompatActivity {

    private TextView username;
    private TextView lastname;
    private TextView firstname;
    private TextView utaId;
    private TextView role;
    private TextView zipcode;
    private TextView phoneoremail;
    private TextView street;
    private TextView city;
    private TextView state;
    private CheckBox clubMemberStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Bundle bundle=getIntent().getExtras();
        String un=bundle.getString("username");

        final List<User> userList = LitePal.where("username = ?", un).find(User.class);
        final User user = userList.get(0);
        username = findViewById(R.id.username);
        lastname = findViewById(R.id.lastname);
        firstname = findViewById(R.id.firstname);
        role = findViewById(R.id.role);
        zipcode = findViewById(R.id.zipcode);
        phoneoremail = findViewById(R.id.phoneoremail);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        utaId = findViewById(R.id.utaid);
        state = findViewById(R.id.state);
        CheckBox clubMemberStatus = findViewById(R.id.clubmemberStatus);
        Button update = findViewById(R.id.update);
        username.setText(userList.get(0).getUsername());
        lastname.setText(userList.get(0).getLastname());
        firstname.setText(userList.get(0).getFirstname());
        role.setText(userList.get(0).getRole());
        zipcode.setText(userList.get(0).getZipcode());
        phoneoremail.setText(userList.get(0).getPhoneoremail());
        street.setText(userList.get(0).getStreet());
        city.setText(userList.get(0).getCity());
        utaId.setText(userList.get(0).getUTAID());
        state.setText(userList.get(0).getState());
        if (userList.get(0).isIsmember()) {
            clubMemberStatus.setChecked(true);
        } else {
            clubMemberStatus.setChecked(false);
        }

        final String UTAID=((EditText)findViewById(R.id.utaid)).getText().toString();
        final String Phoneoremail=((EditText)findViewById(R.id.phoneoremail)).getText().toString();
        final String Lastname=((EditText)findViewById(R.id.lastname)).getText().toString();
        final String Firstname=((EditText) findViewById(R.id.firstname)).getText().toString();
        final String Street=((EditText) findViewById(R.id.street)).getText().toString();
        final String City=((EditText) findViewById(R.id.city)).getText().toString();
        final String State=((EditText) findViewById(R.id.state)).getText().toString();
        final String Zipcode=((EditText) findViewById(R.id.zipcode)).getText().toString();
        final Boolean Ismember = ((CheckBox) findViewById(R.id.clubmemberStatus)).isChecked();
        update.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                user.setLastname(Lastname);
                user.setCity(City);
                user.setFirstname(Firstname);
                user.setUTAID(UTAID);
                user.setPhoneoremail(Phoneoremail);
                user.setStreet(Street);
                user.setState(State);
                user.setZipcode(Zipcode);
                user.setIsmember(Ismember);
                user.updateAll("username = ?",user.getUsername());

                ContentValues values= new ContentValues();
                values.put("phoneoremail",Phoneoremail);
                values.put("UTAID", UTAID);
                values.put("lastname",Phoneoremail);
                values.put("firstname", UTAID);
                values.put("street",Street);
                values.put("city", City);
                values.put("state",State);
                values.put("zipcode", Zipcode);
                values.put("ismember", Ismember);
                LitePal.updateAll(User.class, values, "username = ?", user.getUsername());



                Intent intent = new Intent();
                intent.setClass(UpdateProfile.this, ProfileFragment.class);
                startActivity(intent);

            }

        });


    }
}