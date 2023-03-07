package com.example.harvestmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.harvestmate.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ImageView book,bookings,records,help;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        book = findViewById(R.id.book_drone);
        bookings = findViewById(R.id.bookings);
        records = findViewById(R.id.records);
        help = findViewById(R.id.help_support);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookdrone = new Intent(HomeActivity.this,BookDrone.class);
                startActivity(bookdrone);
            }
        });


        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarBookDrone.toolbar);
//        binding.appBarBookDrone.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_myaccount, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();

//        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            Intent logout = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(logout);
            finish();
            return true;
        });


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_book_drone);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_drone, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_book_drone);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}