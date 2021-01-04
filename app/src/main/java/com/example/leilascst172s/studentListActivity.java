package com.example.leilascst172s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class studentListActivity extends AppCompatActivity {
    ListView userList;
    DBHelper DB;
    EditText idStudent, names;
    ImageView imageMore;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        DB = new DBHelper(this);

        listItem = new ArrayList<>();

        viewData();

        imageMore = findViewById(R.id.imageMore);
        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(studentListActivity.this, ListdataActivity.class));
            }
        });
        userList = findViewById(R.id.listview);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                Toast.makeText(studentListActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(studentListActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);
        }
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;//todo
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Cursor res = DB.getdata();
            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);

            TextView idStudent = view1.findViewById(R.id.idStudent);
            idStudent.setText(res.getString(0));
            TextView name = view1.findViewById(R.id.names);
            name.setText(res.getString(1));
            return view1;
        }
    }
}