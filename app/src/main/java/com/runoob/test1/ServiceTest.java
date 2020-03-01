package com.runoob.test1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceTest extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        return serviceTestBinder;
    }

    private final ServiceTestBinder serviceTestBinder = new ServiceTestBinder();

    public class ServiceTestBinder extends Binder{

        public ServiceTest getService(){
            return ServiceTest.this;
        }

    }

    public int getCurrentNum(){
        return i;
    }

    @Override
    public void onCreate() {
        System.out.println("Services onCreate");
        startTimer();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("Services onDestroy");
        stopTimer();
        super.onDestroy();
    }

    private int i = 0;

    public void startTimer(){
        if (timer == null){
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    i++;
                    System.out.println(i);

                }
            };

            timer.schedule(task,1000,1000);
        }
    }

    public void stopTimer(){
        if (timer != null){
            timer.cancel();
            task.cancel();

            timer = null;
            task = null;
        }
    }
    private Timer timer = null;
    private TimerTask task = null;
}
