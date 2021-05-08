package fit.hanu.hellojapan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import fit.hanu.hellojapan.adapters.ImagesRecViewAdapter;
import fit.hanu.hellojapan.models.Image;

public class MainActivity<onClick> extends AppCompatActivity {

    private RecyclerView imgRecViewHi;
    private RecyclerView imgRecViewKa;

    private ArrayList<String> imagesNameHi = new ArrayList<>();
    private ArrayList<Image> images;
    private ArrayList<Image> imagesHi;
    private ArrayList<Image> imagesKa;

    private ArrayList<String> imagesNameKa = new ArrayList<>();

    private Button btnHira;
    private Button btnKata;
    private TextView txtTitle;

    private ImagesRecViewAdapter adapter;
    private ImagesRecViewAdapter adapterHi;
    private ImagesRecViewAdapter adapterKa;


    private boolean changeAlpha = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgRecViewHi = findViewById(R.id.imgRecViewHi);
        imgRecViewKa = findViewById(R.id.imgRecViewKa);

        btnHira = findViewById(R.id.btnHira);
        btnKata = findViewById(R.id.btnKata);
        txtTitle = findViewById(R.id.txtTitle);

        Field[] drawables = R.drawable.class.getFields();

        Field f;
        for (int i = 0; i < drawables.length; i++) {
            f = drawables[i];
            try {
                if (f.getName().length() < 15 && f.getName().contains("hira")) {
                    imagesNameHi.add(f.getName());

                }else if(f.getName().length() < 15 && f.getName().contains(("k"))){
                    imagesNameKa.add(f.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        onClickChange(imagesNameHi, false);

        btnHira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAlpha = false;
                txtTitle.setText("Hiragana");

                imgRecViewHi.scrollToPosition(0);
                imgRecViewHi.setVisibility(View.VISIBLE);
                imgRecViewKa.setVisibility(View.GONE);

            }
        });

        btnKata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAlpha = true;
                txtTitle.setText("Katakana");
                if(adapterKa == null || adapterKa.getItemCount() == 0){
                    onClickChange(imagesNameKa, true);
                }
                imgRecViewKa.scrollToPosition(0);
                imgRecViewHi.setVisibility(View.GONE);
                imgRecViewKa.setVisibility(View.VISIBLE);

            }
        });
    }


    public void onClickChange(ArrayList<String> imagesName, boolean changeAlpha){
        images = new ArrayList<>();
        int[] imgId = new int[imagesName.size()];
        for (int i = 0; i < imagesName.size(); i++) {
            imgId[i] = getResources().getIdentifier(imagesName.get(i), "drawable", getPackageName());
        }

        for (int i = 0; i < imgId.length; i++) {
            Image img = new Image(imgId[i], imagesName.get(i));

            images.add(img);
        }

        if(!changeAlpha){
            imagesHi = new ArrayList<>(images);

            adapterHi = new ImagesRecViewAdapter(this,false);
            adapterHi.setImages(imagesHi);
            imgRecViewHi.setAdapter(adapterHi);

            imgRecViewHi.setLayoutManager(new GridLayoutManager(this, 5));

        }else{
            imagesKa = new ArrayList<>(images);

            adapterKa = new ImagesRecViewAdapter(this,true);
            adapterKa.setImages(imagesKa);

            imgRecViewKa.setAdapter(adapterKa);
            imgRecViewKa.setLayoutManager(new GridLayoutManager(this, 5));

        }
    }


}