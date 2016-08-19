package view.zbea.com.tcustomview.views;

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
public class CustomSuccessView extends View
{
    private int mProgress=0;
    private int cWidth;
    private int cHeight;
    private int pointX=0;
    private int pointTwoX=0;
    private int pointTwoY=0;
    private boolean isStop;

    public CustomSuccessView(Context context)
    {
        this(context, null);
    }

    public CustomSuccessView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomSuccessView(Context context, AttributeSet attrs, int defStyleAttr)
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

        setMeasuredDimension(cWidth,cHeight);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint mPaint=new Paint();
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(new RectF(10,10,cWidth-10,cHeight-10),-90,360*mProgress/100,false,mPaint);
        if(mProgress<=100)
        {
            mProgress+=5;
        }
        else
        {
            canvas.drawLine(cWidth/4,cHeight/2,cWidth/4+pointX,cHeight/2+pointX,mPaint);
            if (pointX<cWidth/4)
            {
                pointX+=4;
            }
            else
            {
                canvas.drawLine(cWidth/2,cHeight*3/4,cWidth/2+pointTwoX,cHeight*3/4-pointTwoY,mPaint);
                if (pointTwoX<cWidth/4)
                {
                    pointTwoX+=5;
                    pointTwoY+=10;
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
        pointTwoX=0;
        pointTwoY=0;
        isStop=false;
        invalidate();
    }
}
