package hanu.a2_1801040169.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.List;

import hanu.a2_1801040169.models.CartItem;

public class CartItemManager {
    private static CartItemManager instance;

    private static final String INSERT_STMT = "INSERT INTO " + DbSchema.CartItemTable.NAME +
           "(thumbnail, name, price, count, sum_price, product_id) VALUES (?, ?,?,?,?,?)";
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private CartItemManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public static CartItemManager getInstance(Context context){
        if(instance == null){
            instance = new CartItemManager(context);
        }
        return instance;
    }

    public List<CartItem> all(){
        String sql = "SELECT *  FROM " + DbSchema.CartItemTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        CartItemCursorManager cursorManager = new CartItemCursorManager(cursor);

        return cursorManager.getCartItems();
    }

    public boolean add(CartItem cartItem){

        SQLiteStatement statement = db.compileStatement(INSERT_STMT);
        statement.bindString(1, cartItem.getThumbnail());
        statement.bindString(2, cartItem.getName());
        statement.bindString(3, String.valueOf(cartItem.getPrice()));
        statement.bindString(4, String.valueOf(cartItem.getCount()));
        statement.bindString(5, String.valueOf(cartItem.getSumPrice()));
        statement.bindString(6, String.valueOf(cartItem.getProductId()));


        long id = statement.executeInsert();

        if(id > 0){
            cartItem.setId(id);
            Log.d("SET-ID", id + "");
            return true;
        }else{
            return false;
        }

    }

    public CartItem findByProductId(long productId){
        String sql = "SELECT * FROM " + DbSchema.CartItemTable.NAME + " WHERE " + DbSchema.CartItemTable.Cols.PRODUCT_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{productId + ""});
        CartItemCursorManager cursorManager = new CartItemCursorManager(cursor);

        return cursorManager.getCartItem(cursor);
    }

    public boolean update(CartItem cartItem){
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.CartItemTable.Cols.COUNT, cartItem.getCount());
        cv.put(DbSchema.CartItemTable.Cols.SUM_PRICE, cartItem.getSumPrice());

        long result = db.update(DbSchema.CartItemTable.NAME, cv, "id=?", new String[]{String.valueOf(cartItem.getId())});
        Log.d("ID", cartItem.getId() + "");
        Log.d("RESULT", result + "");

        return result > 0;
    }

    public boolean delete(long id){
        long result = db.delete(DbSchema.CartItemTable.NAME, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
