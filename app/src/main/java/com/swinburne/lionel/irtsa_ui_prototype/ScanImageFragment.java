package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class represents the image fragment we insert into the top of our ScanFragmentContainer.
 * All this class does is inflate the fragment_scan_image layout file which contains a single empty ImageView.
 */

public class ScanImageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scan_image, container, false);
    }
}
