package com.example.mytools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDataSource {

    private SQLiteDatabase database;
    private StudentDbHelper dbHelper;

    private String[] columns = {
            StudentDbHelper.COLUMN_ID,
            StudentDbHelper.COLUMN_FIRST_NAME,
            StudentDbHelper.COLUMN_LAST_NAME,
            StudentDbHelper.COLUMN_DATE_BIRTH,
            StudentDbHelper.COLUMN_CLASS,
            StudentDbHelper.COLUMN_NUMBER_SUBJECTS,
            StudentDbHelper.COLUMN_AGE
    };

    public StudentDataSource(Context context) {
        dbHelper = new StudentDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student createStudent(String pFirstName, String pLastName, String pDateOfBirth, int pClass, int pNumberSubjects, int pAge) {
        ContentValues values = new ContentValues();
        values.put(StudentDbHelper.COLUMN_FIRST_NAME, pFirstName);
        values.put(StudentDbHelper.COLUMN_LAST_NAME, pLastName);
        values.put(StudentDbHelper.COLUMN_DATE_BIRTH, pDateOfBirth);
        values.put(StudentDbHelper.COLUMN_CLASS, pClass);
        values.put(StudentDbHelper.COLUMN_NUMBER_SUBJECTS, pNumberSubjects);
        values.put(StudentDbHelper.COLUMN_AGE, pAge);

        long insertId = database.insert(StudentDbHelper.TABLE_STUDENT_LIST, null, values);

        Cursor cursor = database.query(StudentDbHelper.TABLE_STUDENT_LIST,
                columns,StudentDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Student student = cursorToStudent(cursor);
        cursor.close();

        return student;
    }

    private Student cursorToStudent(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(StudentDbHelper.COLUMN_ID);
        int idFirstName = cursor.getColumnIndex(StudentDbHelper.COLUMN_FIRST_NAME);
        int idLastName = cursor.getColumnIndex(StudentDbHelper.COLUMN_LAST_NAME);
        int idDateBirth = cursor.getColumnIndex(StudentDbHelper.COLUMN_DATE_BIRTH);
        int idClass = cursor.getColumnIndex(StudentDbHelper.COLUMN_CLASS);
        int idNumberSubjects = cursor.getColumnIndex(StudentDbHelper.COLUMN_NUMBER_SUBJECTS);
        int idAge = cursor.getColumnIndex(StudentDbHelper.COLUMN_AGE);

        String fName = cursor.getString(idFirstName);
        String lName = cursor.getString(idLastName);
        String dateBirth = cursor.getString(idDateBirth);
        int nClass = cursor.getInt(idClass);
        int nSubjects = cursor.getInt(idNumberSubjects);
        int age = cursor.getInt(idAge);
        long id = cursor.getLong(idIndex);

        Student student = new Student(fName, lName, dateBirth, (byte)nClass, (byte)nSubjects, (byte)age, id);
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        Cursor cursor = database.query(StudentDbHelper.TABLE_STUDENT_LIST, columns,null,null,null,null,null);

        cursor.moveToFirst();
        Student student;

        while(!cursor.isAfterLast()){
            student = cursorToStudent(cursor);
            studentList.add(student);
            cursor.moveToNext();
        }

        cursor.close();

        return studentList;
    }

}


