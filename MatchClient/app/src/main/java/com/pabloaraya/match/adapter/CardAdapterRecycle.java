package com.pabloaraya.match.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.pabloaraya.match.R;
import com.pabloaraya.match.model.CardModel;
import com.pabloaraya.match.model.CardModelHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 2/25/15.
 */
public class CardAdapterRecycle extends RecyclerView.Adapter<CardModelHolder>{

    private Context context;
    private Typeface robotoLight;
    private List<CardModel> cards;

    public CardAdapterRecycle(Context context, List<CardModel> cards){
        this.context    = context;
        this.cards      = cards;
        robotoLight     = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
    }

    @Override
    public CardModelHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_card, null);
        CardModelHolder mh = new CardModelHolder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(CardModelHolder cardHolder, int i) {

        CardModel cardModel = cards.get(i);

        cardHolder.textViewText.setText(cardModel.getText());
        cardHolder.textViewName.setText(cardModel.getFirstName());

        cardHolder.textViewText.setTypeface(robotoLight);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
