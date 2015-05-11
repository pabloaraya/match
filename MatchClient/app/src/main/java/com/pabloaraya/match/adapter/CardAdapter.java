package com.pabloaraya.match.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.pabloaraya.match.R;
import com.pabloaraya.match.model.CardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 2/27/15.
 */
public class CardAdapter extends BaseAdapter {

    private Context context;
    private Typeface robotoLight;
    private List<CardModel> cards;
    private LayoutInflater li;

    public CardAdapter(Context context){
        this.context    = context;
        cards = new ArrayList<CardModel>();
        li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        robotoLight     = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public CardModel getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(CardModel cardModel){
        cards.add(cardModel);
    }
    public void remove(int position){
        if(getCount() > 0) {
            cards.remove(position);
            notifyDataSetChanged();
        }
    }
    public void remove(String user_id){
        if(getCount() > 0){
            for(CardModel card : cards){
                if(card.getUserId().equals(user_id)){
                    cards.remove(card);
                    notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CardHolder viewHolder;
        if(cards != null && cards.size() > 0) {

            CardModel card = getItem(position);
            if (convertView == null) {
                viewHolder = new CardHolder();

                convertView = li.inflate(R.layout.text_card, parent, false);

                viewHolder.textViewText = (TextView) convertView.findViewById(R.id.textViewText);
                viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);

                viewHolder.textViewText.setTypeface(robotoLight);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (CardHolder) convertView.getTag();
            }

            viewHolder.textViewText.setText(card.getText());
            viewHolder.textViewName.setText(card.getFirstName()
                    .concat(", ")
                    .concat(String.valueOf(card.getAge())));
        }

        return convertView;
    }
}
class CardHolder{
    TextView textViewText;
    TextView textViewName;
}