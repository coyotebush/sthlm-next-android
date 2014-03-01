package com.saboonchi.sthlmnext.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.saboonchi.sthlmnext.R;
import com.saboonchi.sthlmnext.database.FavoritesDBAdapter;

public class FavoritesCursorAdapter extends ResourceCursorAdapter {

    public FavoritesCursorAdapter(Context context, Cursor c) {
        super(context, R.layout.list_item_2_column, c, CursorAdapter.FLAG_AUTO_REQUERY);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mainView = (TextView) view.findViewById(R.id.text_main);
        TextView subView = (TextView) view.findViewById(R.id.text_sub);
        TextView rightView = (TextView) view.findViewById(R.id.text_right);

        String namesString =
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_STATIONNAME))
                + " \u2192 " // U+2192 RIGHTWARDS ARROW
                + cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_DESTINATIONNAME));
        mainView.setText(namesString);
        String lineString =
                cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_LINETYPE))
                + " " + cursor.getString(cursor.getColumnIndex(FavoritesDBAdapter.COL_LINENUMBER));
        subView.setText(lineString);
        rightView.setText("");
    }

}
