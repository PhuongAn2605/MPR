package hanu.a2_1801040169.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "products_test.db";
    private static final int DATABASE_VERSION = 9;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.ProductTable.NAME + "(" +
                DbSchema.ProductTable.Cols.ID + " INTEGER PRIMARY KEY, " +
                DbSchema.ProductTable.Cols.THUMBNAIL + " TEXT, " +
                DbSchema.ProductTable.Cols.NAME + " TEXT, " +
                DbSchema.ProductTable.Cols.CATEGORY + " TEXT, " +
                DbSchema.ProductTable.Cols.PRICE + " TEXT, " +
                DbSchema.ProductTable.Cols.COUNT + " INTEGER "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DbSchema.ProductTable.NAME);
        onCreate(db);
    }
}
