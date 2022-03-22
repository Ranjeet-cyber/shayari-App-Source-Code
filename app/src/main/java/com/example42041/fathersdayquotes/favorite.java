package com.example42041.fathersdayquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class favorite extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    // private AdView adView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
ImageView btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desh);
        btn1=findViewById(R.id.button11);
        btn2=findViewById(R.id.button12);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.DrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.navigation_manu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    //add drawer item intent here and manage drawer items.......

                    case R.id.about:
                        //  Intent in= new Intent(DESC.this,about.class);
                        Toast.makeText(favorite.this, " About", Toast.LENGTH_SHORT).show();
                        //  startActivity(in);
                        About();
                        break;

                    case R.id.PrivcyPolicy:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://latestupdate2020.blogspot.com/2021/09/fathers-day.html")));

                        break;
                    case R.id.More:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=Ranjeetdagar")));

                        break;
                    case R.id.rate:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
                        Toast.makeText(favorite.this, "Rating...me", Toast.LENGTH_SHORT).show();

                        break;


                    case R.id.share:
                        Intent sharing = new Intent(Intent.ACTION_SEND);
                        sharing.setType("text/plain");
                        String sharebody = "http://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID+"\n\n";
                        //paste your link here........
                        String sharesubject="Share App With Your Friends..";
                        sharing.putExtra(Intent.EXTRA_TEXT,sharebody);
                        sharing.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
                        startActivity(Intent.createChooser(sharing,"share using"));
                        Toast.makeText(favorite.this, " share", Toast.LENGTH_SHORT).show();
                        break;


                }
                return false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(favorite.this, "hindi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(favorite.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(favorite.this, "english", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(favorite.this,desh.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void About() {
        AlertDialog.Builder builder = new AlertDialog.Builder(favorite.this,R.style.Theme_MaterialComponents_BottomSheetDialog);
        View view = LayoutInflater.from(favorite.this).inflate(R.layout.aboutt,(ConstraintLayout) findViewById(R.id.layout_countainer));
        builder.setView(view);
        ((TextView) view.findViewById(R.id.text15)).setText(getResources().getString(R.string.app_name));
        ((TextView) view.findViewById(R.id.text161)).setText(getResources().getString(R.string.v));
        ((TextView) view.findViewById(R.id.text16)).setText(getResources().getString(R.string.ranjit));
        ((TextView) view.findViewById(R.id.text162)).setText(getResources().getString(R.string.by));
        //  ((ImageView) view.findViewById(R.id.imageView15)).setImageResource(R.drawable.ic_baseline_person_24);
        ((Button) view.findViewById(R.id.btnNo12)).setText(getResources().getString(R.string.ok));
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.btnNo12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();

            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            alertDialog.show();
        }
    }
}