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

        new processImage().execute("s");
    }

    private class processImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... imageInfo) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            ScanSaveFragment newFragment = new ScanSaveFragment();
            FragmentChangeListener fragmentChangeListener = (FragmentChangeListener)getActivity().getFragmentManager().
                    findFragmentById(R.id.content_frame);

            fragmentChangeListener.replaceFragment(newFragment);
        }
    }
}

