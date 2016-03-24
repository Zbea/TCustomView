package view.zbea.com.tcustomview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import view.zbea.com.tcustomview.view.zbea.com.tcustomview.views.CustomErrorView;
import view.zbea.com.tcustomview.view.zbea.com.tcustomview.views.CustomSuccessView;

/**
 * Created by ZBea on 16/1/11.自定义错误动画页面
 */
public class ErrorOrSuccessViewPage extends Activity
{
    private CustomErrorView errorView;
    private CustomSuccessView successView;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_error_view);

        type=getIntent().getFlags();

        errorView=(CustomErrorView)findViewById(R.id.customErrorView);
        errorView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                errorView.reset();
            }
        });
        successView=(CustomSuccessView)findViewById(R.id.customSuccessView);
        successView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                successView.reset();
            }
        });

        if (type==1)
        {
            errorView.setVisibility(View.VISIBLE);
            successView.setVisibility(View.GONE);
        }
        else
        {
            errorView.setVisibility(View.GONE);
            successView.setVisibility(View.VISIBLE);
        }
    }
}
