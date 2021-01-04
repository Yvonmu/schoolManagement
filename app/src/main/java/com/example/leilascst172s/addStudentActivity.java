package com.example.leilascst172s;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class addStudentActivity extends AppCompatActivity {

    EditText idStudent,names,sex,cellphone,dormitory,address,guardian,gphone;
    Button save;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        
        DB = new DBHelper(this);

        idStudent = findViewById(R.id.idStudent);
        names = findViewById(R.id.names);
        sex = findViewById(R.id.gender);
        cellphone = findViewById(R.id.phoneNumber);
        dormitory = findViewById(R.id.dormitory);
        address = findViewById(R.id.address);
        guardian = findViewById(R.id.guardianName);
        gphone = findViewById(R.id.guardianNumber);
        
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(idStudent.getText().toString())|| TextUtils.isEmpty(names.getText().toString() )||
                        TextUtils.isEmpty(sex.getText().toString())||TextUtils.isEmpty(cellphone.getText().toString()) ||TextUtils.isEmpty(dormitory.getText().toString())
                        ||TextUtils.isEmpty(address.getText().toString()) ||TextUtils.isEmpty(guardian.getText().toString()) ||TextUtils.isEmpty(gphone.getText().toString())){
                    Toast.makeText(addStudentActivity.this, "Please all information is required", Toast.LENGTH_LONG).show();
                }else {
                    String idStudentTxt = idStudent.getText().toString();
                    String nameTxt = names.getText().toString();
                    String sexTxt = sex.getText().toString();
                    String cellphoneTxt = cellphone.getText().toString();
                    String dormitoryTxt = dormitory.getText().toString();
                    String addressTxt = address.getText().toString();
                    String guardianTxt = guardian.getText().toString();
                    String gphoneTxt = gphone.getText().toString();

                    Boolean checkinsertdata = DB.insertuserdata(idStudentTxt, nameTxt, sexTxt, cellphoneTxt, dormitoryTxt, addressTxt, guardianTxt, gphoneTxt);
                    if (checkinsertdata == true) {
                        Toast.makeText(addStudentActivity.this, "New Student Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(addStudentActivity.this, "No Student Inserted PLEASE CHECK IF ID AND REAL NAME IS NOT INSERTED", Toast.LENGTH_SHORT).show();
                    }
                }

                String s = names.getText().toString();
                Intent intent = new Intent(addStudentActivity.this,TestActivity.class);
                intent.putExtra("StudentId",s);
//                startActivity(intent);
            }
        });

        //update
//        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String idStudentTxt = idStudent.getText().toString();
//                String nameTxt = names.getText().toString();
//                String sexTxt = sex.getText().toString();
//                String cellphoneTxt = cellphone.getText().toString();
//                String dormitoryTxt = dormitory.getText().toString();
//                String addressTxt = address.getText().toString();
//                String guardianTxt = guardian.getText().toString();
//                String gphoneTxt = gphone.getText().toString();
//
//                Boolean checkupdatedata = DB.updateuserdata(idStudentTxt, nameTxt, sexTxt, cellphoneTxt, dormitoryTxt, addressTxt, guardianTxt, gphoneTxt);
//                if (checkupdatedata == true){
//                    Toast.makeText(addStudentActivity.this, "Student details Updated", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(addStudentActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        //delete
//        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String idStudentTxt = idStudent.getText().toString();
//                Boolean checkdeletedata = DB.deletedata(idStudentTxt);
//                if (checkdeletedata == true){
//                    Toast.makeText(addStudentActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(addStudentActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        //view
//        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}