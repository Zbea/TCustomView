package view.zbea.com.tcustomview.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Zbea on 16/2/19.滑动顶部后漂浮
 */
public class CustomFloatTopView extends ScrollView
{

    private OnScrollListener onScrollListener;

    private int scrollY;//滑动距离
    private int scrollLeaveY;//离开 scrollview滑动的距离

    public CustomFloatTopView(Context context)
    {
        this(context, null);
    }

    public CustomFloatTopView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomFloatTopView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    /**
     * 重写onTouchEvent， 当用户的手在ScrollView上面的时候，
     * 直接将ScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * ScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * ScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if(onScrollListener!=null)
            onScrollListener.onScroll(scrollLeaveY=getScrollY());
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(),20);
                break;
        }
        return super.onTouchEvent(ev);
    }


    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            scrollY=getScrollY();
            if (scrollLeaveY!=scrollY)//如果原来的距离不等于现在得到的距离则重新处理
            {
                scrollLeaveY=scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(),5);
            }
            if (onScrollListener!=null)
            {
                onScrollListener.onScroll(scrollY);
            }
        }
    };


    public void setOnScrollListener(OnScrollListener onScrollListener)
    {
        this.onScrollListener=onScrollListener;
    }

    public interface OnScrollListener
    {
        public void onScroll(int scrollY);
    }


}
