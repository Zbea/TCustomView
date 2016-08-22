package view.zbea.com.tcustomview;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import view.zbea.com.tcustomview.base.BaseActivity;
import view.zbea.com.tcustomview.page.AutoViewPagerPage;
import view.zbea.com.tcustomview.page.CircleViewPage;
import view.zbea.com.tcustomview.page.ErrorOrSuccessViewPage;
import view.zbea.com.tcustomview.page.FloatTopViewPage;
import view.zbea.com.tcustomview.page.FlowViewPage;
import view.zbea.com.tcustomview.page.RefreshListPage;
import view.zbea.com.tcustomview.page.ScaleViewPager;
import view.zbea.com.tcustomview.views.TimerTextView;


public class MainActPage extends BaseActivity
{
    private long mtimes;
    private Button errorBtn,successBtn,flowBtn,circleBtn,floatBtn,scaleViewBtn
            ,refreshBtn,autoViewPagerBtn;


    @Override
    public int setLayoutResId()
    {
        return R.layout.main_act;
    }

    @Override
    public void initView()
    {
        super.initView();
        //        Fresco.initialize(this);


        TimerTextView timerTextView=(TimerTextView)findViewById(R.id.tv_timer);
        timerTextView.setTimes(2000000000);
        timerTextView.start();

        errorBtn=(Button)findViewById(R.id.customErrorBtn);
        errorBtn.setOnClickListener(onClickListener);

        successBtn=(Button)findViewById(R.id.customSuccessBtn);
        successBtn.setOnClickListener(onClickListener);

        flowBtn=(Button)findViewById(R.id.customFlowBtn);
        flowBtn.setOnClickListener(onClickListener);

        circleBtn=(Button)findViewById(R.id.customCircleBtn);
        circleBtn.setOnClickListener(onClickListener);

        floatBtn=(Button)findViewById(R.id.customFloatTopBtn);
        floatBtn.setOnClickListener(onClickListener);


        scaleViewBtn=(Button)findViewById(R.id.customScaleBtn);
        scaleViewBtn.setOnClickListener(onClickListener);

        refreshBtn=(Button)findViewById(R.id.customRefreshListViewBtn);
        refreshBtn.setOnClickListener(onClickListener);

        autoViewPagerBtn=(Button)findViewById(R.id.customAutoViewPaperBtn);
        autoViewPagerBtn.setOnClickListener(onClickListener);



//        Uri uri = Uri.parse("http://img.gaoxiaogif.cn/GaoxiaoGiffiles/images/2015/06/25/shuaixiachezuodengzishang.gif");
//        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
////		draweeView.setImageURI(uri);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setAutoPlayAnimations(true)
//                .build();
//        draweeView.setController(controller);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (v==errorBtn)
            {
                Intent error=new Intent(MainActPage.this,ErrorOrSuccessViewPage.class);
                error.setFlags(1);
                startActivity(error);
            }
            if (v==successBtn)
            {
                Intent success=new Intent(MainActPage.this,ErrorOrSuccessViewPage.class);
                success.setFlags(2);
                startActivity(success);
            }
            if (v==flowBtn)
            {
                Intent flow=new Intent(MainActPage.this,FlowViewPage.class);
                startActivity(flow);
            }
            if (v==circleBtn)
            {
                Intent flow=new Intent(MainActPage.this,CircleViewPage.class);
                startActivity(flow);
            }
            if (v==floatBtn)
            {
                Intent flow=new Intent(MainActPage.this,FloatTopViewPage.class);
                startActivity(flow);
            }
            if (v==scaleViewBtn)
            {
                Intent flow=new Intent(MainActPage.this,ScaleViewPager.class);
                startActivity(flow);
            }
            if (v==refreshBtn)
            {
                Intent flow=new Intent(MainActPage.this,RefreshListPage.class);
                startActivity(flow);
            }

            if (v==autoViewPagerBtn)
            {
                Intent flow=new Intent(MainActPage.this,AutoViewPagerPage.class);
                startActivity(flow);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(System.currentTimeMillis()-mtimes>2000)
            {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mtimes = System.currentTimeMillis();
            }
            else
            {
                finish();
            }

        }
        return true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (BaseApplication.newRequestQueue()!=null)
            BaseApplication.newRequestQueue().cancelAll(BaseApplication.TAG);
    }
}
