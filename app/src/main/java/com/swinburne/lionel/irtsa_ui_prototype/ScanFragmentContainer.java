package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class is a fragment which can be inserted into a container.
 * This fragment acts as a container for two other fragments.
 * By having this container fragment, we can easily swap out its two children fragments.
 *
 * This container fragment contains two vertically stacked fragments, the top fragment is
 * a fragment which contains our image. The bottom fragment contains our controls and is swapped
 * with other fragments (scan in progress fragment and save image fragment) after a scan to facilitate saving.
 */

public class ScanFragmentContainer extends Fragment implements FragmentChangeListener {

    //Declare the two fragments we will be inserting into our fragment container.
    private ScanImageFragment mScanImageFragment;
    private ScanControlsFragment mScanControlsFragment;

    //This method is called once our view is created.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //Initialise the fragments we will be inserting into our containers.
        mScanImageFragment = new ScanImageFragment();
        mScanControlsFragment = new ScanControlsFragment();

        /*
        savedInstanceState is a variable that allows us to retain information when a screen is \
        recreated. Here we check if savedInstanceState is null, if it is, the user has not yet
        visited this screen and we insert our two fragments.
         */
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.scanImageContainer, mScanImageFragment).commit();
            getFragmentManager().beginTransaction().replace(R.id.scanControlsContainer, mScanControlsFragment).commit();
        }

        //'Inflate' the layout file associated with ScanFragmentContainer.java
        return inflater.inflate(R.layout.fragment_scan_container, container, false);
    }


    /*
      This class implements the 'FragmentChangeListener' interface.
      The below method implements interface method 'replaceFragment'.
      By implenting this method, we are able to swap the fragments in our containers.
      Right now this method is hardcoded to always replace the FrameLayout with ID scanControlsContainer.
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.scanControlsContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

}

