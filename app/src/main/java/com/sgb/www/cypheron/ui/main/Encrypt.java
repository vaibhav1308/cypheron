    package com.sgb.www.cypheron.ui.main;

import android.animation.TypeConverter;
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

import com.google.android.material.textfield.TextInputLayout;
import com.sgb.www.cypheron.R;
import com.sgb.www.cypheron.ciphers.*;



public class Encrypt extends Fragment {

    //For storing values from user
    private String mParam1;
    private String mParam2;
    private String mParam3;

    private String selection;

    //For copying from TextBox
    public static String copied;

    public Encrypt() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Initalize view
        View view = inflater.inflate(R.layout.fragment_encrypt, container, false);

        //TODO:Initialize and assign (Encryption Program)

        //Initiate components of Encrypt fragment
        EditText plaintext = view.findViewById(R.id.EnPlainText);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.fill_ciphers);
        Button DoWork = view.findViewById(R.id.EncBtn);
        TextInputLayout textInputLayout = view.findViewById(R.id.EnKeyTextLayout);
        EditText key = view.findViewById(R.id.EnKey);
        EditText ShowText = view.findViewById(R.id.EnShowView);

        //Cipher name list for DropDown menu
        String[] ciphers = new String[]{
                "Monoalphabetic","Caesar","Atbash","Vernam","AES","Rail Fence",
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

               if(selection.equals("Vernam") || selection.equals("AES") || selection.equals("Rail Fence") ){
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
                    ShowText.setText(at.encrypt(mParam1));
                }
                if (mParam2.equals("Monoalphabetic")){
                    Monoalpha ma = new Monoalpha();
                    ShowText.setText(ma.encrypt(mParam1));
                }
                if(mParam2.equals("Caesar")){
                    Caesar ca = new Caesar();
                    ShowText.setText(ca.encrypt(mParam1));
                }


                if(mParam2.equals("AES")){
                    AES as = new AES();
                    ShowText.setText(as.encrypt(mParam1,mParam3));
                }
                if(mParam2.equals("Vernam")){
                    Vernam vm = new Vernam();
                    ShowText.setText(vm.encrypt(mParam1,mParam3));
                }
                if(mParam2.equals("Rail Fence")){
                    RailFence rf = new RailFence();
                    ShowText.setText(rf.encrypt(mParam1,mParam3));
                }
            }
        });

        ImageView imageView = view.findViewById(R.id.Copy);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                copied = ShowText.getText().toString();
                Toast.makeText(getActivity(),"Text Copied",Toast.LENGTH_SHORT).show();
            }
        });
        
        // Return view
        return view;
    }
}