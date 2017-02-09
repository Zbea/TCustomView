package view.zbea.com.tcustomview.page;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import view.zbea.com.tcustomview.R;
import view.zbea.com.tcustomview.base.BaseActivity;
import view.zbea.com.tcustomview.views.StepView;

/**
 * 类似物流进度条
 */
public class StepBarPage extends BaseActivity
{

    @Bind(R.id.step_view)
    StepView stepView;

    @Override
    public int setLayoutResId()
    {
        return R.layout.page_step_bar;
    }

    @Override
    public void initView()
    {
        List<String> titles = new ArrayList<String>();
        titles.add("下订单");
        titles.add("代付款");
        titles.add("支付完成");
        titles.add("已经发货");
        titles.add("交易完成");
        stepView.setStepTitles(titles);
    }



    public void next(View view)
    {
        stepView.nextStep();
    }

    public void reset(View view)
    {
        stepView.reset();
    }


}
