package view.zbea.com.tcustomview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by ZBea on 16/1/12.流布局
 */
public class FlowViewPage extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_flow_view);
    }
}
