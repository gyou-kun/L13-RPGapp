package sg.edu.rp.c346.id20024466.l13_rpgapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Weapon> weaponList;

    public CustomAdapter(Context context, int resource, ArrayList<Weapon> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        weaponList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvPrice = rowView.findViewById(R.id.tvPrice);
        RatingBar rating = rowView.findViewById(R.id.ratingBar);
        TextView tvDescription = rowView.findViewById(R.id.tvDesc);

        // Obtain the Android Version information based on the position
        Weapon currentVersion = weaponList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getName());
        tvPrice.setText("" + currentVersion.getPrice());
        tvDescription.setText(currentVersion.getDescription());

        //rating.setClickable(false);
        //rating.setRating(currentVersion.getStars());
        return rowView;
    }


}
