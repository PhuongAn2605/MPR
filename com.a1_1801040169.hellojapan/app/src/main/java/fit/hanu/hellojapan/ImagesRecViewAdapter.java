package fit.hanu.hellojapan;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class ImagesRecViewAdapter extends RecyclerView.Adapter<ImagesRecViewAdapter.ViewHolder>{
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Sound> soundId = new ArrayList<>();
    private Context context;
    private boolean changeAlpha;
    private MediaPlayer sound;



    public ImagesRecViewAdapter(Context context, boolean changeAlpha) {

        this.context = context;
        this.changeAlpha = changeAlpha;
//        this.soundId = soundId;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_letter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(this.changeAlpha == false){
            holder.imgLetterHi.setImageResource(images.get(position).getImageId());
            holder.imgLetterHi.animate().alpha(1).setDuration(1000);
            holder.imgLetterKa.animate().alpha(0).setDuration(500);
            holder.imgLetter = holder.itemView.findViewById(R.id.imgLetterHi);
        }else{
            holder.imgLetterKa.setImageResource(images.get(position).getImageId());
            holder.imgLetterHi.animate().alpha(0).setDuration(500);
            holder.imgLetterKa.animate().alpha(1).setDuration(1000);
            holder.imgLetter = holder.itemView.findViewById(R.id.imgLetterKa);
        }

        holder.imgLetter.setOnClickListener(new View.OnClickListener() {
            Field[] fields;
            String[] nameCLicked;
            String imgName;

            @Override
            public void onClick(View v) {
                fields = R.raw.class.getFields();
                int soundId;
                for (int i = 0; i < fields.length; i++) {
                    imgName = images.get(position).getImageName();
                    if (fields[i].getName().contains(imgName)) {
                        nameCLicked = imgName.split("_");
                        Toast.makeText(context, nameCLicked[1], Toast.LENGTH_SHORT).show();
                        soundId = context.getResources().getIdentifier(fields[i].getName(), "raw", context.getPackageName());
                        sound = MediaPlayer.create(context, soundId);
                        if(sound.isPlaying()){
                            sound.reset();
                        }else{
                            sound.start();
                        }
                        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                sound.release();
                            }
                        });
                    }
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public void refreshView(int position){
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgLetter;
        private ImageView imgLetterHi;
        private ImageView imgLetterKa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLetter = itemView.findViewById(R.id.imgLetterHi);
            imgLetterHi = itemView.findViewById(R.id.imgLetterHi);
            imgLetterKa = itemView.findViewById(R.id.imgLetterKa);

        }

    }
}
