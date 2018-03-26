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

    //Declare a variable that will reference the progress bar.
    private ProgressBar mProgressBar;

    //This method is called when this fragment is created. It returns the root view for this fragment (fragment_scan_processing.xml)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Inflate our root view.
        View rootView = inflater.inflate(R.layout.fragment_scan_processing, container, false);
        //Initialise our mProgressBar variable.
        mProgressBar = rootView.findViewById(R.id.scanProgressBar);

        return rootView;
    }

    //This method is called once this fragment has been created.
    @Override
    public void onResume() {
        super.onResume();

        new processImage().execute("s"); //Execute our asynchronous task that simulates a delay.
    }

    //An asynchronous task that simply simulates a 2.5 second pause before moving swapping to the 'save' fragment.
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

        //Once we are finished waiting the 2.5 seconds. Tell our root container to swap this fragment with the save scan fragment.
        @Override
        protected void onPostExecute(String s) {
            ScanSaveFragment newFragment = new ScanSaveFragment();
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener)getActivity().getFragmentManager().
                    findFragmentById(R.id.content_frame);

            fragmentChangeListener.replaceFragment(newFragment);
        }
    }
}

