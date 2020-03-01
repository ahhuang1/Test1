package com.runoob.test1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {

    private Button btnStartAty1;
    private TextView tvOut;
    private Button btnStartService,btnStopService,btnBindService,btnUnbindService,btnGetCurrnetNum;
    private Intent serviceIntent;
    private ServiceTest serviceTest = null;
    private Button btnSendBroadcast,btnRegBCR,btnUnregBCR;
    private Button btnAddStudents;

    private final MyBC mybc = new MyBC();
    private Intent si = new Intent(MyBC.ACTION);

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStartAty1 = findViewById(R.id.btnStartAty1);
        btnStartAty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Aty1.class);
//                i.putExtra("txt","hello acty1");
                Bundle data = new Bundle();
                data.putString("txt","hello acty1");
                i.putExtras(data);

//                startActivity(i);
                startActivityForResult(i,0);
            }
        });


        serviceIntent = new Intent(this,ServiceTest.class);
        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        btnBindService = findViewById(R.id.btnBindService);
        btnUnbindService = findViewById(R.id.btnUnbindService);
        btnGetCurrnetNum = findViewById(R.id.btnGetCurrentNum);
        btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnRegBCR = findViewById(R.id.btnRegBCR);
        btnUnregBCR = findViewById(R.id.btnUnregBCR);
        btnAddStudents = findViewById(R.id.btnAddStudents);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetCurrnetNum.setOnClickListener(this);
        btnSendBroadcast.setOnClickListener(this);
        btnRegBCR.setOnClickListener(this);
        btnUnregBCR.setOnClickListener(this);
        btnAddStudents.setOnClickListener(this);

        System.out.println("onCreate");

//        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getStringExtra("result");
        tvOut = findViewById(R.id.tvOut);
        tvOut.setText(result);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                serviceTest = null;
                break;
            case R.id.btnGetCurrentNum:
                if (serviceTest!=null){
                    System.out.println("当前服务的数字："+ serviceTest.getCurrentNum());
                }
                break;
            case R.id.btnSendBroadcast:
//                Intent si = new Intent(this,MyBC.class);
                si.putExtra("broadkey","hello broadcast");
                sendBroadcast(si);
                break;
            case R.id.btnRegBCR:
                registerReceiver(mybc,new IntentFilter(MyBC.ACTION));
                break;
            case R.id.btnUnregBCR:
                unregisterReceiver(mybc);
                break;
            case R.id.btnAddStudents:
                Intent ai = new Intent(this,StudentAddActivity.class);
                startActivity(ai);
                break;
            default:
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        System.out.println("onServiceConnected");
        serviceTest = ((ServiceTest.ServiceTestBinder)iBinder).getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        System.out.println("onServiceDisconnected");

    }
}
