package com.surfline.surflinegh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.surfline.R;
import com.surfline.surflinegh.constants.AppConstants;


public class MenuAdapter extends BaseAdapter {

    private static int[] list = {AppConstants.Store_locator, AppConstants.Retailer,
     AppConstants.Bundles, AppConstants.Devices, AppConstants.Company};

    private Context mContext;
    private LayoutInflater inflater;

    public MenuAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Integer getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView  txvMenu;

        ViewHolder(View convertView) {
            txvMenu = (TextView) convertView.findViewById(R.id.txvMenu);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_menu, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txvMenu.setText(mContext.getResources().getStringArray(R.array.menu_)[getItem(position)]);

        return convertView;
    }
}
