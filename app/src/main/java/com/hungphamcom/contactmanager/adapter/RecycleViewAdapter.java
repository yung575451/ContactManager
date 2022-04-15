package com.hungphamcom.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hungphamcom.contactmanager.DetailsActivity;
import com.hungphamcom.contactmanager.R;
import com.hungphamcom.contactmanager.model.contact;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context context;
    private List<contact> contactList;

    public RecycleViewAdapter(Context context, List<contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        contact contact=contactList.get(position); // each contact object inside the list
        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);

            contactName=itemView.findViewById(R.id.name);
            phoneNumber=itemView.findViewById(R.id.phone_number);
            iconButton=itemView.findViewById(R.id.iconButton);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position= getAdapterPosition();
            contact contact=contactList.get(position);

            Intent intent=new Intent(context, DetailsActivity.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("number",contact.getPhoneNumber());

            context.startActivity(intent);

//            switch ((view.getId())){
//                case R.id.iconButton:
//                    Log.d("Iconclicked", "onClick: "+contact.getName());
//                    break;
//            }
//
//            Log.d("clicked", "onClick: "+contact.getName());

        }
    }
}
