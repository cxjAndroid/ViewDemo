package com.example.chenxujun.drawviewdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private DemoView demo_view;
    private MyTask   myTask;
    private Button   btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        demo_view = (DemoView) findViewById(R.id.demo_view);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTask != null && myTask.getStatus() == AsyncTask.Status.RUNNING) {
                    myTask.cancel(true);
                    demo_view.setProgress(0);
                }
            }
        });

       /*  final MyHandler myHandler = new MyHandler(this);
       new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 361; i++) {
                    try {
                        Thread.sleep(10);
                        Message msg = Message.obtain();
                        msg.arg1 = i;
                        myHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();*/
        myTask = new MyTask();
        myTask.execute();
    }


    /*static class MyHandler extends Handler {

        private WeakReference<MainActivity> weakReference;
        private final MainActivity act;

        public MyHandler(MainActivity activity) {
            weakReference = new WeakReference<>(activity);
            act = weakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            act.demo_view.setProgress(msg.arg1);
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myTask != null && myTask.getStatus() == AsyncTask.Status.RUNNING) {
            myTask.cancel(true);
            demo_view.setProgress(0);
        }
    }


    class MyTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 361; i++) {
                if (isCancelled()) {
                    break;
                }
                try {
                    Thread.sleep(20);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()) {
                return;
            }
            demo_view.setProgress(values[0]);
        }
    }

}
