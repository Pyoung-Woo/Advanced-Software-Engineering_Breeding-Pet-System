package com.example.hj.product_160416;

import android.app.Application;

/**
 * Created by Heo on 2016-07-02.
 */
public class ResultListItem{

    String res_name;
    String res_result;

    public String getName(){return res_name;}
    public String getResult(){return res_result;}

    public void setResName(String in_name){
        this.res_name = in_name;
    }
    public void setResResult(String in_result){
        this.res_result = in_result;
    }


    ResultListItem(){

    }

    ResultListItem(String res_name, String res_result){
        this.res_name = res_name;
        this.res_result = res_result;
    }
}
