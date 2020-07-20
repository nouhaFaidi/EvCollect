package com.example.evcollect;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String  APPS_TABLE_NAME = "Applications";
    public static final String  Forms_TABLE_NAME = "Formu";
    public static final String Forms_COLUMN_ID = "id";
    public static final String Forms_COLUMN_Permis = "Permis";
    public static final String Forms_COLUMN_Demandeur = "Demandeur";
    public static final String Forms_COLUMN_Nombre= "Nombre";
    public static final String Forms_COLUMN_Taille = "Taille";
    public static final String Forms_COLUMN_Date = "Date";

    public static final String APPS_COLUMN_ID = "id";
    public static final String APPS_COLUMN_Identifiant = "identifiant";
    public static final String APPS_COLUMN_Name = "name";
    public static final String APPS_COLUMN_DateCreated = "DateCreated";
    public static final String APPS_COLUMN_DateModified = "DateModified";
    public static final String APPS_COLUMN_Description = "Description";
    public static final String APPS_COLUMN_ResourceType = "ResourceType";
    public static final String APPS_COLUMN_DescribeUrl = "DescribeUrl";
    private HashMap hp;


    public DBhelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL( "create table Applications" +"(id integer primary key,name text,identifiant text, DateCreated text,DateModified text,Description text,ResourceType text,DescribeUrl text)" );

         /*    db.execSQL(
                "create table Formulaires " +"(id integer primary key,Permis text, Demandeur text,Nombre text,Taille text,Date text)"
        );*/
       //  db.execSQL( ("create table Formulaires"+"(id integer primary key,Permis text, Demandeur text,Nombre text,Taille text,Date text)") );
    db.execSQL( "create table Formu"+"(id integer primary key,Permis text, Demandeur text,Nombre text,Taille text,Date text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL( "DROP TABLE IF EXISTS Applications " );
        db.execSQL( "DROP TABLE IF EXISTS Formu " );
        onCreate( db );
    }

    public boolean insertForm ( String NoPermis,String Demandeur,String Nombre,String Taille,String Date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Permis", NoPermis);
        contentValues.put("Demandeur", Demandeur);
        contentValues.put("Nombre", Nombre);

        contentValues.put("Taille", Taille);
        contentValues.put("Date", Date);
db.insert( "Formu",null,contentValues );
       // db.insert("Formulaires", null, contentValues);
        //db.insert( "Formulaires" ,null,contentValues);
    return true;
    }
    public boolean insertApp ( String DateCreated, String DateModified, String
           Description, String name, String identifiant,String ResourceType , String DescribeUrl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DateCreated", DateCreated);
        contentValues.put("DateModified", DateModified);

        contentValues.put("Name", name);
        contentValues.put("identifiant", identifiant);
        contentValues.put("Description", Description);


        contentValues.put("ResourceType", ResourceType);
        contentValues.put("DescribeUrl", DescribeUrl);
        db.insert("Applications", null, contentValues);
        return true;
    }

    public Cursor getDataForm(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery( "select * from Formu where id="+id+"",null );
        return res;
       /* SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Formulaires where id="+id+"", null );
        return res;*/
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Applications where id="+id+"", null );
        return res;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                APPS_TABLE_NAME);
        return numRows;
    }

    public boolean updateForm (Integer id,String NoPermis,String Demandeur,String Nombre ,String Taille,String Date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Permis", NoPermis);
        contentValues.put("Demandeur", Demandeur);
        contentValues.put("Nombre", Nombre);
        contentValues.put("Taille", Taille);
        contentValues.put("Date", Date);

        db.update("Formu", contentValues, "id = ? ", new String[] {
                (id.toString()) } );
        return true;
    }


    public boolean updateApp (Integer id, String DateCreated, String DateModified, String
            Description, String name, String identifiant, String ResourceType , String DescribeUrl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("DateCreated", DateCreated);
        contentValues.put("DateModified", DateModified);
        contentValues.put("Description", Description);
        contentValues.put("name", name);
        contentValues.put( "identifiant",identifiant );
        contentValues.put("ResourceType", ResourceType);
        contentValues.put("DescribeUrl", DescribeUrl);
        db.update("Applications", contentValues, "id = ? ", new String[] {
               (id.toString()) } );
        return true;
    }

public Integer deleteForm(Integer id){
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete("Formu","id = ? ", new String[] { Integer.toString(id) });

}

    public Integer deleteApp (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Applications","id = ? ",
                new String[] { Integer.toString(id) });
    }


public ArrayList<Formulaire> getAllFormulaires(){
    ArrayList array_list = new ArrayList();
    Cursor res;
    SQLiteDatabase db=this.getReadableDatabase();
    res=db.rawQuery( "select * from Formu",null );
//hp = new HashMap();
    /*Cursor res;
    SQLiteDatabase db = this.getReadableDatabase() ;
        res = db.rawQuery( "select * from Formulaires", null );
*/
    res.moveToFirst();
    while(res.isAfterLast() == false){
        array_list.add(res.getString(res.getColumnIndex(Forms_COLUMN_Demandeur)
        ));
        res.moveToNext();
    }
    return array_list;
}

    public ArrayList getAllApplications()
    {
        ArrayList array_list = new ArrayList();
//hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Applications", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(APPS_COLUMN_Name)
            ));
            res.moveToNext();
}
        return array_list;
                }





                }
