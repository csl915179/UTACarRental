package com.uta.utacarrental;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.view.View;
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

        if (username.length() != 0 && password.length() != 0){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            if(user.save()){
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}