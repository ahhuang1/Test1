package com.runoob.test1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentAddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_add);
    }

    public void onClickAddName(View view){
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.editText1)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI,values);

        Toast.makeText(getBaseContext(),
                uri.toString() + "添加成功",Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view){
        //Retrieve student records
        String URL = "content://com.example.provider.College/students";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students,null,null,null,"name");

        if(c.moveToFirst()){
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                                "," + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_LONG).show();
            }while (c.moveToNext());
        }
    }
}
