package com.amandroidapps.encryptsms;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class Inbox extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_ID = 1;
    //private LoaderManager.LoaderCallbacks<Cursor> mCallBacks;
    private static final String CONTENT_URI = "content://sms/inbox";
    private static final String[] PROJECTION = {"_id","address","date","body","read"};
    private static final String[] DISPLAY_COLS = {"_id","body","address"};
    private static final String SELECTION = "body like ?";
    private static final String[] SELECTION_ARGS = {"%256%256$$AMAA%"};
    private static final String SORT_ORDER = "date DESC";
    private MessageAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        RecyclerView rvMessages = (RecyclerView)findViewById(R.id.rvMessages);
        rvMessages.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessages.setLayoutManager(llm);
        ma = new MessageAdapter(this,null);
        rvMessages.setAdapter(ma);
        LoaderManager.LoaderCallbacks<Cursor> mCallBacks;
        mCallBacks=this;
        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallBacks);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = new CursorLoader(this);
        Uri uri = Uri.parse(CONTENT_URI);
        cl.setUri(uri);
        cl.setProjection(PROJECTION);
        cl.setSortOrder(SORT_ORDER);
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()){
            case LOADER_ID:
                ma.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        ma.swapCursor(null);
    }

    /*private Cursor createCursor(){
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);

        return c;
    }*/

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
