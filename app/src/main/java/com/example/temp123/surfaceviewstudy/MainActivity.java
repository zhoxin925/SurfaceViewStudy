package com.example.temp123.surfaceviewstudy;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
//    RecyclerView recyclerView;
//
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Log.d("handler study---what==", String.valueOf(msg.what));
//            Log.d("handler study---obj==", (String) msg.obj);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Message msg = Message.obtain();
//        msg.what = 1;
//        msg.obj = "hello handler";
//        mHandler.sendMessage(msg);
//
//        //AsyncTask
//        ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();
//        threadLocal.set(false);
//        threadLocal.get();
//
//        for(;;)
//        {
//            int i=0;
//            if(i==10){
//               return;
//            }
//            i++;
//        }
    }
}
