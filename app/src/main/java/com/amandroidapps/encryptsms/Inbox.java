package com.amandroidapps.encryptsms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class Inbox extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        RecyclerView rvMessages = (RecyclerView)findViewById(R.id.rvMessages);
        rvMessages.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessages.setLayoutManager(lm);
        MessageAdapter ma = new MessageAdapter(createList(10));
        rvMessages.setAdapter(ma);

    }

    private List<MessageInfo> createList(int size){
        List<MessageInfo> result = new ArrayList<MessageInfo>();
        for(int i=0;i<size;i++){
            MessageInfo mi = new MessageInfo();
            mi.mi_Name="Name = "+i;
            mi.mi_Message="Message = "+i;
            result.add(mi);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
