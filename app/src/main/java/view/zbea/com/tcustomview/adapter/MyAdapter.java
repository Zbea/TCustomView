package view.zbea.com.tcustomview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import view.zbea.com.tcustomview.R;
import view.zbea.com.tcustomview.bean.ApkEntity;
import view.zbea.com.tcustomview.utils.BaseListAdapter;
import view.zbea.com.tcustomview.utils.ViewHolder;

public class MyAdapter extends BaseListAdapter<ApkEntity>
{
    public MyAdapter(Context context, List<ApkEntity> list)
    {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.item_refresh_list, null);
            TextView tv_name = ViewHolder.get(convertView, R.id.itemApkName);
            TextView tv_des = ViewHolder.get(convertView, R.id.itemApkDes);
            TextView tv_info = ViewHolder.get(convertView, R.id.itemApkInfo);
             ApkEntity item = getList().get(position);
             tv_name.setText(item.getName());
             tv_des.setText(item.getDes());
             tv_info.setText(item.getInfo());
        return convertView;
    }

}
