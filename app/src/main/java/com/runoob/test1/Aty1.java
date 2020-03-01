package com.runoob.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Aty1 extends Activity {

    private Button btnClose;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty1);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("result","hello world txt");

                setResult(0,i);
                finish();
            }
        });

        tvOut = findViewById(R.id.tvOut);

        Bundle data = getIntent().getExtras();
        String txt = data.getString("txt");

        tvOut.setText(txt);
//        tvOut.setText(getIntent().getStringExtra("txt"));

    }
}
