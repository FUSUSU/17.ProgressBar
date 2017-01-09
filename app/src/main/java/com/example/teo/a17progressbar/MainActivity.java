package com.example.teo.a17progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
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
        addListenerOnButtonClick();
    }

    //Hàm bắt sự kiện button xử lý tiến trình download file
    public void addListenerOnButtonClick(){
        //Ánh xạ
        btnStartProgress = (Button) findViewById(R.id.button1);
        //Bắt sự kiện
        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating progress bar dialog
                //Tạo một hộp thoại hiển thị tiến trình đang hoạt động
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                //Tin nhắm hiển thị file downloading.. ra màn hình
                progressBar.setMessage("File downloading...");
                //Cài đặt style ProgressDialog
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //Giá trị của ProgressDialog
                progressBar.setProgress(0);
                //Giá trị lớn nhất của ProgressDialog
                progressBar.setMax(100);
                //Hiển thị tiến trình ProressDialgo
                progressBar.show();
                //reset progressbar and fliesize status
                //Thiết lập lại thanh tiến trình và trạng thái kích thước
                progressBarStatus = 0;
                fileSize = 0;

                //Tạo tiến trình mới
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                      while (progressBarStatus < 100){
                          //performing operation
                          //Hoạt động tiếp diễn
                          progressBarStatus = doOperation();
                          //Cho tiến trình tạm ngủ 1000 mili giây
                          try {
                              Thread.sleep(1000);
                          } catch (InterruptedException e){
                              e.printStackTrace();
                          }
                          //Updating the progress bar
                          //Cập nhật tiến độ thanh
                          progressBarHandler.post(new Runnable() {
                              @Override
                              public void run() {
                                  //Cài đặt ProgressDialog với giá trị progressBarStaturs
                                  progressBar.setProgress(progressBarStatus);
                              }
                          });
                      }
                        //Performing operation if file is downloaded,
                        //Thực hiện hành động nếu file được tải về
                        if (progressBarStatus >= 100){
                            //sleeping for 1 second after operation completed
                            //Ngủ sau 2 giây sau khi hoạt động hoàn thành
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            //Close the progress bar dialog
                            progressBar.dismiss();
                        }
                    }
                }).start();
            }//end of onClick method
        });
    }

    //Checking how much file is downloaded and updating the filesize
    //Kiểm tra có bào nhiêu tệp tin được tải về và cập nhật kích thước.
    public int doOperation(){
        //The range of ProgressDialog starts from 0 to 10000
        //Phạm vi của ProgressDialog bắt đầu từ 0 đến 10000
        while (fileSize<10000){
            fileSize++;
            if (fileSize == 1000){
                return 10;
            } else if (fileSize == 2000){
                return 20;
            } else if (fileSize == 3000){
                return 30;
            } else if (fileSize == 4000){
                return 40;
            } else if (fileSize == 5000){
                return 50;
            } else if (fileSize == 6000){
                return 60;
            } else if (fileSize == 7000){
                return 70;
            } else if (fileSize == 8000){
                return 80;
            } else if (fileSize == 9000){
                return 90;
            } else {
                return 100;
            }
        }//end of while
        return 100;
    }//end of doOperation

    //Menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
