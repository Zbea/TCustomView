package view.zbea.com.tcustomview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 16/3/16.
 */
public class PageAdapter extends PagerAdapter
{
    List<Integer> list = new ArrayList<Integer>();
    Context mContext;

    public PageAdapter(List<Integer> list,Context mContext)
    {
        super();
        this.list=list;
        this.mContext=mContext;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
       container.removeView((ImageView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(list.get(position));
        container.addView(imageView);
        return imageView;
    }


    @Override
    public boolean isViewFromObject(View view, Object o)
    {
        return view==o;
    }
}
