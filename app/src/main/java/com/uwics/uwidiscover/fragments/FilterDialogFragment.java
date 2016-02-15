package com.uwics.uwidiscover.fragments;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

/**
 * Created by shane on 2/15/16.
 */
public class FilterDialogFragment extends DialogFragment {

    public static final String TAG = "FilterDialogFragment";

    public interface FilterDialogListener {
        void onDialogPositiveClick();
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private FilterDialogListener mListener;

    public static FilterDialogFragment newInstance() {
        return new FilterDialogFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (FilterDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FilterDialogListener");
        }
    }
}
