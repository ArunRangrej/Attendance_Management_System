package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.attendance_management_system.DB_Helper.faculty_connectivity;
import com.example.attendance_management_system.DB_Connectivity.DB;

public class view_faculty extends AppCompatActivity {

    ArrayList<faculty_connectivity> facultyBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    DB dbAdapter = new DB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> facultyList = new ArrayList<String>();

        facultyBeanList=dbAdapter.getAllFaculty();

        for(faculty_connectivity facultyBean : facultyBeanList)
        {
            String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();

            facultyList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_view_faculty, R.id.labelF, facultyList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view_faculty.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        facultyList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
                        facultyBeanList=dbAdapter.getAllFaculty();

                        for(faculty_connectivity facultyBean : facultyBeanList)
                        {
                            String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
                            facultyList.add(users);
                            Log.d("users: ", users);

                        }

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();





                return false;
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    }
