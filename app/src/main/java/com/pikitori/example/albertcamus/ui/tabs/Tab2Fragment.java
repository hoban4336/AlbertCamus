package com.pikitori.example.albertcamus.ui.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pikitori.example.albertcamus.R;
import com.pikitori.example.albertcamus.core.service.UserService;
import com.pikitori.example.android.SafeAsyncTask;

public class Tab2Fragment extends Fragment {

    private UserService userService = new UserService();
    EditText edit1 ;
    EditText edit2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container,false);

        Button sendbtn = (Button) view.findViewById(R.id.sendbtn);
         edit1 = (EditText) view.findViewById(R.id.edit_1);
         edit2 = (EditText) view.findViewById(R.id.edit_2);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edit1.getText().toString();
                String name =  edit2.getText().toString();

                new SendUserAsyncTask(email, name).execute();
            }
        });

        return view;
    }


    private class SendUserAsyncTask extends SafeAsyncTask{

        String email ;
        String name;
        SendUserAsyncTask(String name, String email){
            this.name = name;
            this.email = email;
        }

        @Override
        public Object call() throws Exception {

            userService.sendPostUser(name,email);
            return null;
        }
    }
}
