package view.zbea.com.tcustomview.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import butterknife.ButterKnife;
import view.zbea.com.tcustomview.ExitApplication;

/**
 * Created by Zbea on 16/8/19. 基本活动类
 */
public class BaseActivity extends Activity
{

    private int layoutResId;//布局资源ID
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayoutResId());

        ButterKnife.bind(this);

        mContext=BaseActivity.this;
        ExitApplication.getInstance().addActivity(this);

        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    public void initData()
    {

    }

    /**
     * 初始化View
     */
    public void initView()
    {

    }

    /**
     * 设置布局文件
     * @return
     */
    public int setLayoutResId()
    {
        return layoutResId;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
