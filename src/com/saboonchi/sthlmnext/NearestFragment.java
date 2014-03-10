package com.saboonchi.sthlmnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

public class NearestFragment extends Fragment {

    private static final String[] types = { "B", "T", "J", "L", "W" };

    /** Whether the map (instead of list) display is visible. */
    private boolean mapDisplayed = false;

    /** ViewSwitcher containing the fragments for map and list displays. */
    private ViewSwitcher viewSwitcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearest, container,
                false);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.group_nearest);

        LinearLayout toggleGroup = (LinearLayout) view.findViewById(R.id.group_type_filter);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        for (String t : types) {
            ToggleButton button = new ToggleButton(getActivity());
            button.setText(t);
            button.setTextOn(t);
            button.setTextOff(t);
            button.setChecked(true);
            toggleGroup.addView(button, layoutParams);
        }
        return view;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.nearest, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_toggle_list_map:
            viewSwitcher.showNext();
            mapDisplayed = !mapDisplayed;
            item.setIcon(mapDisplayed ? R.drawable.ic_action_view_as_list : R.drawable.ic_action_map);
            item.setTitle(mapDisplayed ? R.string.button_list : R.string.button_map);
            return true;

        case R.id.action_refresh:
            ((MainActivity) getActivity()).refreshStations();
            return true;
            
        case R.id.action_bus:
            boolean b = !item.isChecked();
            ((MainActivity) getActivity()).setIncludeBus(b);
            item.setChecked(b);

        default:
            return super.onOptionsItemSelected(item);
        }
    }

}
