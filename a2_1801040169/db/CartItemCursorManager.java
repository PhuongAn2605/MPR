package hanu.a2_1801040169.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1801040169.models.CartItem;

public class CartItemCursorManager extends CursorWrapper {


    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CartItemCursorManager(Cursor cursor) {
        super(cursor);
    }

    public CartItem getCartItem(){
        String thumbnail = getString(getColumnIndex(DbSchema.CartItemTable.Cols.THUMBNAIL));
        long id = getLong(getColumnIndex(DbSchema.CartItemTable.Cols.ID));
        String name = getString(getColumnIndex(DbSchema.CartItemTable.Cols.NAME));
        double price = getDouble(getColumnIndex(DbSchema.CartItemTable.Cols.PRICE));
        long count = getInt(getColumnIndex(DbSchema.CartItemTable.Cols.COUNT));
        double sumPrice = getDouble(getColumnIndex(DbSchema.CartItemTable.Cols.SUM_PRICE));

        long productId = getLong(getColumnIndex(DbSchema.CartItemTable.Cols.PRODUCT_ID));


        CartItem cartItem = new CartItem(id, thumbnail, name, price, count, sumPrice, productId);
        return cartItem;
    }

    public CartItem getCartItem(Cursor cursor){
        moveToFirst();
        CartItem cartItem = null;
        if(!isAfterLast()){
            cartItem = getCartItem();
        }
        return cartItem;
    }

    public List<CartItem> getCartItems(){
        List<CartItem> cartItems = new ArrayList<>();

        moveToFirst();
        while(!isAfterLast()){
            CartItem cartItem = getCartItem();
            cartItems.add(cartItem);
            moveToNext();
        }
        return cartItems;
    }
}
