package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LecturesDatabase extends SQLiteOpenHelper {

    public LecturesDatabase(Context context) {
        //Подключаемся к базе данных
        super(context, "Ilmhona.Courses", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cоздаём таблицу
        db.execSQL("CREATE TABLE lectures (title text, published text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Уничтожаем таблицу
        db.execSQL("DROP TABLE IF EXISTS lectures");
        //Создаём заново
        onCreate(db);
    }

    public void wipeLectures() {
        //Подключаем переменную с БД
        SQLiteDatabase db = this.getWritableDatabase();
        //Отправляем SQL для удаления данных
        db.execSQL("DELETE FROM lectures");
    }

    public void insertLecture(String title, String published) {
        //Подключаем переменную с БД
        SQLiteDatabase db = this.getWritableDatabase();
        //Отправляем SQL для добавления данных
        db.execSQL("INSERT INTO lectures VALUES('" + title + "', '" + published + "')");
    }

    public ArrayList<Lecture> getLectures() {
        ArrayList<Lecture> lecturesArray = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM lectures", null);
        result.moveToFirst();
        Integer i = 0;
        while (i < result.getCount()) {
            Lecture lecture = new Lecture();
            lecture.title = result.getString(0);
            lecture.published = result.getString(1);
            lecturesArray.add(lecture);
            i = i + 1;
            result.moveToNext();
        }
        return lecturesArray;
    }

}
