package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lionel on 3/18/2018.
 */

public class ScanControlsFragment extends Fragment {

    Button mStartScanButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentRootView = inflater.inflate(R.layout.fragment_scan_controls, container, false);

        mStartScanButton = fragmentRootView.findViewById(R.id.beginScanButton);



        return fragmentRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mStartScanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScanProcessingFragment newFragment = new ScanProcessingFragment();
                FragmentChangeListener fragmentChangeListener = (FragmentChangeListener)getActivity().getFragmentManager().
                        findFragmentById(R.id.content_frame);

                fragmentChangeListener.replaceFragment(newFragment);
            }
        });
    }
}
