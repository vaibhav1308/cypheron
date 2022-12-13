package com.sgb.www.cypheron.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sgb.www.cypheron.R;
import com.sgb.www.cypheron.ciphers.*;

public class Decrypt extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    private String selection;

    public Decrypt() {
        // Required empty public constructor
    }

    public static Decrypt newInstance(String param1, String param2) {
        Decrypt fragment = new Decrypt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /* paste button code */
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_decrypt, container, false);

        //Initialize and assign variable (Decryption Program)

        //Initiate components of Decrypt fragment
        EditText plaintext = view.findViewById(R.id.DePlainText);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.fill_ciphers);
        EditText key = view.findViewById(R.id.DeKey);
        TextInputLayout textInputLayout = view.findViewById(R.id.DeKeyTextLayout);
        Button DoWork = view.findViewById(R.id.DecBtn);
        EditText ShowText = view.findViewById(R.id.DeShowView);

        //Cipher name list for DropDown menu
        String[] ciphers = new String[]{
                "Monoalphabetic","Caesar","Atbash","Vernam","AES","Rail Fence"
        };

        //Adapter for String List
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.options_for_cipher,
                ciphers
        );
        autoCompleteTextView.setAdapter(adapter);

        //Setting the default visibility of key TextBox
        textInputLayout.setVisibility(View.GONE);

        //Enabling TextBox for key for specific ciphers
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selection = autoCompleteTextView.getEditableText().toString();

                if(selection.equals("Vernam") || selection.equals("AES") || selection.equals("Rail Fence")){
                    textInputLayout.setVisibility(View.VISIBLE);
                }

                if(selection.equals("Monoalphabetic") || selection.equals("Caesar") || selection.equals("Atbash")){

                    textInputLayout.setVisibility(View.GONE);
                }
            }
        });

        //Trigger code for button click
        DoWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mParam1 = plaintext.getText().toString();
                mParam2 = autoCompleteTextView.getEditableText().toString();
                mParam3 = key.getText().toString();


                if(mParam2.equals("Atbash")){
                    Atbash at = new Atbash();
                    ShowText.setText(at.decrypt(mParam1));
                }
                if (mParam2.equals("Monoalphabetic")){
                    Monoalpha ma = new Monoalpha();
                    ShowText.setText(ma.decrypt(mParam1));
                }
                if(mParam2.equals("Caesar")){
                    Caesar ca = new Caesar();
                    ShowText.setText(ca.decrypt(mParam1));
                }


                if(mParam2.equals("AES")){
                    AES as = new AES();
                    ShowText.setText(as.decrypt(mParam1,mParam3));
                }
                if(mParam2.equals("Vernam")){
                    Vernam vm = new Vernam();
                    ShowText.setText(vm.decrypt(mParam1,mParam3));
                }
                if(mParam2.equals("Rail Fence")){
                    RailFence rf = new RailFence();
                    ShowText.setText(rf.decrypt(mParam1,mParam3));
                }
            }
        });

        //Pasting text in text field
        TextInputLayout EndIcon = view.findViewById(R.id.paste);
        EndIcon.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plaintext.setText(Encrypt.copied);
                Toast.makeText(getActivity(),"Pasted",Toast.LENGTH_SHORT).show();
            }
        });

        //Sharing text through button
        ShapeableImageView share = view.findViewById(R.id.ShareButton);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, ShowText.getText().toString());
                intent.setType("text/plain");
                intent = Intent.createChooser(intent,"Share Via");
                startActivity(intent);
            }
        });

        // Return view
        return view;
    }
}