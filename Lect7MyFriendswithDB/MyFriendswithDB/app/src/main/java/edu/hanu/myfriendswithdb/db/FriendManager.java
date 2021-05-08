package edu.hanu.myfriendswithdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import edu.hanu.myfriendswithdb.models.Friend;

public class FriendManager {
    // singleton
    private static FriendManager instance;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.FriendsTable.NAME + "(name, phoneNo, email) VALUES (?, ?, ?)";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public static FriendManager getInstance(Context context) {
        if (instance == null) {
            instance = new FriendManager(context);
        }

        return instance;
    }

    private FriendManager(Context context) {
        dbHelper = new DbHelper(context);
        //call onCreate/update
        db = dbHelper.getWritableDatabase();
    }

    public List<Friend> all() {
        String sql = "SELECT * FROM friends";
        Cursor cursor = db.rawQuery(sql, null);
        FriendCursorWrapper cursorWrapper = new FriendCursorWrapper(cursor);

        return cursorWrapper.getFriends();
    }

    /**
     * @modifies friend
     */
    public boolean add(Friend friend) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1, friend.getName());
        statement.bindString(2, friend.getPhoneNo());
        statement.bindString(3, friend.getEmail());

        long id = statement.executeInsert();
//        statement.executeUpdateDelete();
        // a
        if (id > 0) {
            friend.setId(id);
            return true;
        }

        return false;
    }

    public boolean delete(long id) {
        int result = db.delete(DbSchema.FriendsTable.NAME, "id = ?", new String[]{ id+"" });

        return result > 0;
    }
}