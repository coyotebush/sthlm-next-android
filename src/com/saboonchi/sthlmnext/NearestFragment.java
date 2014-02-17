package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class NearestFragment extends Fragment {

    /** Whether the map (instead of list) display is visible. */
    private boolean mapDisplayed = false;

    /** Button that toggles between map and list displays. */
    private Button toggleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearest, container,
                false);
        toggleButton = (Button) view.findViewById(R.id.button_nearest_toggle);

        toggleButton.setOnClickListener(new OnClickListener() {

            /**
             * Called when the "Map"/"List" toggle button is pressed.
             */
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                mapDisplayed = !mapDisplayed;
                if (mapDisplayed) {
                    toggleButton.setText(R.string.button_list);
                    fragmentTransaction.replace(R.id.group_nearest, new NearestMapFragment());
                } else {
                    toggleButton.setText(R.string.button_map);
                    fragmentTransaction.replace(R.id.group_nearest, new NearestListFragment());
                }
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}
