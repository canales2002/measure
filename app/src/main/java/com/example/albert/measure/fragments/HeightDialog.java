package com.example.albert.measure.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.albert.measure.R;
import com.example.albert.measure.activities.DistanceActivity;
import com.example.albert.measure.activities.DistanceParametersActivity;

public class HeightDialog extends DialogFragment {

    private Bundle bundle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.dialog_height)
                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Overridden below
                    }
                });

        return builder.create();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    EditText editText = d.findViewById(R.id.heightET);
                    String text = editText.getText().toString().trim();
                    if (text.length() == 0) editText.setError("Cannot be empty");
                    else {
                        double height = getFinalHeight(Double.valueOf(editText.getText().toString()));
                        Log.d("PARAMETER_VALUE", Double.toString(height));
                        if (height == -1) {
                            editText.setError("Not a valid number");
                        } else {
                            bundle.putDouble("HEIGHT", getFinalHeight(height));
                            dismiss();
                            Intent i = new Intent(getContext(), DistanceActivity.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    }
                }
            });
        }
    }

    public double getFinalHeight(double height) {
        if (bundle.get("HEIGHT_MODE").equals("MANUAL"))
            return height;
        return -1;       // TODO Add automatic algorithm
    }

    public void passParameters(Bundle bundle) {
        this.bundle = bundle;
    }
}