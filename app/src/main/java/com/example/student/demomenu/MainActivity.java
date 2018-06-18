package com.example.student.demomenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;


public class MainActivity extends AppCompatActivity {
    ListView lv;
    String s;
    String y;
    SmsManager sm;
    String[] arr = {"Big Bazar \n 12123234134", "34355656567", "third"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list1);
        sm=SmsManager.getDefault();
        final ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adp);
        registerForContextMenu(lv);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) lv.getItemAtPosition(position);
                y = s.replaceAll("[^0-9]", "");
                return false;

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose Option");
        menu.add(0, v.getId(), 0, "call");
        menu.add(0, v.getId(), 0, "Send Sms");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("call")) {
            Intent i = new Intent(Intent.ACTION_CALL);
            Toast.makeText(this, "" + y, Toast.LENGTH_SHORT).show();
            i.setData(Uri.parse("tel:" + y));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return TODO;
            }
            startActivity(i);
        }
    if(item.getTitle()=="Send Sms")
    {

    sm.sendTextMessage(y,null,"MESSAGE",null,null);
        Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
    }
        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnf =getMenuInflater();
        mnf.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int id=  item.getItemId();
      switch (id)
      {
          case R.id.myclose:
              finish();
          break;
          case R.id.myopen:
              Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivity(i);
              break;
      }
        return super.onOptionsItemSelected(item);
    }


}
