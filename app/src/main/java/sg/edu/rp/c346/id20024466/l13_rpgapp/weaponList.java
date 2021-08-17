package sg.edu.rp.c346.id20024466.l13_rpgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class weaponList extends AppCompatActivity {

    ListView lv;
    ArrayList<Weapon> weaponList;
    //ArrayAdapter adapter;
    CustomAdapter adapter;
    String moduleCode;
    int requestCode = 9;
    Button btn5Stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weapon_list);

        setTitle(getTitle().toString() + " ~ " +  getResources().getText(R.string.title_weapon_list));

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        weaponList = dbh.getAllWeapons();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, weaponList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(weaponList.this, updateWeapon.class);
                i.putExtra("weapon", weaponList.get(position));
                startActivityForResult(i, requestCode);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(weaponList.this);
                weaponList.clear();
                weaponList.addAll(dbh.getAllWeaponByStars(5));
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.requestCode && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
            weaponList.clear();
            weaponList.addAll(dbh.getAllWeapons());
            dbh.close();
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}