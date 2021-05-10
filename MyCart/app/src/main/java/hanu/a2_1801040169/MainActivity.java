package hanu.a2_1801040169;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hanu.a2_1801040169.adapter.ProductAdapter;
import hanu.a2_1801040169.models.Product;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView rvProducts;
//    private List<Product> products;
//    private ProductAdapter productAdapter;
//
//    private static final int VIEW_CART = 1;
//    private static String JSON_URL = "https://mpr-cart-api.herokuapp.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rvProducts = findViewById(R.id.rvProducts);
//
//        products = new ArrayList<>();
//        products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
//        products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
//        products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
//        products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
//        products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));

//        String url = "https://mpr-cart-api.herokuapp.com/products";
//        RestLoader restLoader = new RestLoader();
//        restLoader.execute();

//        productAdapter = new ProductAdapter(products);
//        rvProducts.setAdapter(productAdapter);
//
//        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new ProductFragment();

        manager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.iconCart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

//    public class RestLoader extends AsyncTask<String, Void, String> {
//        private ArrayList<String> list = new ArrayList<>();
//
//        @Override
//        protected String doInBackground(String... strings) {
//            URL url;
//            HttpURLConnection urlConnection;
////            String current = "";
////            for (int i = 0; i < list.size(); i++) {
//            try {
//                url = new URL(JSON_URL);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.connect();
//                InputStream is = urlConnection.getInputStream();
//                Scanner sc = new Scanner(is);
//                StringBuilder result = new StringBuilder();
//                String line;
//                while (sc.hasNextLine()) {
//                    line = sc.nextLine();
//                    result.append(line);
//                }
//
//                return result.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result == null) {
//                return;
//            }
//            try {
//                JSONArray jsonArray = new JSONArray(result);
////                Log.d("JSONArr", result + "");
//
////                Long id = obj.getLong("id");
//                for(int i=0; i<jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
////                    Log.d("JSONObj", jsonObject + "");
//
//                    Product product = new Product();
//                    product.setId(jsonObject.getLong("id"));
////                                        Log.d("JSONObj", jsonObject.getLong("id") + "");
//
////                    product.setThumbnail(jsonObject.getString("thumbnail"));
////                    product.setName(jsonObject.getString("name"));
////                    product.setCategory(jsonObject.getString("category"));
////                    product.setUnitPrice(jsonObject.getDouble("unitPrice"));
////
////                    products.add(product);
//                    long id = jsonObject.getLong("id");
//                    String name = jsonObject.getString("name");
//                    String category = jsonObject.getString("category");
//                    double unitPrice = jsonObject.getDouble("unitPrice");
//
//                    String thumbnail = jsonObject.getString("thumbnail");
////                            products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
////                    products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
////                    products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
////                    products.add(new Product(1, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", "watches", 23000));
//                    products.add(new Product(id, thumbnail, name, category, unitPrice));
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            productAdapter = new ProductAdapter(products, MainActivity.this);
//            rvProducts.setAdapter(productAdapter);
//
//            rvProducts.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
//        }
//
//}

//public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
//
//    @Override
//    protected Bitmap doInBackground(String... strings) {
//        try {
//            URL url = new URL(strings[0]);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            InputStream is = connection.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            return bitmap;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//
//    }
//}


}