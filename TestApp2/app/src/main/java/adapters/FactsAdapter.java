package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.testapp.R;

import java.util.List;

import model.FactItem;
import utils.AppUtils;

/**
 * Created by SumitBhatia on 22/03/15.
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

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgThumbnail;
    }
}
