package com.example.leilascst172s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    EditText idStudent, names, sex, cellphone, dormitory, address, guardian, gphone;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        DB = new DBHelper(this);

        idStudent = findViewById(R.id.idStudent);
        names = findViewById(R.id.names);
        sex = findViewById(R.id.gender);
        cellphone = findViewById(R.id.phoneNumber);
        dormitory = findViewById(R.id.dormitory);
        address = findViewById(R.id.address);
        guardian = findViewById(R.id.guardianName);
        gphone = findViewById(R.id.guardianNumber);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int j = (int) bundle.get("nameId");
            String name = (String) bundle.get("realname");
//            idStudent.setText(j);
            names.setText(name);
        }

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStudentTxt = idStudent.getText().toString();
                String nameTxt = names.getText().toString();
                String sexTxt = sex.getText().toString();
                String cellphoneTxt = cellphone.getText().toString();
                String dormitoryTxt = dormitory.getText().toString();
                String addressTxt = address.getText().toString();
                String guardianTxt = guardian.getText().toString();
                String gphoneTxt = gphone.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(idStudentTxt, nameTxt, sexTxt, cellphoneTxt, dormitoryTxt, addressTxt, guardianTxt, gphoneTxt);
                if (checkupdatedata == true) {
                    Toast.makeText(UpdateActivity.this, "Student details Updated", Toast.LENGTH_SHORT).show();
                    recreate();
                } else {
                    Toast.makeText(UpdateActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}