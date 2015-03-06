package bigbangtheory.display.yao.bigbangtheory.ui.appinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bigbangtheory.display.yao.bigbangtheory.R;
import bigbangtheory.display.yao.bigbangtheory.appinformation.InstalledDefaultApp;
import bigbangtheory.display.yao.bigbangtheory.ui.BaseListAdapter;

/** 一个
 * Created by JimmyandHurry on 2015/3/5.
 */
public class DefaultAppAdapter extends BaseListAdapter<InstalledDefaultApp> {

    public DefaultAppAdapter(Context context, List<InstalledDefaultApp> items) {
        super(context, items);
    }

    class ViewHolder {
        ImageView icon;
        TextView lable;
        TextView packageName;
        TextView state;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = getInflater().inflate(R.layout.item_installed_app_information, viewGroup, false);
            holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.app_icon);
            holder.lable = (TextView) view.findViewById(R.id.app_lable);
            holder.packageName = (TextView) view.findViewById(R.id.app_package_name);
            holder.state = (TextView) view.findViewById(R.id.app_state);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        InstalledDefaultApp app = getItem(i);
        holder.icon.setImageDrawable(app.getIcon());
        holder.lable.setText(app.getLable());
        holder.packageName.setText(app.getPackageName());
        if(app.getState() == InstalledDefaultApp.INSTALLED_APP) {
            holder.state.setText("已安装");
        } else {
            holder.state.setText("未安装");
        }
        return view;
    }
}
