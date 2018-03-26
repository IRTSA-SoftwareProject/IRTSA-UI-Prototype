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
 * This 'initial screen' is never dismissed. It contains containers that contain fragments.
 * This allows for a modular design of our application.
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; //A variable representing the nav drawer.
    private NavigationView mNavigationView;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //We use this method to specify the layout file for this activity.
        setContentView(R.layout.activity_main);

        //A method that initialises the different UI elements on screen.
        initialiseUI(savedInstanceState);
    }

    private void initialiseUI(Bundle savedInstanceState) {

        //Initialise our UI elements using IDs specified in the XML layout file (activity_main.xml)
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mToolBar = findViewById(R.id.toolbar);

        /*
        Check if this is the first time loading this screen. If it is we insert the ScanFragmentContainer.
        By inserting the ScanFragmentContainer here, we are specifying it to be the initial content
        that loads when the user opens the app.
         */
        if (savedInstanceState == null){
            mNavigationView.setCheckedItem(R.id.nav_camera);
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new ScanFragmentContainer()).commit();
        }

        setSupportActionBar(mToolBar); //Specify we want to use an actionbar at the top of our application.
        ActionBar actionbar = getSupportActionBar(); //Create an object represnting the action bar.
        actionbar.setDisplayHomeAsUpEnabled(true); //Make the Nav Drawer icon visible in the top left of the actionbar.
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp); //Set the image we want to use for the top left of the actionbar.

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
                                    //Insert the ScanFragmentContainer into the container with ID: 'content_frame'
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