package com.hungphamcom.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private TextView detsName,detsPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detsName=findViewById(R.id.dets_Name);
        detsPhone=findViewById(R.id.dets_phone);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            String name=bundle.getString("name");
            String phone= bundle.getString("number");

            detsName.setText(name);
            detsPhone.setText(phone);
        }
    }
}