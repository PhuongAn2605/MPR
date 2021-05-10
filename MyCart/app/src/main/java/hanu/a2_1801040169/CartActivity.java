
package hanu.a2_1801040169;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1801040169.adapter.CartItemAdapter;
import hanu.a2_1801040169.db.ProductManager;
import hanu.a2_1801040169.models.Product;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCartItems;
    private List<Product> cartItems;
    private CartItemAdapter cartItemAdapter;
    private ProductManager cartItemManager;
    public TextView tvTotalPrice;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        rvCartItems = findViewById(R.id.rvCartItems);
        cartItems = new ArrayList<>();
        Product item = null;

//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));

        cartItemManager = ProductManager.getInstance(this);
        cartItems = cartItemManager.all();
        cartItems = cartItemManager.orderByQuantity();

        for(int i=0; i<cartItems.size(); i++){
            totalPrice += cartItems.get(i).getSumPrice();
            if(i == cartItems.size() -1){
                tvTotalPrice.setText(cartItems.get(i).formatPrice(totalPrice));
            }
        }
//        setTvTotalPrice();
        cartItemAdapter = new CartItemAdapter(cartItems, new CartItemAdapter.IClickChangeTotalPriceListener() {

            @Override
            public void onCLickChangeTotalPrice(ImageView iv, Product cartItem) {
               setTotalPrice(iv, cartItem);
            }
        });
        rvCartItems.setAdapter(cartItemAdapter);
        rvCartItems.setLayoutManager(new GridLayoutManager(this, 1));

//            tvTotalPrice.setText(str_total);
    }

//    public void setTvTotalPrice(double totalPrice){
//        totalPrice = 0;
//        for(int i=0; i<cartItems.size(); i++){
//            totalPrice += cartItems.get(i).getSumPrice();
//            if(i == cartItems.size() -1){
//               str_total = cartItems.get(i).formatPrice(totalPrice);
//            }
//        }
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(ImageView iv, Product cartItem) {
        if(iv.getId() == R.id.ivIncrease){

            this.totalPrice += cartItem.getPrice();

        }else if(iv.getId() == R.id.ivDecrease){
            this.totalPrice -= cartItem.getPrice();

        }
//        cartItemAdapter.notifyDataSetChanged();
        tvTotalPrice.setText(cartItem.formatPrice(totalPrice));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.iconCart){
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}