package fit.hanu.hellojapan.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import fit.hanu.hellojapan.R;
import fit.hanu.hellojapan.models.Image;
import fit.hanu.hellojapan.models.Sound;


public class ImagesRecViewAdapter extends RecyclerView.Adapter<ImagesRecViewAdapter.ImageHolder>{
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Sound> soundId = new ArrayList<>();
    private Context context;
    private boolean changeAlpha;
    private MediaPlayer sound;



    public ImagesRecViewAdapter(Context context, boolean changeAlpha) {

        this.context = context;
        this.changeAlpha = changeAlpha;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_letter, parent, false);
        ImageHolder holder = new ImageHolder(view, context, changeAlpha);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Image friend = images.get(position);
        holder.bind(friend);

    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public void refreshView(int position){
        notifyItemChanged(position);
    }

    public class ImageHolder extends RecyclerView.ViewHolder{

        private ImageView imgLetter;
        private ImageView imgLetterHi;
        private ImageView imgLetterKa;
        private Context context;
        private boolean changeAlpha;

        public ImageHolder(@NonNull View itemView, Context context, boolean changeAlpha) {
            super(itemView);

            this.context = context;
            this.changeAlpha = changeAlpha;

            imgLetter = itemView.findViewById(R.id.imgLetterHi);
            imgLetterHi = itemView.findViewById(R.id.imgLetterHi);
            imgLetterKa = itemView.findViewById(R.id.imgLetterKa);

        }
            public void bind(Image image){
                int position = images.indexOf(image);
                if(this.changeAlpha == false){
                    imgLetterHi.setImageResource(images.get(position).getImageId());
                    imgLetterHi.animate().alpha(1).setDuration(1000);
                    imgLetterKa.animate().alpha(0).setDuration(500);
                    imgLetter = itemView.findViewById(R.id.imgLetterHi);
                }else{
                    imgLetterKa.setImageResource(images.get(position).getImageId());
                    imgLetterHi.animate().alpha(0).setDuration(500);
                    imgLetterKa.animate().alpha(1).setDuration(1000);
                    imgLetter = itemView.findViewById(R.id.imgLetterKa);
                }

                imgLetter.setOnClickListener(new View.OnClickListener() {
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

    }
}
