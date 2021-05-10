package hanu.a2_1801040169.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.List;

import hanu.a2_1801040169.models.Product;

public class ProductManager {
    private static ProductManager instance;

    private static final String INSERT_STMT = "INSERT INTO " + DbSchema.ProductTable.NAME +
           "(id, thumbnail, name, category, price, count) VALUES (?, ?, ?,?, ?,?)";
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private ProductManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public static ProductManager getInstance(Context context){
        if(instance == null){
            instance = new ProductManager(context);
        }
        return instance;
    }

    public List<Product> all(){
        String sql = "SELECT *  FROM " + DbSchema.ProductTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        ProductCursorManager cursorManager = new ProductCursorManager(cursor);

        return cursorManager.getProducts();
    }

    public boolean add(Product product){

        SQLiteStatement statement = db.compileStatement(INSERT_STMT);
        statement.bindString(1, String.valueOf(product.getId()));

        statement.bindString(2, product.getThumbnail());
        statement.bindString(3, product.getName());
        statement.bindString(4, product.getCategory());
        statement.bindString(5, String.valueOf(product.getPrice()));
        statement.bindString(6, String.valueOf(product.getCount()));

        long id = statement.executeInsert();

        if(id > 0){
//            product.setId(id);
//            Log.d("SET-ID", id + "");
            return true;
        }else{
            return false;
        }

    }

    public Product findById(long id){
        String sql = "SELECT * FROM " + DbSchema.ProductTable.NAME + " WHERE " + DbSchema.ProductTable.Cols.ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        ProductCursorManager cursorManager = new ProductCursorManager(cursor);

        return cursorManager.getProduct(cursor);
    }

    public List<Product> findByCategory(String categoryName){
        String sql = "SELECT * FROM " + DbSchema.ProductTable.NAME + " WHERE " + DbSchema.ProductTable.Cols.CATEGORY + " LIKE ?";
        Cursor cursor = db.rawQuery(sql, new String[]{"%" + categoryName + "%"});
        ProductCursorManager cursorManager = new ProductCursorManager(cursor);

        return cursorManager.getProducts();
    }

    public List<Product> orderByQuantity(){
        String sql = "SELECT * FROM " + DbSchema.ProductTable.NAME + " ORDER BY " + DbSchema.ProductTable.Cols.COUNT + " DESC";
        Cursor cursor = db.rawQuery(sql, null);
        ProductCursorManager cursorManager = new ProductCursorManager(cursor);

        return cursorManager.getProducts();
    }

    public boolean update(Product product){
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.ProductTable.Cols.COUNT, product.getCount());
//        cv.put(DbSchema.ProductTable.Cols.SUM_PRICE, product.getSumPrice());

        long result = db.update(DbSchema.ProductTable.NAME, cv, "id=?", new String[]{String.valueOf(product.getId())});
        Log.d("ID", product.getId() + "");
        Log.d("RESULT", result + "");

        return result > 0;
    }

    public boolean delete(long id){
        long result = db.delete(DbSchema.ProductTable.NAME, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
