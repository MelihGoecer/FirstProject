package com.example.mytools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDbHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "student_list.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_STUDENT_LIST = "student_list";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_STUDENT_LIST;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_DATE_BIRTH = "date_birth";
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_NUMBER_SUBJECTS = "number_subjects";
    public static final String COLUMN_AGE = "age";


    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_STUDENT_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    COLUMN_LAST_NAME + " INTEGER NOT NULL, " +
                    COLUMN_DATE_BIRTH + " TEXT NOT NULL, " +
                    COLUMN_CLASS + " INTEGER NOT NULL, " +
                    COLUMN_NUMBER_SUBJECTS + " INTEGER NOT NULL, " +
                    COLUMN_AGE + " INTEGER NOT NULL);";

    StudentDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP);
        onCreate(sqLiteDatabase);
    }

}
