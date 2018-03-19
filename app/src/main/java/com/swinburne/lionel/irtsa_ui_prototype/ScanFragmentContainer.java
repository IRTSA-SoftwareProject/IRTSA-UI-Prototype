package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lionel on 18-Mar-18.
 */

public class ScanFragmentContainer extends Fragment implements FragmentChangeListener {

    ScanImageFragment mScanImageFragment;
    ScanControlsFragment mScanControlsFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mScanImageFragment = new ScanImageFragment();
        mScanControlsFragment = new ScanControlsFragment();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.scanImageContainer, mScanImageFragment).commit();
            getFragmentManager().beginTransaction().replace(R.id.scanControlsContainer, mScanControlsFragment).commit();
        }

        return inflater.inflate(R.layout.fragment_scan_container, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.scanControlsContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

}

