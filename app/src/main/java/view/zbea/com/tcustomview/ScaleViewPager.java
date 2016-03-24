package view.zbea.com.tcustomview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import view.zbea.com.tcustomview.adapter.PageAdapter;
import view.zbea.com.tcustomview.view.zbea.com.tcustomview.views.CustomScaleViewPager;

/**
 * Created by ZBea on 16/3/16.类似土巴兔缩放viewPager
 */
public class ScaleViewPager extends Activity
{
    private CustomScaleViewPager scaleViewPager;
    private float maxScale=1.2f;
    private float minScale=0.6f;
    List<Integer> list = new ArrayList<Integer>();
    PageAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_scale_viewpager_view);

        initView();
        initData();
    }

    private void initView()
    {
        scaleViewPager=(CustomScaleViewPager)findViewById(R.id.customScaleView);
        scaleViewPager.setPageTransformer(true,new ViewPager.PageTransformer()
        {
            //当向左划A页(0~-1)B页（1~0）AB绝对值之和为1
            @Override
            public void transformPage(View view, float v)
            {
                if (v<-1)
                {
                    v=-1;
                }
                else if (v>1)
                {
                    v=1;
                }
                float currentScale=0;
                if (v<0)//第一页
                {
                    currentScale=1+v;
                }
                else
                {
                    currentScale=1-v;
                }
                float slope=(maxScale-minScale)/1;
                float scale=minScale+slope*currentScale;
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });
        pagerAdapter=new PageAdapter(list,this);
        scaleViewPager.setAdapter(pagerAdapter);

        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return scaleViewPager.dispatchTouchEvent(event);
            }
        });
    }


    private void initData() {

        list.add(R.drawable.style_xiandai);
        list.add(R.drawable.style_jianyue);
        list.add(R.drawable.style_zhongshi);
        list.add(R.drawable.style_meishi);
        list.add(R.drawable.style_dny);

        //设置OffscreenPageLimit
        scaleViewPager.setOffscreenPageLimit(list.size());
        pagerAdapter.notifyDataSetChanged();

    }
}
