package hanu.a2_1801040169.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1801040169.models.Product;

public class ProductCursorManager extends CursorWrapper {


    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ProductCursorManager(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct(){
        String thumbnail = getString(getColumnIndex(DbSchema.ProductTable.Cols.THUMBNAIL));
        long id = getLong(getColumnIndex(DbSchema.ProductTable.Cols.ID));
        String name = getString(getColumnIndex(DbSchema.ProductTable.Cols.NAME));
        String category = getString(getColumnIndex(DbSchema.ProductTable.Cols.CATEGORY));

        double price = getDouble(getColumnIndex(DbSchema.ProductTable.Cols.PRICE));
        long count = getInt(getColumnIndex(DbSchema.ProductTable.Cols.COUNT));

        Product product = new Product(id, thumbnail, name, category,  price, count);
        return product;
    }

    public Product getProduct(Cursor cursor){
        moveToFirst();
        Product product = null;
        if(!isAfterLast()){
            product = getProduct();
        }
        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        moveToFirst();
        while(!isAfterLast()){
            Product product = getProduct();
            products.add(product);
            moveToNext();
        }
        return products;
    }
}
