package com.pikitori.example.android;

/**
 * Created by admin on 2016-12-28.
 */

public class JSONResult<DataT> {

    private String result;
    private String message;
    private DataT data;  // DataT == Object


    public JSONResult() {

    }

    public JSONResult(String result, String message, DataT data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public DataT getData() {
        return data;
    }

    public void setData(DataT data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess(){
        return "success".equals( this.result ) ? true : false;
    }

    @Override
    public String toString() {
        return "JSONResult{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
