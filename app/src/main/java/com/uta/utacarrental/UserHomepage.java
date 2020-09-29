package com.uta.utacarrental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

public class UserHomepage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Intent intent;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //抽屉滑动时回调
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //抽屉打开后回调
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //抽屉关闭后回调
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //抽屉滑动状态改变时回调
                switch (newState) {
                    case DrawerLayout.STATE_DRAGGING:
                        //拖动状态
                        //没有break,拖动也会执行设置状态的代码
                    case DrawerLayout.STATE_SETTLING:
                        //设置状态
                        System.out.println("设置");
                        ((TextView) findViewById(R.id.username)).setText(user.getUsername());
                        ((TextView) findViewById(R.id.role)).setText(user.getRole());
                        break;
                    case DrawerLayout.STATE_IDLE:
                        //静止状态
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_homepage, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logOut(MenuItem item){
        //注销时清空保存的登陆信息
        SharedPreferences sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor session = sharedpreferences.edit();
        session.clear();
        session.commit();

        //更改intent的目的地，销毁activity
        intent.setClass(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void changePassword(MenuItem item){
        //更改intent的目的地并讲flags设为不会销毁activity
        intent.setClass(this,ChangePasswordScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
