package com.developer.fabian.coffeesqlite.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.developer.fabian.coffeesqlite.R;
import com.developer.fabian.coffeesqlite.database.CoffeeDatabaseHelper;

public class CategoriesActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listDrinks = getListView();
        try {
            SQLiteOpenHelper coffeeDataHelper = new CoffeeDatabaseHelper(this);
            db = coffeeDataHelper.getReadableDatabase();
            cursor = db.query(CoffeeDatabaseHelper.TABLE_NAME,
                    new String[]{CoffeeDatabaseHelper.ID_FIELD, CoffeeDatabaseHelper.NAME_FIELD},
                    null, null, null, null, null);

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{CoffeeDatabaseHelper.NAME_FIELD},
                    new int[]{android.R.id.text1}, 0);

            listDrinks.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.message_error_database, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(CategoriesActivity.this, DetailCategoryActivity.class);
        intent.putExtra(DetailCategoryActivity.DRINK_NUMBER, (int) id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        cursor.close();
        db.close();
    }
}
