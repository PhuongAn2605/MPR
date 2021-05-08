package hanu.a2_1801040169.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import hanu.a2_1801040169.R;
import hanu.a2_1801040169.db.CartItemManager;
import hanu.a2_1801040169.models.CartItem;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemHolder> {

    private List<CartItem> cartItems;
    private Context context;

    public CartItemAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemAdapter.CartItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_cart, parent, false);

        return new CartItemHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.CartItemHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        holder.bind(cartItem, position);

    }

    @Override
    public int getItemCount(){
        return cartItems.size();
    }

    public class CartItemHolder extends RecyclerView.ViewHolder {
        ImageView ivItem, ivIncrease, ivDecrease;
        TextView tvName, tvPrice, tvCount, tvSumPrice;
        private long count;
        private double sumPrice;
        private Context context;
        private CartItemManager cartItemManager;
        private CartItem item;
        public CartItemHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            cartItemManager = CartItemManager.getInstance(context);

            ivItem = itemView.findViewById(R.id.ivItem);
            ivIncrease = itemView.findViewById(R.id.ivIncrease);
            ivDecrease = itemView.findViewById(R.id.ivDecrease);

            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvSumPrice = itemView.findViewById(R.id.tvSumPrice);
        }


        public void bind(CartItem cartItem, int position) {

//            ivItem.setImageResource(R.drawable.watch_sport_s03);
            ivIncrease.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            ivDecrease.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);

            tvName.setText(cartItem.getName());
            tvPrice.setText(Double.toString(cartItem.getPrice()));
            tvCount.setText(Long.toString(cartItem.getCount()));
            tvSumPrice.setText(Double.toString(cartItem.getSumPrice()));

            String url = cartItem.getThumbnail();
            ImageLoader imageLoader = new ImageLoader();
            imageLoader.execute(url);
//            String short_name;

            ivIncrease.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                        count = cartItem.getCount() + 1;
                        cartItem.setCount(cartItem.getCount() + 1);
//                        sumPrice = cartItem.getSumPrice() + cartItem.getPrice();
                        cartItem.setSumPrice(cartItem.getSumPrice() + cartItem.getPrice());
                        cartItemManager.update(cartItem);
                    notifyItemChanged(position);
                }
            });

            ivDecrease.setOnClickListener(new View.OnClickListener(){
                String short_name = cartItem.getName().split(",")[0];

                @Override
                public void onClick(View v) {
                    if(cartItem.getCount() > 1){
                        cartItem.setCount(cartItem.getCount() - 1);
//                        sumPrice = cartItem.getSumPrice() + cartItem.getPrice();
                        cartItem.setSumPrice(cartItem.getSumPrice() - cartItem.getPrice());
                        cartItemManager.update(cartItem);
                        notifyItemChanged(position);

                    }else{
                        deleteCartItem(short_name, cartItem, position);
//                        cartItemManager.delete(cartItem.getId());
//                        notifyItemRemoved(position);
//                        Toast.makeText(context, "Deleted " + short_name, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void deleteCartItem(String short_name, CartItem cartItem, int position){
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Are you sure to delete " + short_name)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cartItemManager.delete(cartItem.getId());
                            cartItems.remove(cartItem);
                            notifyItemRemoved(position);
                            Toast.makeText(context, "Deleted " + short_name, Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).show();
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
                ivItem.setImageBitmap(bitmap);
            }
        }
    }
}
