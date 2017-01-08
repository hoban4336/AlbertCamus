package com.pikitori.example.albertcamus.ui.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pikitori.example.albertcamus.R;
import com.pikitori.example.albertcamus.core.service.UserService;
import com.pikitori.example.albertcamus.core.vo.User;
import com.pikitori.example.android.SafeAsyncTask;

import java.util.List;

public class UserListFragment extends ListFragment {

    private static final String TAG = "###UserListFragment";
    private UserListArrayAdapter userListArrayAdapter;
    private UserService userService = new UserService();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setAdapter

        userListArrayAdapter = new UserListArrayAdapter(getActivity());
        setListAdapter(userListArrayAdapter);
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated()");
        new FetchUserListAsyncTask().execute();
    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<User>>{

        @Override
        public List<User> call() throws Exception {

            List<User> list  = userService.fetchUserList();
            return list;
        }

        @Override
        protected void onSuccess(List<User> list) throws Exception {
//            super.onSuccess(users);
            userListArrayAdapter.add(list);
        }
    }

//    private class FetchUserListAsyncTask extends SafeAsyncTask<String>{
//
//        @Override
//        public String call() throws Exception {
//
//            return userService.fetchUserList();
//        }
//
//        @Override
//        protected void onSuccess(String s) throws Exception {
////            super.onSuccess(s);
//        }
//    }
}
