package com.example.leilascst172s;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    DBHelper DB;
    private ListView mListView;
    EditText idStudent;
    ArrayList<String> listData = new ArrayList<>();
    private String myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DB = new DBHelper(this);
        mListView = (ListView) findViewById(R.id.listView);
        idStudent = findViewById(R.id.idStudent);

        populateListView();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_view) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateListView() {
        Cursor cursor = DB.getdata();

        while (cursor.moveToNext()) {
            listData.add(cursor.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "ONITEM CLICK:" + name);

                Cursor data = DB.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "on item click" + itemID);
                    Intent editScreenIntent = new Intent(TestActivity.this, UpdateActivity.class);
                    editScreenIntent.putExtra("nameId", itemID);
                    editScreenIntent.putExtra("realname", name);
                    startActivity(editScreenIntent);

                } else {
                    Toast.makeText(TestActivity.this, "no id associated with that name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerForContextMenu(mListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView
                .setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ArrayList<String> usersList = new ArrayList<>();
                        for (String user : listData) {
                            if (user.toLowerCase().contains(newText.toLowerCase())) {
                                usersList.add(user);
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestActivity.this,
                                android.R.layout.simple_list_item_1, usersList);
                        mListView.setAdapter(adapter);
                        return true;
                    }
                });
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.setHeaderTitle("Select Action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.deleteAction) {
            final EditText txtUrl = new EditText(TestActivity.this);
            txtUrl.setInputType(InputType.TYPE_CLASS_TEXT);

            new AlertDialog.Builder(TestActivity.this)
                    .setIcon(android.R.drawable.ic_delete)
                    .setTitle("Are you sure?")
                    .setMessage("Insert Student Name")
                    .setView(txtUrl)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            Bundle bundle = intent.getExtras();
                            if (bundle != null) {
                                String j = (String) bundle.get("StudentId");
                                txtUrl.setText(j);
                            }

                            String idStudentTxt = txtUrl.getText().toString();
                            Boolean checkdeletedata = DB.deletedata(idStudentTxt);
                            if (checkdeletedata == true) {
                                Toast.makeText(TestActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                                recreate();
                            } else {
                                Toast.makeText(TestActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();

            return true;
        } else if (item.getItemId() == R.id.updateAction) {
            startActivity(new Intent(TestActivity.this, UpdateActivity.class));

        } else {
            return false;
        }

        return true;
    }

}