package com.uwics.uwidiscover.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.uwics.uwidiscover.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shane on 2/15/16.
 */
public class FilterDialogFragment extends DialogFragment {

    public static final String TAG = "FilterDialogFragment";

    public interface FilterDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Map<String, String> filterValues);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private FilterDialogListener mListener;

    public static FilterDialogFragment newInstance() {
        return new FilterDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(R.layout.fragment_dialog_filter, null);

        final EditText editTextStartTime = (EditText) rootView.findViewById(R.id.start_time);
        final EditText editTextEndTime = (EditText) rootView.findViewById(R.id.end_time);

        final CheckBox checkBoxWednesday = (CheckBox) rootView.findViewById(R.id.wednesday);
        final CheckBox checkBoxThursday = (CheckBox) rootView.findViewById(R.id.thursday);
        final CheckBox checkBoxFriday = (CheckBox) rootView.findViewById(R.id.friday);

        final Map<String, String> filterValues = new ArrayMap<>();

        builder.setView(rootView)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String startTime = editTextStartTime.getText().toString();
                        String endTime = editTextEndTime.getText().toString();

                        if (!startTime.equals(""))
                            filterValues.put("start_time", startTime);

                        if (!endTime.equals(""))
                            filterValues.put("end_time", endTime);

                        if (checkBoxWednesday.isChecked())
                            filterValues.put("wednesday", "18/2/2016");

                        if (checkBoxThursday.isChecked())
                            filterValues.put("thursday", "19/2/2016");

                        if (checkBoxFriday.isChecked())
                            filterValues.put("friday", "20/2/2016");

                        mListener.onDialogPositiveClick(FilterDialogFragment.this, filterValues);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogNegativeClick(FilterDialogFragment.this);
                    }
                })

        return super.onCreateDialog(savedInstanceState);
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
