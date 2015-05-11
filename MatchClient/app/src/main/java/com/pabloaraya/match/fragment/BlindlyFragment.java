package com.pabloaraya.match.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonFloat;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.pabloaraya.match.MainActivity;
import com.pabloaraya.match.R;
import com.pabloaraya.match.adapter.CardAdapter;
import com.pabloaraya.match.model.CardModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BlindlyFragment extends Fragment {

    private CardAdapter cardAdapter;
    private ButtonFloat buttonActionLike;
    private ButtonFloat buttonActionNotLike;
    private SwipeFlingAdapterView flingContainer;

    public static BlindlyFragment newInstance() {
        BlindlyFragment fragment = new BlindlyFragment();
        return fragment;
    }

    public BlindlyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardAdapter = new CardAdapter(getActivity());

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                getString(R.string.url_socket).concat(getString(R.string.method_rest_get_cards)),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                CardModel cardModel = new CardModel();
                                cardModel.setFirstName(jsonObject.getString("name"));
                                cardModel.setText(jsonObject.getString("text"));
                                cardModel.setAge(jsonObject.getInt("age"));
                                cardAdapter.add(cardModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", "ERROR");
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        MainActivity.queue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thinker, container, false);

        buttonActionLike = (ButtonFloat)view.findViewById(R.id.buttonActionLike);
        buttonActionLike.setRippleSpeed(10);
        buttonActionLike.setBackgroundColor(getResources().getColor(R.color.app_color));
        buttonActionLike.setDrawableIcon(getResources().getDrawable(R.drawable.action_like));

        buttonActionNotLike = (ButtonFloat)view.findViewById(R.id.buttonActionNotLike);
        buttonActionNotLike.setRippleSpeed(10);
        buttonActionNotLike.setRippleColor(getResources().getColor(R.color.accent_color));
        buttonActionNotLike.setBackgroundColor(getResources().getColor(android.R.color.white));
        buttonActionNotLike.setDrawableIcon(getResources().getDrawable(R.drawable.action_notlike));

        flingContainer = (SwipeFlingAdapterView)view.findViewById(R.id.frame);
        flingContainer.setAdapter(cardAdapter);

        buttonActionLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectRight();
            }
        });
        buttonActionNotLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectLeft();
            }
        });

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                cardAdapter.remove(0);
            }

            @Override
            public void onLeftCardExit(Object o) {
                // NO
                CardModel cardModel = (CardModel)o;
            }

            @Override
            public void onRightCardExit(Object o) {
                // YES !
                CardModel cardModel = (CardModel)o;
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                Log.e("CLOSE EMPTY", String.valueOf(i));
                if(i == 0){
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET,
                            "http://192.168.56.1/get",
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for(int i = 0; i < jsonArray.length(); i++){
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            CardModel cardModel = new CardModel();
                                            cardModel.setFirstName(jsonObject.getString("name"));
                                            cardModel.setText(jsonObject.getString("text"));
                                            cardModel.setAge(jsonObject.getInt("age"));
                                            cardAdapter.add(cardModel);
                                        }
                                        cardAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "ERROR");
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();

                            return params;
                        }
                    };

                    MainActivity.queue.add(stringRequest);
                }
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        return view;
    }

}
