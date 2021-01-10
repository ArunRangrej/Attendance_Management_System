package com.example.attendance_management_system;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class teacher_attendanceSheet extends AppCompatActivity {
    ListView listView;
    String teacher_id,class_selected;
   Toolbar mToolbar;
    String sub_selected;
    EditText date;
    ArrayList Userlist = new ArrayList<>();
    ArrayList Studentlist = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    DatabaseReference dbStudent;
    String required_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance_sheet);
        listView = (ListView) findViewById(R.id.list);
        date = (EditText) findViewById(R.id.date);
        mToolbar=(Toolbar)findViewById(R.id.takeattendancebar);
        setSupportActionBar(mToolbar);
        Bundle bundle1 = getIntent().getExtras();
        class_selected = bundle1.getString("class_selected");
        sub_selected = bundle1.getString("subject_selected");
        teacher_id = bundle1.getString("tid");

    }

    public void viewlist(View v) {

        Userlist.clear();
        DatabaseReference dbuser = ref.child("Student").child(class_selected);

        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Userlist.add(dsp.child("usn").getValue().toString());

                }
                display_list(Userlist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void display_list(final ArrayList userlist) {

        Studentlist.clear();
        required_date = date.getText().toString();
        dbAttendance = ref.child("attendance");
        Studentlist.add("          USN               "+"Status" + "  period");
        for (Object usn : userlist) {
            System.out.println(usn);
            dbAttendance.child(required_date).child(class_selected).child(usn.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                        String p1 = dsp.getValue().toString();
                        System.out.println(p1);
                        if((p1.equals("A / "+sub_selected))||(p1.equals("P / "+sub_selected))){
                            Studentlist.add(dataSnapshot.getKey().toString() + "            " + p1.substring(0,1) +"        "+dsp.getKey());
                        }
                    }
                    list(Studentlist);

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }

            });


        }

    }
    public void list(ArrayList studentlist){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
