package com.runoob.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBC extends BroadcastReceiver {

    public static final String ACTION = "com.runoob.test1.intent.action.MyBC";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        System.out.println("收到数据："+intent.getStringExtra("broadkey"));

    }
}
