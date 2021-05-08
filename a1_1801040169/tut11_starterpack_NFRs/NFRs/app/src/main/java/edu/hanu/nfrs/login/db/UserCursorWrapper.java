package edu.hanu.nfrs.login.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.nfrs.login.models.User;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);

        moveToFirst();
    }

    public User getUser() {
        if (isAfterLast()) {
            return null;
        }

        int id = getInt(getColumnIndex("id"));
        String name = getString(getColumnIndex(DbSchema.UsersTable.Cols.NAME));
        String username = getString(getColumnIndex(DbSchema.UsersTable.Cols.USERNAME));
        String password = getString(getColumnIndex(DbSchema.UsersTable.Cols.PASSWORD));

        User user = new User(id, name, username, password);

        moveToNext();

        return user;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        while (!isAfterLast()) {
            User friend = getUser();
            users.add(friend);
        }

        return users;
    }
}
