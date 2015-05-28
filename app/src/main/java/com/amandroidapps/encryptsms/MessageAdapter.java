package com.amandroidapps.encryptsms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 25-05-2015.
 */
public class MessageAdapter extends CursorRecyclerViewAdapter<MessageAdapter.MessageViewHolder> {

    //private List<MessageInfo> messageList;
    //private Cursor mCursor;
    private Context context;
    //private String contactName;

    public MessageAdapter(Context context, Cursor cursor){

        //this.messageList = messageList;
        super(context,cursor);
        this.context=context;
        //this.mCursor=cursor;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card_layout,parent,false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, Cursor cursor) {

        MessageInfo mi = MessageInfo.fromCursor(cursor);
        holder.tvName.setText(mi.mi_Name);
        //holder.ivContact.setImageBitmap(mi.mi_Image);
        holder.tvMessage.setText(mi.mi_Message);
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(holder);
        al.add(mi.mi_Name);
        //new ContactName().execute(al);
        String contactName="";
        contactName=getContactName(mi.mi_Name);
        if(contactName!=null && !contactName.equals("")){
            holder.tvName.setText(contactName);
        }

    }

    private String getContactName(String phoneNumber){
        Uri uri = Uri.parse("content://com.android.contacts/phone_lookup");
        String [] projection = new String[] { "display_name" };
        uri = Uri.withAppendedPath(uri, Uri.encode(phoneNumber));
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        String contactName = "";

        if (cursor.moveToFirst())
        {
            contactName = cursor.getString(0);
        }

        cursor.close();
        cursor = null;
        return contactName;
    }

    private class ContactName extends AsyncTask<ArrayList,Void,ArrayList>{

        @Override
        protected ArrayList doInBackground(ArrayList... al) {
            Uri uri = Uri.parse("content://com.android.contacts/phone_lookup");
            String [] projection = new String[] { "display_name" };
            String phoneNumber=(String)al[0].get(1);
            uri = Uri.withAppendedPath(uri, Uri.encode(phoneNumber));
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            String contactName = "";

            if (cursor.moveToFirst())
            {
                contactName = cursor.getString(0);
            }

            cursor.close();
            cursor = null;
            Log.d("ESMS",phoneNumber+" = "+contactName);
            ArrayList<Object> alResult = new ArrayList<Object>();
            alResult.add(al[0].get(0));
            alResult.add(contactName);

            return alResult;
        }

        @Override
        protected void onPostExecute(ArrayList s) {
            MessageViewHolder holder = (MessageViewHolder)s.get(0);
            String contactName=(String)s.get(1);
            //Log.d("ESMS","contactNamePE = "+contactName);
            if(contactName!=null && !contactName.equals("")){
                holder.tvName.setText(contactName);
            }
        }
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        protected TextView tvName;
        protected ImageView ivContact;
        protected TextView tvMessage;
        public MessageViewHolder(View v){
            super(v);
            tvName = (TextView)v.findViewById(R.id.tvName);
            //ivContact = (ImageView)v.findViewById(R.id.ivContact);
            tvMessage = (TextView)v.findViewById(R.id.tvMessage);
        }
    }
}
