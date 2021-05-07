package hanu.a2_1801040169.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "cartItems.db";
    private static final int DATABASE_VERSION = 10;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.CartItemTable.NAME + "(" +
                DbSchema.CartItemTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.CartItemTable.Cols.THUMBNAIL + " TEXT, " +
                DbSchema.CartItemTable.Cols.NAME + " TEXT, " +
                DbSchema.CartItemTable.Cols.PRICE + " TEXT, " +
                DbSchema.CartItemTable.Cols.COUNT + " INTEGER, " +
                DbSchema.CartItemTable.Cols.SUM_PRICE + " DOUBLE, " +
                DbSchema.CartItemTable.Cols.PRODUCT_ID + " INTEGER"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DbSchema.CartItemTable.NAME);
        onCreate(db);
    }
}
