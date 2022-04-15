package com.hungphamcom.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hungphamcom.contactmanager.model.contact;
import com.hungphamcom.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - Structured Query Language
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again
        onCreate(sqLiteDatabase);

    }

    /*
    CRUD=Create,Read,Update,Delete
     */
    //Add Contact
    public void addContact(contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert to row
        sqLiteDatabase.insert(Util.TABLE_NAME, null, values);
        Log.d("DBHandler", "addContact: " + "item added");
        sqLiteDatabase.close();//Closing db connection!
    }

    //Get a contact
    public contact getcontact(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        contact contact = new contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;

    }

    //Get all Contacts
    public List<contact> getAllContact() {
        List<contact> contactList = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAll, null);

        //loop through our data
        if (cursor.moveToFirst()) {
            do {
                contact contact = new contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact Object to out list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    //Update contact
    public int updateContact(contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());

        //update the row
        //update(tableName, values,where id=43)
        return sqLiteDatabase.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Delete single contact
    public void deleteContact(contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});

        sqLiteDatabase.close();
    }

    //get contacts count
    public int getCount(){
        String countQuery="SELECT * FROM "+Util.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(countQuery,null);

        return cursor.getCount();
    }
}
