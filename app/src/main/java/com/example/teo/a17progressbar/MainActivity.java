package com.example.teo.a17progressbar;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //khai báo Button Bắt đầu tiến trình
    Button btnStartProgress;
    //Khai báo thanh tiến trình trình
    ProgressDialog progressBar;
    //Khai báo biến tiến trình đang bao nhiêu?
    private int progressBarStatus = 0;
    //Biến xử lý.
    private Handler progressBarHandler = new Handler();
        //Biến lưu kích thước của file
        private long fileSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
