package com.example.hoangtangkhai_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoangtangkhai_ktra2_bai2.model.Course;

import java.util.Calendar;

public class Remove_UpdateActivity extends AppCompatActivity {
    private TextView tvId, tvDate;
    private EditText edtName;
    private Spinner spNganh;
    private CheckBox checkBox;
    private Button btnGetDate, btnUpdate, btnDelete, btnBack;
    private SQLiteHepler sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__update);
        sqLiteHelper = new SQLiteHepler(this);
        init();
        Intent i = getIntent();
        Course o = (Course) i.getSerializableExtra("course");
        tvId.setText(o.getId()+"");
        tvDate.setText(o.getDate());
        edtName.setText(o.getName());
        switch (o.getNganh()){
            case "English":
                spNganh.setSelection(0);
                break;
            case "CNTT":
                spNganh.setSelection(1);
                break;
            case "Kinh tế":
                spNganh.setSelection(2);
                break;
            case "Truyền thông":
                spNganh.setSelection(3);
                break;
            default:
                spNganh.setSelection(0);
        }
        int j = o.getKichhoat();
        if (j == 1){
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int d = c.get(Calendar.DAY_OF_MONTH);
                int m = c.get(Calendar.MONTH);
                int y = c.get(Calendar.YEAR);
                DatePickerDialog dpd = new DatePickerDialog(Remove_UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDate.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },y,m,d);
                dpd.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Remove_UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course o = new Course();
                o.setId(Integer.parseInt(tvId.getText().toString()));
                o.setName(edtName.getText().toString());
                o.setDate(tvDate.getText().toString());
                o.setNganh(spNganh.getSelectedItem().toString());
                if(checkBox.isChecked()){
                    o.setKichhoat(1);
                }else{
                    o.setKichhoat(0);
                }
                sqLiteHelper.updateCourse(o);
                Intent i = new Intent(Remove_UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = checkBox.isChecked();
                if(check){
                    Toast.makeText(getApplicationContext(), "Không thể xoá course đang kích hoạt", Toast.LENGTH_LONG).show();
                }else{
                    int id = Integer.parseInt(tvId.getText().toString());
                    sqLiteHelper.deleteCourse(id);
                    Intent i = new Intent(Remove_UpdateActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void init() {
        tvId = findViewById(R.id.tvIdRemoveUpdate);
        tvDate = findViewById(R.id.edtDateRemoveUpdate);
        edtName = findViewById(R.id.edtNameRemoveUpdate);
        spNganh = findViewById(R.id.spinNganhEdit);
        checkBox = findViewById(R.id.edtKichHoat);
        btnGetDate = findViewById(R.id.btnGetDateRemoveUpdate);
        btnUpdate = findViewById(R.id.btnUpdateRemoveUpdate);
        btnDelete = findViewById(R.id.btnRemoveRemoveUpdate);
        btnBack = findViewById(R.id.btnBackRemoveUpdate);
    }
}