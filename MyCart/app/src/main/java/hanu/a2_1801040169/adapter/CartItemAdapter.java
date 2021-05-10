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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import hanu.a2_1801040169.CartActivity;
import hanu.a2_1801040169.R;
import hanu.a2_1801040169.db.ProductManager;
import hanu.a2_1801040169.models.Product;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemHolder> {

    public List<Product> cartItems;
    private Context context;
    private double totalPrice;
    private CartActivity cartActivity;

    private IClickChangeTotalPriceListener iClickChangeTotalPriceListener;

    public interface IClickChangeTotalPriceListener {
        void onCLickChangeTotalPrice(ImageView iv, Product cartItem);
    }

    //
    public CartItemAdapter(List<Product> cartItems, double totalPrice, Context context, CartActivity cartActivity) {
        this.cartItems = cartItems;
        this.context = context;
        this.totalPrice = totalPrice;
        this.cartActivity = cartActivity;
    }

    public CartItemAdapter(List<Product> cartItems, IClickChangeTotalPriceListener listener) {
        this.iClickChangeTotalPriceListener = listener;
        this.cartItems = cartItems;
        notifyDataSetChanged();
//        this.context = context;
//        this.totalPrice = totalPrice;
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
        Product cartItem = cartItems.get(position);

        holder.bind(cartItem, position);

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartItemHolder extends RecyclerView.ViewHolder {
        public ImageView ivItem, ivIncrease, ivDecrease;
        private TextView tvName, tvPrice, tvCount, tvSumPrice;
        private CardView cvItem;
        private long count;
        private double sumPrice;
        private Context context;
        private ProductManager productManager;
        private Product item;
        String short_name;

        public CartItemHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            productManager = productManager.getInstance(context);

            ivItem = itemView.findViewById(R.id.ivItem);
            ivIncrease = itemView.findViewById(R.id.ivIncrease);
            ivDecrease = itemView.findViewById(R.id.ivDecrease);

            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvSumPrice = itemView.findViewById(R.id.tvSumPrice);

            cvItem = itemView.findViewById(R.id.cvItem);
//            tvTotalPriceAdapter = cartActivity.findViewById(R.id.tvTotalPrice);
        }


        public void bind(Product cartItem, int position) {

//            ivItem.setImageResource(R.drawable.watch_sport_s03);
            ivIncrease.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            ivDecrease.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);

            short_name = cartItem.getName().split(",")[0];

            tvName.setText(short_name);
            tvPrice.setText(cartItem.formatPrice(cartItem.getPrice()));
            tvCount.setText(Long.toString(cartItem.getCount()));
            tvSumPrice.setText(cartItem.formatPrice(cartItem.getSumPrice()));

            String url = cartItem.getThumbnail();
            ImageLoader imageLoader = new ImageLoader();
            imageLoader.execute(url);
//            String short_name;

            cvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("LONG CLICK", "clicked");
                    deleteProduct(short_name, cartItem, position);
                    return true;

                }
            });


            ivIncrease.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                        count = Product.getCount() + 1;
                    iClickChangeTotalPriceListener.onCLickChangeTotalPrice(ivIncrease, cartItem);
                    cartItem.setCount(cartItem.getCount() + 1);
//                        sumPrice = Product.getSumPrice() + Product.getPrice();
//                        cartItem.setSumPrice(cartItem.getSumPrice() + cartItem.getPrice());
                    productManager.update(cartItem);
                    totalPrice = cartItem.getSumPrice();
//                    Log.d("TOTAL", totalPrice + "");
//                    tvTotalPriceAdapter.setText(cartItem.formatPrice(totalPrice));
                    notifyItemChanged(position);

//                    cartItems.set(position, cartItem);
//                    Log.d("CART", cartItems.toString());
//                    CartActivity cartActivity = new CartActivity();
//                    Log.d("CART BEFORE", cartActivity.getTotalPrice() + "");

//                    cartActivity.setTotalPrice(totalPrice);
//                    notifyDataSetChanged();
//                    Log.d("CART", cartActivity.getTotalPrice() + "");

//                    cartActivity.tvTotalPrice.setText(cartItem.formatPrice(totalPrice));
//                    notifyItemChanged(position);

//                    notifyDataSetChanged();


                }
            });

            ivDecrease.setOnClickListener(new View.OnClickListener() {
                //                String short_name = product.getName().split(",")[0];
                @Override
                public void onClick(View v) {
                    if (cartItem.getCount() > 1) {
                        iClickChangeTotalPriceListener.onCLickChangeTotalPrice(ivDecrease, cartItem);

                        cartItem.setCount(cartItem.getCount() - 1);
//                        sumPrice = Product.getSumPrice() + Product.getPrice();
//                        cartItem.setSumPrice(cartItem.getSumPrice() - cartItem.getPrice());
                        productManager.update(cartItem);
                        notifyItemChanged(position);
//                        notifyDataSetChanged();


                    } else {
                        deleteProduct(short_name, cartItem, position);
//                        CartItemManager.delete(Product.getId());
//                        notifyItemRemoved(position);
//                        Toast.makeText(context, "Deleted " + short_name, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void deleteProduct(String short_name, Product cartItem, int position) {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Are you sure to delete " + short_name)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iClickChangeTotalPriceListener.onCLickChangeTotalPrice(ivDecrease, cartItem);
                            productManager.delete(cartItem.getId());
                            cartItems.remove(cartItem);
                            notifyItemRemoved(position);
//                            notifyDataSetChanged();

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
