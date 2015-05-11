package com.pabloaraya.match.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pabloaraya.match.R;

/**
 * Created by pablo on 2/27/15.
 */
public class CardModelHolder extends RecyclerView.ViewHolder {

    public TextView textViewText;
    public TextView textViewName;

    public CardModelHolder(View itemView) {
        super(itemView);
        textViewText = (TextView)itemView.findViewById(R.id.textViewText);
        textViewName = (TextView)itemView.findViewById(R.id.textViewName);
    }
}
