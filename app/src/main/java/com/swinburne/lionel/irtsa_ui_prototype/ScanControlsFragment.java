package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This class represents the Scan Controls fragment we insert into the bottom of our ScanFragmentContainer.
 */

public class ScanControlsFragment extends Fragment {

    //A member variable which represents the scan button.
    private Button mStartScanButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentRootView = inflater.inflate(R.layout.fragment_scan_controls, container, false);

        mStartScanButton = fragmentRootView.findViewById(R.id.beginScanButton);

        //Set the listener that listens for clicks on the start scan button.
        mStartScanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Create and insert the 'Processing' fragment (into the bottom container) after we press the start scan button.
                ScanProcessingFragment newFragment = new ScanProcessingFragment();
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener)getActivity().getFragmentManager().
                        findFragmentById(R.id.content_frame);

                fragmentChangeListener.replaceFragment(newFragment);
            }
        });
        return fragmentRootView;
    }

}
