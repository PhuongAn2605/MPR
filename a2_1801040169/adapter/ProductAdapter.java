package hanu.a2_1801040169.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hanu.a2_1801040169.R;
import hanu.a2_1801040169.db.CartItemManager;
import hanu.a2_1801040169.models.CartItem;
import hanu.a2_1801040169.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    
    List<Product> products;
    private Context context;
    private Activity activity;
    
    
    public ProductAdapter(List<Product> products){
        this.products = products;
    }

    public ProductAdapter(List<Product> products, Context context, Activity activity) {
        this.products = products;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_product, parent, false);
        
        return new ProductHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = this.products.get(position);
        holder.bind(product, position);
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct, imgCart;
        private TextView productPrice, productDesc;
        Context context;
        private CartItem cartItem;
        private long count;
        private double sumPrice;
        private CartItemManager cartItemManager;
        private CartItem existingItem;

        public ProductHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgCart = itemView.findViewById(R.id.imgCart);
            productDesc = itemView.findViewById(R.id.productDesc);
            productPrice = itemView.findViewById(R.id.productPrice);

        }

        public void bind(Product product, int position) {
//            imgProduct.setImageResource(R.drawable.watch_sport_s03);
            imgCart.setImageResource(R.drawable.ic_baseline_shopping_cart_24);
            productDesc.setText(product.getName());
            productPrice.setText(Double.toString(product.getUnitPrice()));

            String url = product.getThumbnail();
            ImageLoader restLoader = new ImageLoader();
            restLoader.execute(url);

            imgCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String short_name = product.getName().split(",")[0];
                    Toast.makeText(context, short_name + " is added to your cart", Toast.LENGTH_SHORT).show();

                    cartItemManager = CartItemManager.getInstance(context);
                    existingItem = cartItemManager.findByProductId(product.getId());
//                    Log.d("EXIST", "" + existingItem.toString());
                    if(existingItem != null){
                        count = existingItem.getCount() + 1;
                        Log.d("EXIST", "" + count);

                        sumPrice = product.getUnitPrice() * count;
                        Log.d("EXIST", "" + sumPrice);

//                        Log.d("EXIST", "" + existingItem.toString());
                        Log.d("PRODUCT", product.getId() + "");

                        cartItem = new CartItem(product.getId(), product.getThumbnail(), product.getName(), product.getUnitPrice(), count, sumPrice, product.getId());
                        Log.d("EXIST", "" + cartItem.toString());

                        cartItemManager.update(cartItem);

                    }else if(existingItem == null){
                        count = 1;
                        sumPrice = product.getUnitPrice();
                        Log.d("PRODUCT", product.getId() + "");
                        cartItem = new CartItem(product.getId(), product.getThumbnail(), product.getName(), product.getUnitPrice(), count, sumPrice, product.getId());
                        Log.d("EXIST", "" + cartItem.toString());

                        cartItemManager.add(cartItem);
                    }

//                    CartItem cartItem = new CartItem(product.getThumbnail(), product.getName(), product.getUnitPrice(), count, sumPrice, product.getId());
//                    cartItemManager.add(cartItem);

                }
            });
        }




        public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imgProduct.setImageBitmap(bitmap);
            }
        }
    }
}
