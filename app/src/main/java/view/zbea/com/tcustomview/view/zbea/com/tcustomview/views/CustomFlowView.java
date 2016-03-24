package view.zbea.com.tcustomview.view.zbea.com.tcustomview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZBea on 16/1/12.流布局
 */
public class CustomFlowView extends ViewGroup
{
    //所有子View集合
    private List<List<View>> mAllViews=new ArrayList<List<View>>();
    //所有行高集合
    private List<Integer> mLineHeights=new ArrayList<Integer>();

    public CustomFlowView(Context context)
    {
        this(context, null);
    }

    public CustomFlowView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomFlowView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    /**
     * 布局
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        mAllViews.clear();
        mLineHeights.clear();
        //行宽
        int lineWidth=0;
        //行高
        int lineHeight=0;
        //一行所包含的所有 View
        List<View> lineViews=new ArrayList<View>();
        //拿到所有行高，所有子 View
        for (int i=0;i<getChildCount();i++)
        {
            View child=getChildAt(i);
            MarginLayoutParams lp=(MarginLayoutParams)child.getLayoutParams();
            int cWidth=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            int cHeight=child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            //换行（父宽度要减去父设置的 padding 距离）
            if (lineWidth+cWidth>getWidth()-getPaddingLeft()-getPaddingRight())
            {
                mAllViews.add(lineViews);
                mLineHeights.add(lineHeight);
                lineViews=new ArrayList<View>();
                lineWidth=0;
                lineHeight=cHeight;
            }
            lineWidth+=cWidth;
            lineHeight=Math.max(lineHeight,cHeight);
            lineViews.add(child);

            if (i==getChildCount()-1)
            {
                //将最后一行加入进去
                mLineHeights.add(lineHeight);
                mAllViews.add(lineViews);
            }
        }

        //设置子View位置(如何父 VIew设置 padding距离)
        int left=getPaddingLeft();
        int top=getPaddingTop();

        for (int i = 0; i <mAllViews.size(); i++)
        {
            lineViews=mAllViews.get(i);
            lineHeight=mLineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child= lineViews.get(j);
                //判断是否显示
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //确定子 view 上下左右的位置
                int cLeft=left+lp.leftMargin;
                int cTop=top+lp.topMargin;
                int cRight=cLeft+child.getMeasuredWidth();
                int cBottom=cTop+child.getMeasuredHeight();
                //对子 View进行布局
                child.layout(cLeft,cTop,cRight,cBottom);
                //叠加左边初始距离
                left+=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            }
            //一行 View布局完后初始化下一行的 left、top
            left=getPaddingLeft();
            top+=lineHeight;

        }



    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthSpec=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightSpec=MeasureSpec.getMode(heightMeasureSpec);

        //warp_content 下的父 View的宽高
        int width=0;
        int height=0;
        //记录每一行的宽度和高度
        int lineWidth=0;//行宽
        int lineHeight=0;//行高

        for (int i = 0; i <getChildCount() ; i++)
        {
            View child=getChildAt(i);
            //测量子 View的宽高
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp=(MarginLayoutParams)child.getLayoutParams();
            //子 View占据的宽、高
            int cWidth=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            int cHeight=child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            //不换行（父宽度要减去父设置的 padding 距离）
            if (lineWidth+cWidth<=widthSize-getPaddingLeft()-getPaddingRight())
            {
                //叠加宽
                lineWidth+=cWidth;
                //获得最大行高
                lineHeight=Math.max(lineHeight,cHeight);
            }
            else
            {
                //对比得到父 View的最大宽度
                width=Math.max(width,lineWidth);
                lineWidth=cWidth;
                //叠加得到父 View的最大高度
                height+=lineHeight;
                lineHeight=cHeight;
            }
            if (i==getChildCount()-1)
            {
                width=Math.max(width,lineWidth);
                height+=lineHeight;
            }

        }

        setMeasuredDimension(widthSpec==MeasureSpec.EXACTLY?widthSize:width+getPaddingLeft()+getPaddingRight(),
                heightSpec==MeasureSpec.EXACTLY?heightSize:height+getPaddingTop()+getPaddingBottom());

    }

    /**
     * ViewGroup 对应相关 layoutParams
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
