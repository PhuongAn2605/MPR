package edu.hanu.nfrs.login.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.hanu.nfrs.login.models.User;

public class UserManager {
    // singleton
    private static UserManager instance;

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public static UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context);
        }

        return instance;
    }

    private UserManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public User getByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
        Cursor cursor = db.rawQuery(sql, null);

        UserCursorWrapper cursorWrapper = new UserCursorWrapper(cursor);
        return cursorWrapper.getUser();
    }

}