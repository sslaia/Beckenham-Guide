package com.blogspot.sslaia.beckenhamtourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Global variables
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First load the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Also load the navigation drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.drawer_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // This is only for accessibility purpose
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // This make sure that the first fragment is loaded when activity first starts
        // or but not when other fragment is displayed, hence if statement
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WelcomeFragment()).commit();
            navigationView.setCheckedItem(R.id.drawer_menu_welcome);
        }
    }

    // Define what happens if one of the items on the drawer is clicked
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // This part shows corresponding fragment to the one clicked on the drawer
        switch (menuItem.getItemId()) {
            case R.id.drawer_menu_welcome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WelcomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_celebrities:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CelebritiesFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_entertainment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EntertainmentFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_restaurants:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RestaurantFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_accommodation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccommodationFragment()).addToBackStack(null).commit();
                break;
            case R.id.drawer_menu_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GalleryFragment()).addToBackStack(null).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Make sure the drawer close when not needed
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Inflate the toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Define what happens if one of the items on toolbar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // This part deals with the action taken if item is selected on the toolbar
        switch (item.getItemId()) {
            case R.id.tbmenu_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EventsFragment()).addToBackStack(null).commit();
                return true;
            case R.id.tbmenu_share:
                Toast.makeText(this, "Share is selected", Toast.LENGTH_SHORT).show();
                Intent menuShare = new Intent(Intent.ACTION_SEND);
                menuShare.setType("text/plain");
                menuShare.putExtra(Intent.EXTRA_SUBJECT, "Beckenham Tour Guide");
                menuShare.putExtra(Intent.EXTRA_TEXT, "Your guide in and around Beckenham");
                if (menuShare.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(menuShare, "Share this text"));
                }
                return true;
            case R.id.tbmenu_feedback:
                Toast.makeText(this, "Feedback is selected", Toast.LENGTH_SHORT).show();
                Intent menuFeedback = new Intent(Intent.ACTION_SENDTO);
                menuFeedback.setData(Uri.parse("mailto:laia.sirus@gmail.com"));
                menuFeedback.putExtra(Intent.EXTRA_SUBJECT, "My Feedback to Beckenham Tour Guide");
                menuFeedback.putExtra(Intent.EXTRA_TEXT, "Dear Sir");
                if (menuFeedback.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(menuFeedback, "Share this content"));
                }
                return true;
            case R.id.tbmenu_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}