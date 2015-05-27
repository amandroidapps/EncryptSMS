package com.amandroidapps.encryptsms;

import android.database.Cursor;

/**
 * Created by USER on 25-05-2015.
 */
public class MessageInfo {

    protected String mi_Name;
    //protected Bitmap mi_Image;
    protected String mi_Message;

    public static MessageInfo fromCursor(Cursor cursor){

        MessageInfo mi = new MessageInfo();
        mi.mi_Name=cursor.getString(cursor.getColumnIndex("address"));
        mi.mi_Message=cursor.getString(cursor.getColumnIndex("body"));
        return mi;
    }
}
