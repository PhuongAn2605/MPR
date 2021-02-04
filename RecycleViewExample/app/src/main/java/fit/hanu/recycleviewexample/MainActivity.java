package fit.hanu.recycleviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contactsRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecView = findViewById(R.id.contactsRecView);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Phuong", "anphuong@gmail.com", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia-cdn.laodong.vn%2FStorage%2FNewsPortal%2F2020%2F10%2F31%2F850352%2FHuonggiang1.jpg%3Fw%3D720%26crop%3Dauto%26scale%3Dboth&imgrefurl=https%3A%2F%2Flaodong.vn%2Fgiai-tri%2Fsao-viet-dap-tra-anti-fan-huong-giang-den-tan-noi-son-tung-mtp-im-lang-850352.ldo&tbnid=mU6GPah07Nla3M&vet=10CAcQxiAoBWoXChMImMj91-q07gIVAAAAAB0AAAAAEAc..i&docid=-E6A1pkk7TgNhM&w=720&h=541&itg=1&q=huong%20giang&ved=0CAcQxiAoBWoXChMImMj91-q07gIVAAAAAB0AAAAAEAc"));
        contacts.add(new Contact("PhuongAn", "anphuong@gmail.com", "https://via.placeholder.com/300.png/09f/fff%20C/O%20https://placeholder.com/"));
        contacts.add(new Contact("Mai Lien", "anphuong@gmail.com", "https://via.placeholder.com/300.png/09f/fff%20C/O%20https://placeholder.com/"));
        contacts.add(new Contact("Phuong", "anphuong@gmail.com", "https://via.placeholder.com/300.png/09f/fff%20C/O%20https://placeholder.com/"));
        contacts.add(new Contact("Phuong", "anphuong@gmail.com", "https://via.placeholder.com/300.png/09f/fff%20C/O%20https://placeholder.com/"));
        contacts.add(new Contact("Phuong", "anphuong@gmail.com", "https://via.placeholder.com/300.png/09f/fff%20C/O%20https://placeholder.com/"));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);
        contactsRecView.setAdapter(adapter);
//        contactsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}