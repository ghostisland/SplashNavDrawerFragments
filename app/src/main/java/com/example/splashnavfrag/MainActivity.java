package com.example.splashnavfrag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIALIZE TOOLBAR AND NAVIGATION DRAWER

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // SET COMPONENTS TO START UP STATE

        drawer = findViewById(R.id.drawer_layout);
        sendButton = findViewById(R.id.sendButton);

        // SET NAVIGATION VIEW TOGGLE

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // code for changes colour of Communicate
        Menu myMenu = navigationView.getMenu();
        MenuItem tools= myMenu.findItem(R.id.communicate);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.MyTheme), 0, s.length(), 0);
        tools.setTitle(s);
        navigationView.setNavigationItemSelectedListener(this);

        // THIS IS ONLY USED WHEN A FRAGMENT IS OPENED ON CREATE

        //if (savedInstanceState == null) {
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
        //new MessageFragment()).commit();
        //navigationView.setCheckedItem(R.id.nav_message);
        //}

        // CLOSE KEYBOARD WHEN NAVIGATION DRAWER OPENED

        ActionBarDrawerToggle toggle2 = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {

                // DO SOMETHING HERE...

                super.onDrawerClosed(drawerView);

                //InputMethodManager inputMethodManager = (InputMethodManager)
                //getSystemService(Context.INPUT_METHOD_SERVICE);
                //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                // DO SOMETHING HERE...

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                // DO SOMETHING HERE...

                super.onDrawerOpened(drawerView);

                //InputMethodManager inputMethodManager = (InputMethodManager)
                //getSystemService(Context.INPUT_METHOD_SERVICE);
                //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };

        drawer.addDrawerListener(toggle2);
        toggle2.syncState();

        // DO SOMETHING HERE...

        sendButton.setOnClickListener(v -> {
            // DO SOMETHING HERE...
        });


    }


    // SELECT NAVIGATION DRAWER SCREENS

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                List<Fragment> all_frags = getSupportFragmentManager().getFragments();
                if (all_frags.size() > 0) {
                    for (Fragment frag : all_frags) {
                        getSupportFragmentManager().beginTransaction().remove(frag).commit();
                    }
                }

                // DO SOMETHING HERE...

                break;
            case R.id.nav_export:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExportFragment()).commit();
                sendButton.setEnabled(false);

                // DO SOMETHING HERE...

                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                sendButton.setEnabled(false);

                // DO SOMETHING HERE...

                break;
            case R.id.nav_share:

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    String shareMessage= "\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }

                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_rate:

                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }

                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // CLOSE NAVIGATION DRAWER ON BACK PRESS

    @Override
    public void onBackPressed() {

        // DO SOMETHING HERE...

        List<Fragment> all_frags = getSupportFragmentManager().getFragments();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // DO SOMETHING HERE...

            if (all_frags.size() > 0) {
                for (Fragment frag : all_frags) {
                    getSupportFragmentManager().beginTransaction().remove(frag).commit();

                    sendButton.setEnabled(true);

                    // DO SOMETHING HERE...

                }
            } else {
                onExit();
            }
        }
    }

    // EXIT APP ON BACK PRESS

    public void onExit() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> MainActivity.this.finish())
                .setNegativeButton("No", null)
                .show();
    }


}