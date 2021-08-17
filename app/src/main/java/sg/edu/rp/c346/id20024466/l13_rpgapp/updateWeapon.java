package sg.edu.rp.c346.id20024466.l13_rpgapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class updateWeapon extends AppCompatActivity {

    EditText etName, etDescription, etPrice, etID;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_weapon);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_update_weapon));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etID = (EditText) findViewById(R.id.etID);
        ratingBar = (RatingBar) findViewById(R.id.ratingStars1);

        Intent i = getIntent();
        final Weapon currentWeapon = (Weapon) i.getSerializableExtra("weapon");

        etID.setText(currentWeapon.getId()+"");
        etName.setText(currentWeapon.getId()+"");
        etDescription.setText(currentWeapon.getDescription());
        etPrice.setText(currentWeapon.getPrice()+"");
        //int stars = (int) ratingBar.getRating();
        currentWeapon.setStars((int) ratingBar.getRating());
        ratingBar.setRating(currentWeapon.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(updateWeapon.this);
                currentWeapon.setName(etName.getText().toString().trim());
                currentWeapon.setDescription(etDescription.getText().toString().trim());
                int price = 0;
                try {
                    price = Integer.valueOf(etPrice.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(updateWeapon.this, "Invalid price", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentWeapon.setPrice(price);

                ratingBar.setRating(currentWeapon.getStars());
                int result = dbh.updateWeapon(currentWeapon);
                if (result>0){
                    Toast.makeText(updateWeapon.this, "Weapon updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(updateWeapon.this, "Weapon failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(updateWeapon.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the weapon, " + currentWeapon.getName() + "?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myBuilder.setNeutralButton("Cancel", null);

                        DBHelper dbh = new DBHelper(updateWeapon.this);
                        int result = dbh.deleteWeapon(currentWeapon.getId());
                        if (result>0){
                            Toast.makeText(updateWeapon.this, "Weapon deleted", Toast.LENGTH_SHORT).show();
                            Intent y = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(updateWeapon.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(updateWeapon.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Do you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do not discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }


}