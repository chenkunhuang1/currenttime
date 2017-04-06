package com.example.chen.currenttime;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mget_time,mstart_time,mend_start;
    private EditText minput_time;
    private TextView mtv;
    private Timer timer = null;
    private TimerTask task = null;
    private int t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        mget_time = (Button) findViewById(R.id.get_time);
        mstart_time = (Button) findViewById(R.id.start_time);
        mend_start = (Button) findViewById(R.id.end_time);
        minput_time = (EditText) findViewById(R.id.et_input);
        mtv = (TextView) findViewById(R.id.tv);
        mget_time.setOnClickListener(this);
        mstart_time.setOnClickListener(this);
        mend_start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_time:
                t = Integer.parseInt(minput_time.getText().toString().trim());
                mtv.setText(minput_time.getText().toString());

                break;
            case R.id.start_time:
                startTime();
                break;
            case R.id.end_time:
                endTime();
                break;

        }

    }

    private void endTime() {
        timer.cancel();
    }

    private void startTime() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                t--;
                Message message = mHandler.obtainMessage();
                message.arg1 = t;
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(task,1000);

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mtv.setText(msg.arg1+"");
            startTime();

        }
    };

}
