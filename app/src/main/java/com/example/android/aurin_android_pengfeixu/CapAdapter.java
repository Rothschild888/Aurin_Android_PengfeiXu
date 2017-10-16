/**
 * Created by PENGFEI XU on 2017.
 * This class defines a adapter for list view
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CapAdapter extends ArrayAdapter<Capabilities> {

    private int resourceId;

    public CapAdapter(Context context, int textViewResourceId, List<Capabilities> objects){
        super (context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Capabilities cap = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView capImage = (ImageView) view.findViewById(R.id.cap_image);
        TextView capText = (TextView) view.findViewById(R.id.cap_name);
        capImage.setImageResource(cap.image_id);
        capText.setText(cap.title);
        return view;
    }
}
