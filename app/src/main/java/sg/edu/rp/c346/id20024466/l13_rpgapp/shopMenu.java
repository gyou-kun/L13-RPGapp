package sg.edu.rp.c346.id20024466.l13_rpgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class shopMenu extends AppCompatActivity {
    EditText etName, etDescription, etPrice;
    Button btnInsert, btnShowList;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_menu);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDesc);
        etPrice = findViewById(R.id.etPrice);
        btnInsert = findViewById(R.id.btnInsertWeapon);
        btnShowList = findViewById(R.id.btnShowList);
        ratingBar = findViewById(R.id.ratingBar);

        btnInsert.setBackgroundResource(R.drawable.button);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (name.length() == 0 || description.length() == 0){
                    Toast.makeText(shopMenu.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String price_str = etPrice.getText().toString().trim();
                int price = Integer.valueOf(price_str);
                int stars = (int) ratingBar.getRating();

                DBHelper dbh = new DBHelper(shopMenu.this);
                long result = dbh.insertWeapon(name, description, price, stars);

                if (result != -1) {
                    Toast.makeText(shopMenu.this, "Song inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etDescription.setText("");
                    etPrice.setText("");
                } else {
                    Toast.makeText(shopMenu.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(shopMenu.this, weaponList.class);
                startActivity(i);
            }
        });

    }

    private float getStars() {
        float stars = (int) ratingBar.getRating();
    return stars;
    }

}