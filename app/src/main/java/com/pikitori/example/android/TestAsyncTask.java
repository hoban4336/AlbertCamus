package com.pikitori.example.android;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pikitori.example.albertcamus.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "###TestAsyncTask";

    private Context mContext;
    private View mView;

//    생성자에게 Context 전달!
    public TestAsyncTask(View rootView){
        this.mView = rootView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute ");
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        try {
            //java.net.URL
            URL url = new URL("http://192.168.1.4:8080/camus/user/list");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET"); // get방식 통신
//            conn.setDoOutput(true);       // 쓰기모드 지정
//            conn.setDoInput(true);        // 읽기모드 지정

//            conn.setUseCaches(false);     // 캐싱데이터를 받을지 안받을지
//            conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정

            InputStream is = conn.getInputStream();

            StringBuilder builder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));


            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line +"\n");
            }

            result = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d(TAG, "onPostExecute"+ result);

        ((Button)mView.findViewById(R.id.button)).setText("Thread END");
        ((TextView)mView.findViewById(R.id.tx_info)).setText(result);
    }
}
