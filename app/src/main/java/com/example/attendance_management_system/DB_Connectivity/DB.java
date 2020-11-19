package com.example.attendance_management_system.DB_Connectivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.attendance_management_system.DB_Helper.faculty_connectivity;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "MSRIT_Attendance";

    private static final String FACULTY_TABLE = "faculty_table";

    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_FIRSTNAME = "faculty_firstname";
    private static final String FACULTY_LASTNAME = "faculty_Lastname";
    private static final String FACULTY_MO_NO = "faculty_mobilenumber";
    private static final String FACULTY_MAIL = "faculty_address";
    private static final String FACULTY_USERNAME = "faculty_username";
    private static final String FACULTY_PASSWORD = "faculty_password";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String queryFaculty = "CREATE TABLE " + FACULTY_TABLE + " (" +
                FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FACULTY_FIRSTNAME + " TEXT, " +
                FACULTY_LASTNAME + " TEXT, " +
                FACULTY_MO_NO + " TEXT, " +
                FACULTY_MAIL + " TEXT," +
                FACULTY_USERNAME + " TEXT," +
                FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty", queryFaculty);

        try {
            db.execSQL(queryFaculty);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }

    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String queryFaculty="CREATE TABLE "+ FACULTY_TABLE +" (" +
                FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FACULTY_FIRSTNAME + " TEXT, " +
                FACULTY_LASTNAME + " TEXT, " +
                FACULTY_MO_NO + " TEXT, " +
                FACULTY_MAIL + " TEXT," +
                FACULTY_USERNAME + " TEXT," +
                FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty",queryFaculty);
        try
        {
            db.execSQL(queryFaculty);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }
    public void addFaculty(faculty_connectivity faculty) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO faculty_table (faculty_firstname,faculty_Lastname,faculty_mobilenumber,faculty_address,faculty_username,faculty_password) values ('"+
                faculty.getFaculty_firstname()+"', '"+
                faculty.getFaculty_lastname()+"', '"+
                faculty.getFaculty_mobilenumber()+"', '"+
                faculty.getFaculty_mail()+"', '"+
                faculty.getFaculty_username()+"', '"+
                faculty.getFaculty_password()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
    public faculty_connectivity validateFaculty(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM faculty_table where faculty_username='"+userName+"' and faculty_password='"+password+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {

            faculty_connectivity facultyBean = new faculty_connectivity();
            facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
            facultyBean.setFaculty_firstname(cursor.getString(1));
            facultyBean.setFaculty_lastname(cursor.getString(2));
            facultyBean.setFaculty_mobilenumber(cursor.getString(3));
            facultyBean.setFaculty_mail(cursor.getString(4));
            facultyBean.setFaculty_username(cursor.getString(5));
            facultyBean.setFaculty_password(cursor.getString(6));
            return facultyBean;
        }
        return null;
    }
    public ArrayList<faculty_connectivity> getAllFaculty()
    {
        Log.d("in get all","in get all" );
        ArrayList<faculty_connectivity> list = new ArrayList<faculty_connectivity>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM faculty_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                faculty_connectivity facultyBean = new faculty_connectivity();
                facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                facultyBean.setFaculty_firstname(cursor.getString(1));
                facultyBean.setFaculty_lastname(cursor.getString(2));
                facultyBean.setFaculty_mobilenumber(cursor.getString(3));
                facultyBean.setFaculty_mail(cursor.getString(4));
                facultyBean.setFaculty_username(cursor.getString(5));
                facultyBean.setFaculty_password(cursor.getString(6));
                list.add(facultyBean);

            }while(cursor.moveToNext());
        }
        return list;
    }
    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE faculty_id="+facultyId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
}


