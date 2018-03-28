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

    //Member variables representing the fragments that are initially inserted into the top and bottom containers.
    private ScanImageFragment mScanImageFragment;
    private ScanControlsFragment mScanControlsFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //Initialise the fragments we will be inserting into our containers.
        mScanImageFragment = new ScanImageFragment();
        mScanControlsFragment = new ScanControlsFragment();

        //Check if this is the first time we are loading this container. If it is, insert the two initial fragments.
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.scanImageContainer, mScanImageFragment).commit();
            getFragmentManager().beginTransaction().replace(R.id.scanControlsContainer, mScanControlsFragment).commit();
        }

        return inflater.inflate(R.layout.fragment_scan_container, container, false);
    }

    /*
      This method is inherited from the FragmentChangeListener interface.
      It facilitates a child fragments ability to notify its parent fragment that it needs to swap a fragment in its container.
      Currently limited to only change the fragment in the bottom container (image fragment is never swapped out).
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

