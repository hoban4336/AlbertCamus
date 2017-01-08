package com.pikitori.example.albertcamus.core.service;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pikitori.example.albertcamus.core.vo.User;
import com.pikitori.example.android.JSONResult;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeon on 2016-12-27.
 */

public class UserService {

    private static final String TAG = "UserService";
    public List<User> fetchMockUserList() {

        List<User> list = new ArrayList<User>();

        User user = new User();
        user.setId(1L);
        user.setName("안대혁");
        user.setPhone("010-4761-6934");
        user.setEmail("kickscar@gmail.com");
        user.setProfilePic("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/936089_1019758748039064_7187347097932848216_n.jpg?oh=ec46e4053b89bf9ea434f1cf79b6ce43&oe=58B4B3C8");
        user.setStatus(1);
        list.add(user);

        return list;

    }

    public List<User> fetchUserList() {
        String url = "http://192.168.1.4:8080/camus/user/list";
        HttpRequest httpRequest = HttpRequest.get(url);
        httpRequest.contentType(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.code();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP Response:" + responseCode);
        }

//        json 출력
//        String result = null;
//        try {
//            InputStream is = httpRequest.getConnection().getInputStream();
//            StringBuilder builder = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                builder.append(line +"\n");
//            }
//
//            result = builder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.d("result", result);
//
//        return result;

//        파싱

        JSONResultUserList jsonResultUserList = fromJSON(httpRequest, JSONResultUserList.class);

        return jsonResultUserList.getData();
    }

    private class JSONResultUserList extends JSONResult<List<User>> {
        //Result{},Data{ 여기 내용이 == List<User> }
        //따로 오버라이딩할 내용이 없다.
    }


    /* 파싱!!!!!!! 중요 !!!!!!!!!
   * Json 문자열을 자바 객체로 변환
   * @param request
   * @param target
   * @param <V>
   * @return
   * */
    private <V> V fromJSON(HttpRequest httpRequest, Class<V> target) {
// V == Object
        V v = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = httpRequest.bufferedReader();
            v = gson.fromJson(reader, target);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return v;


    }


    public void  sendGetUser(String name, String email){

        String url = "http://192.168.1.4:8080/camus/user/get";

        HttpRequest request =  HttpRequest.get(url,true,"name", name,"email",email);

        Log.d(TAG, ""+request.toString());

        int response = request.code();
        String reponsebody = request.body();

        Log.d(TAG, ""+response+"/"+ reponsebody);

    }
    public void  sendPostUser(String name, String email){

        String url = "http://192.168.1.4:8080/camus/user/get";

        HttpRequest request =  HttpRequest.post(url);

        Log.d(TAG, ""+request.toString());

        request.send("name=" + name + "&email="+ email);

        int response = request.code();
        String reponsebody = request.body();

        Log.d(TAG, ""+response+"/"+ reponsebody);

    }


}
