package edu.hanu.myfriendswithdb.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import edu.hanu.myfriendswithdb.R;
import edu.hanu.myfriendswithdb.db.FriendManager;
import edu.hanu.myfriendswithdb.models.Friend;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {
    private List<Friend> friends;
    private Context context;

    public FriendAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_friend, parent, false);

        return new FriendHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, final int position) {
        Friend friend = this.friends.get(position);

        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return this.friends.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageButton btnCall, btnSendSMS, btnSendMail, btnDelete;

        public FriendHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnSendSMS = itemView.findViewById(R.id.btnSendSMS);
            btnSendMail = itemView.findViewById(R.id.btnSendMail);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(final Friend friend) {
            tvName.setText(friend.getName());

            // telephony
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("FRIEND", " " + friend.getId());
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + friend.getPhoneNo()));

                    context.startActivity(intent);
                }
            });


            // send sms
            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:" + friend.getPhoneNo()));

                    context.startActivity(intent);
                }
            });

            // send mail
            btnSendMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"+Uri.encode(friend.getEmail())));

                    context.startActivity(intent);
                }
            });

            // delete item
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("FRIEND", " " + friend.getId());

                    FriendManager friendManager = FriendManager.getInstance(context);
                    friendManager.delete(friend.getId());

                    friends.remove(friend);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
