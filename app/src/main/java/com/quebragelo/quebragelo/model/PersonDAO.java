package com.quebragelo.quebragelo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import com.brunoocasali.milibros.vo.BookVO;

/**
 * Created by Bruno Casali on 24/04/2015.
 */
public class PersonDAO {

    private AppHelper helper;

//    public PersonDAO(Context context){
//        this.helper = new AppHelper(context, Constraint.DATABASE_NAME, null, 1);
//    }
//
//    public void save(BookVO book){
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(Constraint.COLUMN_TITLE, book.getTitle());
//        values.put(Constraint.COLUMN_AUTHOR, book.getAuthor());
//        values.put(Constraint.COLUMN_RATE, book.getRate());
//        values.put(Constraint.COLUMN_STATUS, book.getStatus());
//
//        db.insert(Constraint.TABLE_BOOKS, null, values);
//        db.close();
//    }
//
//    public Cursor list(){
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        String[] columns = {
//                Constraint.COLUMN_ID,
//                Constraint.COLUMN_TITLE,
//                Constraint.COLUMN_AUTHOR,
//                Constraint.COLUMN_RATE,
//                Constraint.COLUMN_STATUS };
//
//        return db.query(Constraint.TABLE_BOOKS, columns, null, null, null, null, null);
//    }
//
//    public void destroy(long id){
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        String[] condition = { String.valueOf(id) };
//
//        db.delete(Constraint.TABLE_BOOKS, "_id = ?", condition);
//        db.close();
//    }
}
