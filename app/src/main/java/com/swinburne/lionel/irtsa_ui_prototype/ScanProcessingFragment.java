package com.swinburne.lionel.irtsa_ui_prototype;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by Lionel on 3/19/2018.
 */

public class ScanProcessingFragment extends Fragment {

    //A member variable to reference the progress bar.
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_processing, container, false);
        mProgressBar = rootView.findViewById(R.id.scanProgressBar);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        new processImage().execute("s"); //Execute our asynchronous task that simulates a delay.
    }

    //An asynchronous task that simulates a 2.5 second pause before moving swapping to the 'save' fragment.
    private class processImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... imageInfo) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Once we are finished waiting the 2.5 seconds. Tell our ScanFragmentContainer container to swap this fragment with the save scan fragment.
        @Override
        protected void onPostExecute(String s) {
            ScanSaveFragment newFragment = new ScanSaveFragment();
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener)getActivity().getFragmentManager().
                    findFragmentById(R.id.content_frame);

            //Hardcoded in ScanFragmentContainer to only swap the lower fragment.
            fragmentChangeListener.replaceFragment(newFragment);
        }
    }
}

