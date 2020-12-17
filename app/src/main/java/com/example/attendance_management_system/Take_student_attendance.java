package com.example.attendance_management_system;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Take_student_attendance extends AppCompatActivity {


    ArrayList Userlist = new ArrayList<>();
    ArrayList Usernames = new ArrayList<>();
    ArrayList<String> Present_students = new ArrayList<>();
    ArrayList<String> Absent_students = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_student_attendance);
        Bundle bundle1 = getIntent().getExtras();
        String teacher_id  = bundle1.getString("message");
        DatabaseReference dbuser = ref.child("Student");

        Toast.makeText(getApplicationContext(), teacher_id, Toast.LENGTH_LONG).show();
        dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Userlist.add(dsp.child("usn").getValue().toString());
                    Usernames.add(dsp.child("firstname").getValue().toString()+" "+dsp.child("lastname").getValue().toString());

                }
                OnStart(Userlist);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });

    }
    public void OnStart(ArrayList<String> userlist) {
        Absent_students = userlist;
        ListView chl = (ListView) findViewById(R.id.checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.checkable_list_layout, R.id.txt_title, userlist);
        chl.setAdapter(aa);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                System.out.println("Start Test" + selectedItem);
                if (Present_students.contains(selectedItem))
                    Present_students.remove(selectedItem);
                else
                    Present_students.add(selectedItem);
            }
        });

    }
    public void showSelectedItems(View view) {

            ref = FirebaseDatabase.getInstance().getReference();

            dbAttendance = ref.child("Attendance").child(date);

            for (String item : Present_students) {
                Absent_students.remove(item);
                dbAttendance.child(item).setValue("P");

            }
            // Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();


            //for making absent
            for (String item : Absent_students) {
                dbAttendance.child(item).setValue("A");
            }


        Toast.makeText(this, "Attendance created Successfully", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

