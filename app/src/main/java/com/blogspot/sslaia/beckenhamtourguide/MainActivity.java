package com.blogspot.sslaia.beckenhamtourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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

        Fragment fragment = new Fragment();
        // This part shows corresponding fragment to the one clicked on the drawer
        switch (menuItem.getItemId()) {
            case R.id.drawer_menu_welcome:
                fragment = new WelcomeFragment();
                break;
            case R.id.drawer_menu_celebrities:
                fragment = new CelebritiesFragment();
                break;
            case R.id.drawer_menu_history:
                fragment = new HistoryFragment();
                break;
            case R.id.drawer_menu_entertainment:
                fragment = new EntertainmentFragment();
                break;
            case R.id.drawer_menu_restaurants:
                fragment = new RestaurantFragment();
                break;
            case R.id.drawer_menu_accommodation:
                fragment = new AccommodationFragment();
                break;
            case R.id.drawer_menu_gallery:
                fragment = new GalleryFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).addToBackStack(null).commit();

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
                Intent menuShare = new Intent(Intent.ACTION_SEND);
                menuShare.setType("text/plain");
                menuShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                menuShare.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_tag_line));
                if (menuShare.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(menuShare, getString(R.string.share_this)));
                }
                return true;
            case R.id.tbmenu_feedback:
                Intent menuFeedback = new Intent(Intent.ACTION_SENDTO);
                menuFeedback.setData(Uri.parse("mailto:laia.sirus@gmail.com"));
                menuFeedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject));
                menuFeedback.putExtra(Intent.EXTRA_TEXT, "Dear Sir");
                if (menuFeedback.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(menuFeedback, getString(R.string.share_this)));
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