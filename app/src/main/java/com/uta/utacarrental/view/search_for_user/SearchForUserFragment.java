package com.uta.utacarrental.view.search_for_user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uta.utacarrental.MainActivity;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.admin.ViewSelectedUsers;
import com.uta.utacarrental.view.homepage.AdminHomepage;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class SearchForUserFragment extends Fragment {



    private SearchForUserViewModel searchForUserViewModel;

    private ArrayAdapter arrayAdapter;
    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchForUserViewModel =
                ViewModelProviders.of(this).get(SearchForUserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_user, container, false);
        final ListView listView = root.findViewById(R.id.display_search_user);
        final SearchView searchView = root.findViewById(R.id.text_search_user);
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        final String[] un = new String[1];
        searchView.setQueryHint("Enter username");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //单机搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                un[0] =query;
                listView.clearTextFilter();
                List<User> userList = LitePal.where("username = ? ",query).find(User.class);
                String[] data={"Username:"+userList.get(0).getUsername()+" ,Firstname:"+userList.get(0).getFirstname()+" ,Lastname:"+userList.get(0).getLastname()+" ,Phone or Email:"+userList.get(0).getPhoneoremail()};
                if(userList.isEmpty()){
                    Toast.makeText(getActivity(), "Invalid username", Toast.LENGTH_SHORT).show();
                }else if (userList.get(0).getRole().equals("admin") ){
                    Toast.makeText(getActivity(), "Invalid username", Toast.LENGTH_SHORT).show();
                }else {
                    arrayAdapter = new ArrayAdapter<String>(getActivity() , android.R.layout.simple_list_item_1 , data);
                    listView.setAdapter(arrayAdapter);
                }

                return true;
            }

            //输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                Bundle bundle = new Bundle();
                bundle.putString("username", un[0]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), ViewSelectedUsers.class);
                startActivity(intent);
            }
        });
        return root;
    }

}