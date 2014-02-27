package com.saboonchi.sthlmnext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.NumberPicker;

public class DepartureNotificationDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.title_dialog_departure_notification);
        builder.setNegativeButton(R.string.button_departure_notification_cancel, null);
        builder.setPositiveButton(R.string.button_departure_notification_set, null);
        builder.setMessage(R.string.text_departure_notification_message);
        
        NumberPicker numberPicker = new NumberPicker(getActivity());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(60);
        numberPicker.setValue(5);
        builder.setView(numberPicker);

        return builder.create();
    }
}
