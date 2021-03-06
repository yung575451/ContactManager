package com.hungphamcom.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.hungphamcom.contactmanager.adapter.RecycleViewAdapter;
import com.hungphamcom.contactmanager.data.DatabaseHandler;
import com.hungphamcom.contactmanager.model.contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private ArrayList<contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleview);
        contactArrayList=new ArrayList<>();
        DatabaseHandler db=new DatabaseHandler(MainActivity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<contact>contactList=db.getAllContact();
        for(contact contact:contactList){
            Log.d("MainActivity", "onCreate: "+contact.getName());
            contactArrayList.add(contact);

        }

        //setup adapter
        recycleViewAdapter=new RecycleViewAdapter(MainActivity.this,contactArrayList);

        recyclerView.setAdapter(recycleViewAdapter);



    }
}