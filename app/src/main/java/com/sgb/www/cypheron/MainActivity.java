 package com.sgb.www.cypheron;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.sgb.www.cypheron.ui.main.Decrypt;
import com.sgb.www.cypheron.ui.main.Encrypt;
import com.sgb.www.cypheron.ui.main.SaveState;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

 public class MainActivity extends AppCompatActivity {

    //This is bottomSheet
    private BottomSheetDialog bsd;


    SaveState saveState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize button
        ImageButton menu = findViewById(R.id.MenuButton);
        ImageButton darkmode = findViewById(R.id.imageButton2);

//-------------------------- CODE FOR ENCRYPT & DECRYPT TAB ---------------------------------//

        //Initializing the tablayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        ViewPager viewPager = findViewById(R.id.view_pager);

        //Initialize Array and add title in array list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Encrypt");
        arrayList.add("Decrypt");

        //Prepare view pager
        prepareViewPager(viewPager,arrayList);

        //Setup with view pager
        tabLayout.setupWithViewPager(viewPager);

//----------------------------- CODE FOR MENU BUTTON ---------------------------------------//

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creates BottomSheetDialog
                bsd = new BottomSheetDialog(MainActivity.this, R.style.Menu_Theme);

                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_layout,
                        (ViewGroup) findViewById(R.id.menu_sheet));

                //TODO:Generate OnClick For each menu option

                //For Help Button
                sheetView.findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenHelpTab();
                        bsd.dismiss();
                    }
                });
                //For About Button
                sheetView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenAboutTab();
                        bsd.dismiss();
                    }
                });
                //For Settings Button
                sheetView.findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenSettingTab();
                        bsd.dismiss();
                    }
                });
                bsd.setContentView(sheetView);
                bsd.show();
            }

        });
    }

    private void OpenSettingTab() {
        Intent settings = new Intent(this,SettingActivity.class);
        startActivity(settings);
    }

    private void OpenAboutTab() {
        Intent about = new Intent(this,AboutActivity.class);
        startActivity(about);
    }

    private void OpenHelpTab() {
        Intent help = new Intent(this,HelpActivity.class);
        startActivity(help);
    }


//----------------------------- VIEW PAGER FOR TABS --------------------------------------//

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {

        //Initialize main adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        //Initialize both (Encrypt/Decrypt) fragment
        Encrypt fragment1 = new Encrypt();
        Decrypt fragment2 = new Decrypt();

        //Initialize bundle
        Bundle bundle = new Bundle();

        //Put String
        bundle.putString("title",arrayList.get(0));
        bundle.putString("title",arrayList.get(1));

        //Set Argument
        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);

        //Add fragment
        adapter.addFragment(fragment1,arrayList.get(0));
        adapter.addFragment(fragment2,arrayList.get(1));

        //Set Adapter
        viewPager.setAdapter(adapter);
    }


//--------------------------- ADAPTER CODE FOR FRAGMENT -------------------------------//

    private static class MainAdapter extends FragmentPagerAdapter {

        //Initialize Arraylist
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        //Create Constructor
        public void addFragment(Fragment fragment,String title){

            //Add Title
            arrayList.add(title);

            //Add fragment
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //Return Fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //Return Fragment list size
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //Return Array list position
            return arrayList.get(position);
        }
    }
}