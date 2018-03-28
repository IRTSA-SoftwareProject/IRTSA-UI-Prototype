package com.swinburne.lionel.irtsa_ui_prototype;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/*
 * This is the main 'Activity' of our application.
 * This java class represents the initial screen that is loaded when we enter the application.
 *
 * This 'initial screen' is never dismissed. It contains container views that contain fragments.
 * This allows for a modular design of our application.
 */

public class MainActivity extends AppCompatActivity {

    //Member variables that represent views in our activity
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //We use this method to specify the layout file for this activity.
        setContentView(R.layout.activity_main);

        initialiseUI(savedInstanceState);
    }

    //A method to initialise the different UI elements on screen.
    private void initialiseUI(Bundle savedInstanceState) {

        //Initialise our UI elements using IDs specified in the XML layout file (activity_main.xml)
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mToolBar = findViewById(R.id.toolbar);

        //Check if we are loading the screen for the first time, if we are insert the fragment that contains our scan UI.
        if (savedInstanceState == null){
            mNavigationView.setCheckedItem(R.id.nav_camera);
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new ScanFragmentContainer()).commit();
        }

        //Setup our action bar.
        setSupportActionBar(mToolBar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        //Here we specify a listener that listens for when an item is pressed on our nav drawer.
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            // Check if a user selects the menu option with id nav_camera (scan option).
                            case R.id.nav_camera:
                                //Check if we are already displaying the selected item.
                                if (!item.isChecked()) {
                                    mDrawerLayout.closeDrawer(GravityCompat.START); //Close the nav drawer.
                                    //Insert the ScanFragmentContainer fragment into the container view represented by ID: 'content_frame'
                                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new ScanFragmentContainer()).commit();
                                    break;
                                }
                        }
                        return true;
                    }
                }
        );
    }


    //This method is called when the user selects the menu icon in our action bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START); //Open the nav drawer.
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}