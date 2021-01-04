package com.example.leilascst172s;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        findViewById(R.id.studentList).setOnClickListener(this);
        findViewById(R.id.studentDetail).setOnClickListener(this);
        findViewById(R.id.addButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.studentList:
                startActivity(new Intent(this,TestActivity.class));
                break;
            case R.id.studentDetail:
                studentDetails();
                break;
            case R.id.addButton:
                startActivity(new Intent(this,addStudentActivity.class));
                break;
        }
    }

    private void studentDetails() {
        Cursor res = DB.getdata();
        if (res .getCount() == 0){
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Student’s id: " + res.getString(0) + "\n");
            buffer.append("Real Name: " + res.getString(1) + "\n");
            buffer.append("Sexual information: " + res.getString(2) + "\n");
            buffer.append("Cellphone number: " + res.getString(3) + "\n");
            buffer.append("Dormitory Address: " + res.getString(4) + "\n");
            buffer.append("Family address : " + res.getString(5) + "\n");
            buffer.append("Guardian name: " + res.getString(6) + "\n");
            buffer.append("number of guardian: " + res.getString(7) + "\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}