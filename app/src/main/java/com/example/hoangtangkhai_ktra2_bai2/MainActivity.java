package com.example.hoangtangkhai_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoangtangkhai_ktra2_bai2.model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView revView;
    private RecycleViewAdapter adapter;
    private FloatingActionButton fabAdd;
    private SQLiteHepler sqlHelper;
    private TextView tong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter = new RecycleViewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        revView.setLayoutManager(manager);
        revView.setAdapter(adapter);

        sqlHelper = new SQLiteHepler(this);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentAdd);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.mSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Course> list = sqlHelper.getByName(newText);
                adapter.setListCourse(list);
                revView.setAdapter(adapter);
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        List<Course> list =  sqlHelper.getAll();
        adapter.setListCourse(list);
        revView.setAdapter(adapter);
        super.onResume();
    }

    private void init() {
        revView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fltAdd);
        tong = findViewById(R.id.tong);
    }
}