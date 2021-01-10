package com.example.attendance_management_system;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class takeAttendance extends AppCompatActivity {
    String teacher_id;
    String class_selected;
    String sub_selected;
    Spinner period;
    String periodno;
    ArrayList<String> selectedItems;
    ArrayList<String> nonselectedItems;
    Toolbar mToolbar;

    ArrayList<String> ul;
    ListView listView;
    private ArrayAdapter adapter;
    ArrayList Userlist = new ArrayList<>();
    ArrayList Usernames = new ArrayList<>();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        mToolbar = (Toolbar) findViewById(R.id.takeattendancebar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        period = (Spinner) findViewById(R.id.spinner4);

        // ArrayList Userlist;
        selectedItems = new ArrayList<String>();

        TextView classname = (TextView) findViewById(R.id.textView);
        classname.setText("");

        //to get class name from teacherlogin
        Bundle bundle1 = getIntent().getExtras();
        class_selected = bundle1.getString("class_selected");
        sub_selected = bundle1.getString("subject_selected");
        teacher_id = bundle1.getString("tid");
        //  Toast.makeText(getApplicationContext(), teacher_id, Toast.LENGTH_LONG).show();

        classname.setText(class_selected);


        DatabaseReference dbuser = ref.child("Student").child(class_selected);

                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        Userlist.add(dsp.child("usn").getValue().toString());

                    }
                    OnStart(Userlist);
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void OnStart(ArrayList<String> userlist) {
        nonselectedItems = userlist;
        ListView chl = (ListView) findViewById(R.id.checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.checkable_list_layout, R.id.txt_title, userlist);
        chl.setAdapter(aa);
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem);
                else
                    selectedItems.add(selectedItem);

            }

        });


    }

    public void showSelectedItems(View view) {
        String selItems = "";
        periodno = period.getSelectedItem().toString();
        if (periodno.equals("Select Period")) {
            Toast.makeText(this, "Select a class", Toast.LENGTH_LONG).show();

        } else {
            ref = FirebaseDatabase.getInstance().getReference();

            dbAttendance = ref.child("attendance").child(date).child(class_selected);

            for (String item : selectedItems) {
                Toast.makeText(this, "Attendance created Successfully", Toast.LENGTH_SHORT).show();
                nonselectedItems.remove(item);
                dbAttendance.child(item).child(periodno).setValue("P" + " / " + sub_selected);
                if (selItems == "")
                    selItems = item;
                else
                    selItems += "/" + item;
            }
            // Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();


            //for making absent
            for (String item : nonselectedItems) {
                Toast.makeText(this, "Attendance created Successfully", Toast.LENGTH_SHORT).show();
                dbAttendance.child(item).child(periodno).setValue("A" + " / " + sub_selected);
                //Toast.makeText(this, "absentees:" + nonselectedItems, Toast.LENGTH_LONG).show();

            }
        }


    }
}

