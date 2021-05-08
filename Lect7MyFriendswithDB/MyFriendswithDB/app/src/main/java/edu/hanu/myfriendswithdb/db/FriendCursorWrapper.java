package edu.hanu.myfriendswithdb.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.myfriendswithdb.models.Friend;

public class FriendCursorWrapper extends CursorWrapper {
    public FriendCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Friend getFriend() {
        String name = getString(getColumnIndex(DbSchema.FriendsTable.Cols.NAME));
        String phoneNo = getString(getColumnIndex(DbSchema.FriendsTable.Cols.PHONE_NO));
        String email = getString(getColumnIndex(DbSchema.FriendsTable.Cols.EMAIL));

        Friend friend = new Friend(name, phoneNo, email);

        return friend;
    }

    public List<Friend> getFriends() {
        List<Friend> friends = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()) {
            Friend friend = getFriend();
            friends.add(friend);

            moveToNext();
        }

        return friends;
    }
}
