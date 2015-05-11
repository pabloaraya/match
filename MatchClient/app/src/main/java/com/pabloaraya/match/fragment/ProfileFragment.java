package com.pabloaraya.match.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pabloaraya.match.R;


public class ProfileFragment extends Fragment {

    private EditText editTextPersonal;
    private TextView personalText;
    private Typeface robotoLight;
    private ImageView imageViewEditText;
    private ImageView imageViewSaveText;

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load roboto typeface
        robotoLight     = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Light.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        // Load view elements
        editTextPersonal    = (EditText)v.findViewById(R.id.editTextPersonal);
        personalText        = (TextView)v.findViewById(R.id.text);
        imageViewEditText   = (ImageView)v.findViewById(R.id.imageViewEditText);
        imageViewSaveText   = (ImageView)v.findViewById(R.id.imageViewSaveText);

        // Set roboto typeface roboto light
        editTextPersonal.setTypeface(robotoLight);
        personalText.setTypeface(robotoLight);

        // Set listener to "edit" and "save"
        imageViewEditText.setOnClickListener(listenerButtonEditText);
        imageViewSaveText.setOnClickListener(listenerButtonSaveText);
        return v;
    }

    // Listener to edit text
    View.OnClickListener listenerButtonEditText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Hide my personal message
            personalText.setVisibility(View.INVISIBLE);

            // Show edit text
            editTextPersonal.setVisibility(View.VISIBLE);
            editTextPersonal.setText(personalText.getText());

            // Hide button edit
            imageViewEditText.setVisibility(View.INVISIBLE);

            // Show button save
            imageViewSaveText.setVisibility(View.VISIBLE);
        }
    };

    // Listener to save text
    View.OnClickListener listenerButtonSaveText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Verify if text changed
            if(!editTextPersonal.getText().toString().equalsIgnoreCase(personalText.toString())){
                // Change the text
                personalText.setText(editTextPersonal.getText());

                // Send the new text to server
                // ...

            }

            // Hide text editor
            editTextPersonal.setVisibility(View.INVISIBLE);

            // Show personal text
            personalText.setVisibility(View.VISIBLE);

            // Hide button save
            imageViewSaveText.setVisibility(View.INVISIBLE);

            // Show button edit
            imageViewEditText.setVisibility(View.VISIBLE);
        }
    };
}
