package hanu.a2_1801040169;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hanu.a2_1801040169.adapter.ProductAdapter;
import hanu.a2_1801040169.db.ProductManager;
import hanu.a2_1801040169.models.Product;


public class ProductFragment extends Fragment {

    private RecyclerView rvProducts;
    private List<Product> products;
    private ProductAdapter productAdapter;
    private static String JSON_URL = "https://mpr-cart-api.herokuapp.com/products";


    private View itemView;
    private MainActivity mainActivity;
    private EditText textSearch;
    private ImageView ivSearch;

    private ProductManager productManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_product, container, false);
        mainActivity = (MainActivity) getActivity();

        rvProducts = itemView.findViewById(R.id.rvProducts);
        textSearch = itemView.findViewById(R.id.textSearch);
        ivSearch = itemView.findViewById(R.id.ivSearch);

        products = new ArrayList<>();

        return itemView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestLoader restLoader = new RestLoader();
        restLoader.execute(JSON_URL);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = textSearch.getText().toString();
//                Log.d("SEARCH", categoryName);
//                restLoader.execute(JSON_URL + "?category=" + categoryName);
                productManager = productManager.getInstance(mainActivity);
                products = productManager.findByCategory(categoryName);
                setData();



            }
        });
    }

    public class RestLoader extends AsyncTask<String, Void, String> {
        private ArrayList<String> list = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
//            String current = "";
//            for (int i = 0; i < list.size(); i++) {
            try {
//                url = new URL(JSON_URL);
                url = new URL(strings[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                }

                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                return;
            }
            try {
                JSONArray jsonArray = new JSONArray(result);
//                Log.d("JSONArr", jsonArray.toString() + "");
//
//                Long id = obj.getLong("id");
                for (int i = 0; i < jsonArray.length(); i++) {
//                    Log.d("LENGTH", jsonArray.length() + "");
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    Log.d("JSONObj", jsonObject + "");

                    Product product = new Product();
                    product.setId(jsonObject.getLong("id"));

                    long id = jsonObject.getLong("id");
                    String name = jsonObject.getString("name");
                    String category = jsonObject.getString("category");
                    double unitPrice = jsonObject.getDouble("unitPrice");

                    String thumbnail = jsonObject.getString("thumbnail");
                    products.add(new Product(id, thumbnail, name, category, unitPrice));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            setData();
//            Log.d("PRODUCTS", products.toString());


        }

    }
    public void setData(){
        rvProducts.setLayoutManager(new GridLayoutManager(mainActivity, 2));

        productAdapter = new ProductAdapter(products, mainActivity);
        productAdapter.notifyDataSetChanged();
        rvProducts.setAdapter(productAdapter);

    }
}