package edu.hanu.nfrs.login.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.UsersTable.NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.UsersTable.Cols.USERNAME + " TEXT, " +
                DbSchema.UsersTable.Cols.PASSWORD + " TEXT, " +
                DbSchema.UsersTable.Cols.NAME + " TEXT" + ")");

        // other tables here

        db.execSQL("INSERT INTO " + DbSchema.UsersTable.NAME + " (username, password, name) VALUES ('admin', 'admin', 'Administrator')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.UsersTable.NAME);

        // other tables here

        onCreate(db);
    }
}
