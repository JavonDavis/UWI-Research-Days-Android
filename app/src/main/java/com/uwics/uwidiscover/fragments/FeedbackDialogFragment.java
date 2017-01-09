package com.uwics.uwidiscover.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.models.Feedback;

/**
 * Created by Howard on 1/24/2016.
 */
public class FeedbackDialogFragment extends DialogFragment
        implements DialogInterface.OnClickListener {

    private EditText mFeedbackEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_dialog_feedback, null);

        mFeedbackEditText = (EditText) rootView.findViewById(R.id.feedback_edittext);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(rootView);
        alertDialogBuilder.setTitle("Feedback");
        alertDialogBuilder.setPositiveButton("Submit", this).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String feedbackText = mFeedbackEditText.getText().toString();

        //TODO: Determine what ID should be. Gonna make name most likely name
        Feedback feedback = new Feedback("", "", feedbackText, null);
        //TODO: Push feedback to Parse and update mFeedbackRecyclerView

        dialog.dismiss();
    }
}
