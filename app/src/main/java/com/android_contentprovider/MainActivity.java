package com.android_contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     https://www.youtube.com/watch?v=Zjwwhp-YXGk
     Đọc danh bạ điện thoại trong android content Provider : Nam Nguyen - Thầy giáo dạy FPT 

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void getContactList(View view){
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor    = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);

        while ( cursor != null && cursor.moveToNext() ) {
            int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);  //
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);  //
            int hasPhone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);  //
          String strID = cursor.getString(idIndex);
          String strName = cursor.getString(nameIndex);

          if(cursor.getInt(hasPhone) > 0) {
              Cursor pCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{strID}, null);

                      while (pCur.moveToNext()){
                          int numberPhone = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                          String strPhone = pCur.getString(numberPhone);
                          Log.i("GET_CONTRACT", "ID:" + strID);
                          Log.i("GET_CONTRACT", "ID:" + strName);
                          Log.i("GET_CONTRACT", "ID:" + strPhone);
                      }

          }
        }

   }
}