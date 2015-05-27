package com.amandroidapps.encryptsms;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 25-05-2015.
 */
public class MessageAdapter extends CursorRecyclerViewAdapter<MessageAdapter.MessageViewHolder> {

    //private List<MessageInfo> messageList;
    //private Cursor mCursor;

    public MessageAdapter(Context context, Cursor cursor){

        //this.messageList = messageList;
        super(context,cursor);
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

    }

    //@Override
    //public int getItemCount() {
        //return messageList.size();
    //}

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
