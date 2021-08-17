package sg.edu.rp.c346.id20024466.l13_rpgapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weaponlist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_WEAPON = "Weapon";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createWeaponTableSql = "CREATE TABLE " + TABLE_WEAPON + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_PRICE + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createWeaponTableSql);
        Log.i("info", createWeaponTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEAPON);
        onCreate(db);
    }

    public long insertWeapon(String name, String description, int price, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_WEAPON, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Weapon> getAllWeapons() {
        ArrayList<Weapon> weaponlist = new ArrayList<Weapon>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_PRICE + ","
                + COLUMN_STARS + " FROM " + TABLE_WEAPON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Weapon newWeapon = new Weapon(id, title, singers, year, stars);
                weaponlist.add(newWeapon);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return weaponlist;
    }

    public ArrayList<Weapon> getAllWeaponByStars(int starsFilter) {
        ArrayList<Weapon> weaponlist = new ArrayList<Weapon>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PRICE, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};


        Cursor cursor;
        cursor = db.query(TABLE_WEAPON, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String weapons = cursor.getString(2);
                int role = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Weapon newWeapon = new Weapon(id, title, weapons, role, stars);
                weaponlist.add(newWeapon);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return weaponlist;
    }

    public int updateWeapon(Weapon data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_PRICE, data.getPrice());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_WEAPON, values, condition, args);
        db.close();
        return result;
    }


    public int deleteWeapon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_WEAPON, condition, args);
        db.close();
        return result;
    }

}