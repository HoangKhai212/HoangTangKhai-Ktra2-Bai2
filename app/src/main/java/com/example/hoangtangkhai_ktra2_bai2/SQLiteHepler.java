package com.example.hoangtangkhai_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hoangtangkhai_ktra2_bai2.model.Course;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHepler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="course.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHepler(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE course (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "date TEXT," +
                "nganh TEXT," +
                "kichhoat INTEGER)";
        db.execSQL(sql);
    }

    public long addCourse(Course course){
        ContentValues v = new ContentValues();
        v.put("name", course.getName());
        v.put("date",course.getDate());
        v.put("nganh",course.getNganh());
        v.put("kichhoat",course.getKichhoat());
        SQLiteDatabase sld = getWritableDatabase();
        return sld.insert("course",null,v);
    }

    public List<Course> getAll(){
        List<Course> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor c = statement.query("course",null,null,null,null,null,null);
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String date = c.getString(2);
            String nganh = c.getString(3);
            int kichhoat = c.getInt(4);
            list.add(new Course(id,name,date,nganh,kichhoat));
        }
        return list;
    }

    public int updateCourse(Course course){
        ContentValues v = new ContentValues();
        v.put("name",course.getName());
        v.put("date",course.getDate());
        v.put("nganh", course.getNganh());
        v.put("kichhoat", course.getKichhoat());
        SQLiteDatabase slq = getWritableDatabase();
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(course.getId())};
        return slq.update("course",v,wClause,wArgs);
    }

    public int deleteCourse(int id){
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(id)};
        SQLiteDatabase sql = getWritableDatabase();
        return sql.delete("course",wClause,wArgs);
    }

    public List<Course> getByName(String n){
        String sql = "name like ?";
        String[] args = {"%" + n + "%"};
        SQLiteDatabase slq = getReadableDatabase();
        Cursor c = slq.query("course",null, sql, args, null, null, null);
        List<Course> list = new ArrayList<>();
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String date = c.getString(2);
            String nganh= c.getString(3);
            int kichhoat = c.getInt(4);
            list.add(new Course(id,name,date,nganh,kichhoat));
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

