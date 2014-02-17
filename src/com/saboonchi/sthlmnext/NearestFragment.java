package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewSwitcher;

public class NearestFragment extends Fragment {

    /** Whether the map (instead of list) display is visible. */
    private boolean mapDisplayed = false;

    /** Button that toggles between map and list displays. */
    private Button toggleButton;

    /** ViewSwitcher containing the fragments for map and list displays. */
    private ViewSwitcher viewSwitcher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearest, container,
                false);
        toggleButton = (Button) view.findViewById(R.id.button_nearest_toggle);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.group_nearest);

        toggleButton.setOnClickListener(new OnClickListener() {

            /**
             * Called when the "Map"/"List" toggle button is pressed.
             */
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
                mapDisplayed = !mapDisplayed;
                if (mapDisplayed) {
                    toggleButton.setText(R.string.button_list);
                } else {
                    toggleButton.setText(R.string.button_map);
                }
            }
        });
        return view;
    }

}
