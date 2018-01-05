package com.developer.fabian.coffeesqlite.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.fabian.coffeesqlite.R;
import com.developer.fabian.coffeesqlite.database.CoffeeDatabaseHelper;

public class DetailCategoryActivity extends AppCompatActivity {

    public static final String DRINK_NUMBER = "drink_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        int drinkNumber = (Integer) getIntent().getExtras().get(DRINK_NUMBER);

        try {
            SQLiteOpenHelper coffeeDataHelper = new CoffeeDatabaseHelper(this);
            SQLiteDatabase db = coffeeDataHelper.getReadableDatabase();
            Cursor cursor = db.query(CoffeeDatabaseHelper.TABLE_NAME,
                    new String[]{CoffeeDatabaseHelper.NAME_FIELD, CoffeeDatabaseHelper.DESC_FIELD, CoffeeDatabaseHelper.IMAGE_FIELD},
                    CoffeeDatabaseHelper.ID_FIELD + " = ?", new String[]{Integer.toString(drinkNumber)}, null, null, null);

            if (cursor.moveToFirst()) {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int imageId = cursor.getInt(2);

                TextView nameText = findViewById(R.id.name);
                nameText.setText(name);

                TextView descriptionText = findViewById(R.id.description);
                descriptionText.setText(description);

                ImageView imageView = findViewById(R.id.image);
                imageView.setImageResource(imageId);
                imageView.setContentDescription(name);
            }

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.message_error_database, Toast.LENGTH_SHORT).show();
        }
    }
}
