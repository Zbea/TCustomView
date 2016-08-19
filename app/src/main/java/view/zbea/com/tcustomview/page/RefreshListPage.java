package view.zbea.com.tcustomview.page;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import view.zbea.com.tcustomview.R;
import view.zbea.com.tcustomview.bean.ApkEntity;
import view.zbea.com.tcustomview.adapter.MyAdapter;
import view.zbea.com.tcustomview.views.RefreshListView;

/**
 * Created by ZBea on 16/3/16.下啦刷新上拉自动加载
 */
public class RefreshListPage extends Activity
{
    private RefreshListView listView;
    private MyAdapter adapter;
    List<ApkEntity> apk_list = new ArrayList<ApkEntity>();


    private RefreshListView.OnRefreshListener onRefreshListener = new RefreshListView.OnRefreshListener()
    {
        @Override
        public void onRefresh()
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    setRefreshData();
                    adapter.setList(apk_list);
                    listView.refreshComplete();
                }
            },2000);
        }

        @Override
        public void onLoading()
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    setLoadData();
                    adapter.setList(apk_list);
                    listView.onLoadingComplete();
                }
            },2000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_refresh_listviews);

        initView();
        initData();
    }

    private void initView()
    {
        listView = (RefreshListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this, apk_list);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(onRefreshListener);

    }


    private void initData()
    {
        for (int i = 0; i < 10; i++)
        {
            ApkEntity entity = new ApkEntity();
            entity.setName("程序名字");
            entity.setDes("应用程序信息");
            entity.setInfo("应用程序描述");
            apk_list.add(entity);
        }
        adapter.setList(apk_list);

    }


    private void setRefreshData() {
        apk_list.clear();
        for (int i = 0; i < 10; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("下拉刷新");
            entity.setDes("应用程序信息");
            entity.setInfo("应用程序描述");
            apk_list.add(0,entity);
        }
    }

    private void setLoadData() {
        for (int i = 0; i < 5; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("加载更多");
            entity.setDes("应用程序信息");
            entity.setInfo("应用程序描述");
            apk_list.add(entity);
        }
    }
}
