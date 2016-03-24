package view.zbea.com.tcustomview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import view.zbea.com.tcustomview.view.zbea.com.tcustomview.views.CustomFloatTopView;

/**
 * Created by ZBea on 16/1/12.漂浮顶部
 */
public class FloatTopViewPage extends Activity
{

    private CustomFloatTopView scollView;
    private LinearLayout containView,containView1;
    private RelativeLayout topView;
    private TextView layout;
    private Button addBtn,buyBtn;
    private int topHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_float_top_view);
        initView();

    }

    private void initView()
    {
        scollView=(CustomFloatTopView)findViewById(R.id.customFloatView);
        scollView.setOnScrollListener(onScrollListener);

        containView=(LinearLayout)findViewById(R.id.containLine);
        containView1=(LinearLayout)findViewById(R.id.containLine1);
        topView=(RelativeLayout)findViewById(R.id.topView);
        layout=(TextView)findViewById(R.id.layout);

        addBtn=(Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplication(),"添加",Toast.LENGTH_SHORT).show();
            }
        });

        buyBtn=(Button)findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplication(),"购买",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private CustomFloatTopView.OnScrollListener onScrollListener=new CustomFloatTopView.OnScrollListener()
    {
        @Override
        public void onScroll(int scrollY)
        {
            Log.i("Zbea","scrollY:"+scrollY);
            Log.i("Zbea","topHeight:"+topHeight);
            if (scrollY>=topHeight)//当滑动的距离大于或者等于原来距离顶部的距离时则重新处理
            {
                if (topView.getParent()!=containView)
                {
                    containView1.removeView(topView);
                    containView.addView(topView);
                }
            }
            else
            {
                if (topView.getParent()!=containView1)
                {
                    containView.removeView(topView);
                    containView1.addView(topView);
                }
            }
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            topHeight=containView1.getTop();
        }
    }
}
