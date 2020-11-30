package com.uta.utacarrental.view.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.navigation.fragment.NavHostFragment;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.User;

import org.litepal.LitePal;

import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private TextView username;
    private TextView lastname;
    private TextView firstname;
    private TextView utaId;
    private TextView role;
    private TextView password;
    private TextView zipcode;
    private TextView phoneoremail;
    private TextView address;
    private TextView city;
    private TextView state;
    private TextView privilegeStatus;
    private TextView clubMemberStatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        username = root.findViewById(R.id.username);
        lastname = root.findViewById(R.id.lastname);
        firstname = root.findViewById(R.id.firstname);
        role = root.findViewById(R.id.role);
        password = root.findViewById(R.id.password);
        zipcode = root.findViewById(R.id.zipcode);
        phoneoremail = root.findViewById(R.id.phoneoremail);
        address = root.findViewById(R.id.address);
        city = root.findViewById(R.id.city);
        utaId = root.findViewById(R.id.utaid);
        state = root.findViewById(R.id.state);
        privilegeStatus = root.findViewById(R.id.privilegeStatus);
        clubMemberStatus = root.findViewById(R.id.clubmemberStatus);

        // get login User
        Map<String, NavArgument> map = NavHostFragment.findNavController(this).getGraph().getArguments();
        final String usernameStr = (String) map.get("username").getDefaultValue();

        if (usernameStr!=null && !usernameStr.equals("")) {
            profileViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    List<User> userList = LitePal.where("username = ?", usernameStr).find(User.class);

                    if (!userList.isEmpty()) {
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
                    } else {
                        throw new RuntimeException("[ProfileFragment]search table with NavArgument: "
                                + userList + " username: " + usernameStr);
                    }
                }
            });
        } else {
            throw new RuntimeException("ProfileFragment: NavArgument of user information is null.");
        }
        return root;
    }
}