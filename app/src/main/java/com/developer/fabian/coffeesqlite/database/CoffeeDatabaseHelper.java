package com.developer.fabian.coffeesqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developer.fabian.coffeesqlite.R;

public class CoffeeDatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME_DB = "coffeeDB";
    private static final int VERSION_DB = 1;

    public static final String TABLE_NAME = "DRINKS";
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "NAME";
    public static final String DESC_FIELD = "DESCRIPTION";
    public static final String IMAGE_FIELD = "IMAGE_ID";

    private Context context;

    public CoffeeDatabaseHelper(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_FIELD + " TEXT, " + DESC_FIELD + " TEXT, " + IMAGE_FIELD + " INTEGER);";

        db.execSQL(queryCreateTable);

        insertCategory(db, context.getString(R.string.latte_name), context.getString(R.string.latte_description), R.drawable.latte);
        insertCategory(db, context.getString(R.string.cappuccino_name), context.getString(R.string.cappuccino_description), R.drawable.cappuccino);
        insertCategory(db, context.getString(R.string.filter_name), context.getString(R.string.filter_description), R.drawable.filter);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private static void insertCategory(SQLiteDatabase db, String name, String description, int imageId) {
        ContentValues valuesCategory = new ContentValues();

        valuesCategory.put(NAME_FIELD, name);
        valuesCategory.put(DESC_FIELD, description);
        valuesCategory.put(IMAGE_FIELD, imageId);

        db.insert(TABLE_NAME, null, valuesCategory);
    }
}
