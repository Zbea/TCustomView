package view.zbea.com.tcustomview.view.zbea.com.tcustomview.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by roy on 16/3/16.
 */
public class CustomScaleViewPager extends ViewPager
{
    public CustomScaleViewPager(Context context)
    {
        this(context, null);
    }

    public CustomScaleViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (ev.getAction()==MotionEvent.ACTION_UP)
        {
            //将当前的点击的视图设置为默认视图
            View view=getCurrentView(ev);
            if (view!=null)
            {
                setCurrentItem(indexOfChild(view));
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 获取当前点击的view
     * @param ev
     * @return
     */
    private View getCurrentView(MotionEvent ev)
    {
        View view=null;

        float eventX=ev.getX();
        float eventY=ev.getY();

        int[] location=new int[2];

        for (int i = 0; i <getChildCount(); i++)
        {
            view=getChildAt(i);
            view.getLocationOnScreen(location);
            int minX=location[0];
            int minY=getTop();
            int maxX=location[0]+view.getWidth();
            int maxY=getBottom();
            //当当前的坐标出于这个视图的坐标时获取当前的view
            if((eventX>minX && eventX<maxX)&&(eventY>minY && eventY<maxY))
            {
                return view;
            }
        }
        return  null;
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels)
    {
        super.onPageScrolled(position, offset, offsetPixels);
    }
}
