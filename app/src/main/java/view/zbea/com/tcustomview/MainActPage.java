package view.zbea.com.tcustomview;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import view.zbea.com.tcustomview.base.BaseActivity;
import view.zbea.com.tcustomview.page.AutoViewPagerPage;
import view.zbea.com.tcustomview.page.CircleViewPage;
import view.zbea.com.tcustomview.page.ErrorOrSuccessViewPage;
import view.zbea.com.tcustomview.page.FloatTopViewPage;
import view.zbea.com.tcustomview.page.FlowViewPage;
import view.zbea.com.tcustomview.page.RefreshListPage;
import view.zbea.com.tcustomview.page.ScaleViewPager;
import view.zbea.com.tcustomview.page.StepBarPage;
import view.zbea.com.tcustomview.views.TimerTextView;


public class MainActPage extends BaseActivity
{
    @Bind(R.id.tv_timer)
    TimerTextView tvTimer;
    @Bind(R.id.customErrorBtn)
    Button customErrorBtn;
    @Bind(R.id.customSuccessBtn)
    Button customSuccessBtn;
    @Bind(R.id.customFlowBtn)
    Button customFlowBtn;
    @Bind(R.id.customCircleBtn)
    Button customCircleBtn;
    @Bind(R.id.customFloatTopBtn)
    Button customFloatTopBtn;
    @Bind(R.id.customScaleBtn)
    Button customScaleBtn;
    @Bind(R.id.customRefreshListViewBtn)
    Button customRefreshListViewBtn;
    @Bind(R.id.customAutoViewPaperBtn)
    Button customAutoViewPaperBtn;
    @Bind(R.id.customStepBar)
    Button customStepBar;
    private long mtimes;


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


        tvTimer.setTimes(2000000000);
        tvTimer.start();

        customErrorBtn.setOnClickListener(onClickListener);

        customSuccessBtn.setOnClickListener(onClickListener);

        customFlowBtn.setOnClickListener(onClickListener);

        customCircleBtn.setOnClickListener(onClickListener);

        customFloatTopBtn.setOnClickListener(onClickListener);


        customScaleBtn.setOnClickListener(onClickListener);

        customRefreshListViewBtn.setOnClickListener(onClickListener);

        customAutoViewPaperBtn.setOnClickListener(onClickListener);

        customStepBar.setOnClickListener(onClickListener);


//        Uri uri = Uri.parse("http://img.gaoxiaogif.cn/GaoxiaoGiffiles/images/2015/06/25/shuaixiachezuodengzishang.gif");
//        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
////		draweeView.setImageURI(uri);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setAutoPlayAnimations(true)
//                .build();
//        draweeView.setController(controller);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (v == customErrorBtn)
            {
                Intent error = new Intent(MainActPage.this, ErrorOrSuccessViewPage.class);
                error.setFlags(1);
                startActivity(error);
            }
            if (v == customSuccessBtn)
            {
                Intent success = new Intent(MainActPage.this, ErrorOrSuccessViewPage.class);
                success.setFlags(2);
                startActivity(success);
            }
            if (v == customFlowBtn)
            {
                Intent flow = new Intent(MainActPage.this, FlowViewPage.class);
                startActivity(flow);
            }
            if (v == customCircleBtn)
            {
                Intent flow = new Intent(MainActPage.this, CircleViewPage.class);
                startActivity(flow);
            }
            if (v == customFloatTopBtn)
            {
                Intent flow = new Intent(MainActPage.this, FloatTopViewPage.class);
                startActivity(flow);
            }
            if (v == customScaleBtn)
            {
                Intent flow = new Intent(MainActPage.this, ScaleViewPager.class);
                startActivity(flow);
            }
            if (v == customRefreshListViewBtn)
            {
                Intent flow = new Intent(MainActPage.this, RefreshListPage.class);
                startActivity(flow);
            }

            if (v == customAutoViewPaperBtn)
            {
                Intent flow = new Intent(MainActPage.this, AutoViewPagerPage.class);
                startActivity(flow);
            }
            if (v == customStepBar)
            {
                Intent stepbar = new Intent(MainActPage.this, StepBarPage.class);
                startActivity(stepbar);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (System.currentTimeMillis() - mtimes > 2000)
            {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mtimes = System.currentTimeMillis();
            } else
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
        if (BaseApplication.newRequestQueue() != null)
            BaseApplication.newRequestQueue().cancelAll(BaseApplication.TAG);
    }


}
