package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testapplication.testapp.R;

import java.util.List;

import model.FactItem;
import utils.AppUtils;

/**
 * Created by SumitBhatia on 22/03/15.
 */

/**
 * Copyright 2013 Square, Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */


public class FactsAdapter extends BaseAdapter {

    private List<FactItem> factList;
    private Context mContext;
    private int resId;


    public FactsAdapter(Context context, List<FactItem> list, int resId) {
        this.mContext = context;
        this.factList = list;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return factList.size();
    }

    @Override
    public FactItem getItem(int position) {
        return factList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FactItem item = factList.get(position);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(resId, parent, false);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.img_fact);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(AppUtils.isStringNullOrEmpty(item.title) ? mContext.getResources().getString(R.string.not_available)
                : item.title);
        holder.txtDescription.setText(AppUtils.isStringNullOrEmpty(item.description) ? mContext.getResources().getString(R.string.not_available)
                : item.description);

        //Using Picasso by Square for lazy loading of images.
        Picasso.with(mContext).load(item.imageHref).into(holder.imgThumbnail);

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgThumbnail;
    }
}
