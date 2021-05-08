
package hanu.a2_1801040169;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1801040169.adapter.CartItemAdapter;
import hanu.a2_1801040169.db.CartItemManager;
import hanu.a2_1801040169.models.CartItem;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCartItems;
    private List<CartItem> cartItems;
    private CartItemAdapter cartItemAdapter;
    private CartItemManager cartItemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCartItems = findViewById(R.id.rvCartItems);
        cartItems = new ArrayList<>();

//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));
//        cartItems.add(new CartItem(1, "S03 watch - save your time", 150000, 1, 150000));

        cartItemManager = CartItemManager.getInstance(this);
        cartItems = cartItemManager.all();

        cartItemAdapter = new CartItemAdapter(cartItems);
        rvCartItems.setAdapter(cartItemAdapter);
        rvCartItems.setLayoutManager(new GridLayoutManager(this, 1));
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