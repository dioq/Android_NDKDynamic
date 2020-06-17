package com.my.ndkdynamicdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.my.ndkdynamicdemo.util.MyUtil;

public class MainActivity extends AppCompatActivity {

    private TextView showText;
    private MyUtil myUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showText = findViewById(R.id.textBloardId);
        myUtil = new MyUtil();
    }

    public void click1(View view) {
        String result = myUtil.getHello();
        showText.setText(result);
    }

    public void click2(View view) {
        int result = myUtil.meaningOfTheUniverse();
        showText.setText("返回值：" + result);
    }

}
