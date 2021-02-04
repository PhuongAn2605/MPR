package fit.hanu.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int image[] = {R.drawable.a,
            R.drawable.e,
            R.drawable.i,
            R.drawable.o,
            R.drawable.u,
            R.drawable.a,
            R.drawable.e,
            R.drawable.i,
            R.drawable.o,
            R.drawable.u
    };
    GridView gridView;
    ArrayList<imageModel> arrayList;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModel imagemodel = new imageModel();
            imagemodel.setmThumbIds(image[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        imageAdapter adpter= new imageAdapter(getApplicationContext(), arrayList);
        gridView.setAdapter(adpter);
        //item click listner
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            }
        });
    }
}