package view.zbea.com.tcustomview.view.zbea.com.tcustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZBea on 16/1/11.
 */
public class CustomErrorView extends View
{

    private int cWidth;//View宽
    private int cHeight;//View高
    private float mProgress=0;//初始弧度
    private int pointX=0;//移动距离
    private int pointY=0;//移动距离

    private boolean isStop;//是否完成绘画


    public CustomErrorView(Context context)
    {
        this(context, null);
    }

    public CustomErrorView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomErrorView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthSpec=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightSpec=MeasureSpec.getMode(heightMeasureSpec);

        if (widthSpec==MeasureSpec.EXACTLY)
        {
            cWidth=widthSize;
        }
        else
        {
            cWidth=120;
        }

        if (heightSpec==MeasureSpec.EXACTLY)
        {
            cHeight=heightSize;
        }
        else
        {
            cHeight=120;
        }

        if (cHeight!=cWidth)
        {
            cHeight=cWidth=Math.min(cHeight,cWidth);
        }

        setMeasuredDimension(cWidth, cHeight);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);

        RectF rectF=new RectF(10,10,cWidth-10,cHeight-10);
        canvas.drawArc(rectF,-90,360*mProgress/100,false,mPaint);
        mProgress+=5;

        if (mProgress>100)
        {
            canvas.drawLine(cWidth/4,cHeight/4,cWidth/4+pointX,cHeight/4+pointX,mPaint);
            if (pointX<cWidth*0.5)
            {
                pointX+=10;
            }
            else
            {
                canvas.drawLine(cWidth*3/4,cHeight/4,cHeight*3/4-pointY,cWidth/4+pointY,mPaint);
                if (pointY<cWidth*0.5)
                {
                    pointY+=10;
                }
                else
                {
                    isStop=true;
                }
            }
        }

        if (!isStop)
        {
            postInvalidateDelayed(10);
        }
        super.onDraw(canvas);
    }


    public void reset()
    {
        mProgress=0;
        pointX=0;
        pointY=0;
        isStop=false;
        invalidate();
    }

}
