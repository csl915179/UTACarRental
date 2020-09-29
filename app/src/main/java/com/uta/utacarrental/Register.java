package com.uta.utacarrental;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class Register extends AppCompatActivity {

    SQLiteDatabase db = Connector.getDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
    }

    public void register(View view) {

        String username=((EditText) findViewById(R.id.username)).getText().toString().trim();
        String password=((EditText) findViewById(R.id.password)).getText().toString().trim();
        String role = (String) ((Spinner) findViewById(R.id.role)).getSelectedItem();
        String UTAID=((EditText) findViewById(R.id.password)).getText().toString().trim();
        String phoneoremail=((EditText) findViewById(R.id.phoneoremail)).getText().toString().trim();
        String lastname=((EditText) findViewById(R.id.lastname)).getText().toString().trim();
        String firstname=((EditText) findViewById(R.id.firstname)).getText().toString().trim();
        String street=((EditText) findViewById(R.id.street)).getText().toString().trim();
        String city=((EditText) findViewById(R.id.city)).getText().toString().trim();
        String state=((EditText) findViewById(R.id.state)).getText().toString().trim();
        String zipcode=((EditText) findViewById(R.id.zipcode)).getText().toString().trim();
        Boolean ismember = ((CheckBox) findViewById(R.id.ismember)).isChecked();

        if (username.length() != 0 && password.length() != 0){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setUTAID(UTAID);
            user.setPhoneoremail(phoneoremail);
            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setStreet(street);
            user.setState(state);
            user.setCity(city);
            user.setZipcode(zipcode);
            user.setIsmember(ismember);

            if(user.save()){
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}