package com.my.ndkdynamicdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showText = (TextView) findViewById(R.id.textBloardId);
    }

    public void click1(View view) {
        String result = getHello();
        showText.setText(result);
    }

    public void click2(View view) {
        int result = meaningOfTheUniverse();
        showText.setText("返回值："+result);
    }

    public native String stringFromJNI();

    //动态注册
    public native String getHello();

    //动态注册
    public native int meaningOfTheUniverse();

}
