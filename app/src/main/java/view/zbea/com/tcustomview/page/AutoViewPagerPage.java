package view.zbea.com.tcustomview.page;

import android.os.Message;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import view.zbea.com.tcustomview.BaseApplication;
import view.zbea.com.tcustomview.R;
import view.zbea.com.tcustomview.base.BaseActivity;
import view.zbea.com.tcustomview.base.CustomRequest;
import view.zbea.com.tcustomview.bean.Banner;
import view.zbea.com.tcustomview.utils.L;
import view.zbea.com.tcustomview.utils.ScreenUtils;
import view.zbea.com.tcustomview.utils.T;
import view.zbea.com.tcustomview.views.CustomBannerView;

/**
 * Created by ZBea on 16/1/12.自动滑动ViewPager
 */
public class AutoViewPagerPage extends BaseActivity
{

    private LinearLayout ll_content;
    private CustomBannerView customBannerView;
    private static final int REFRESH_LIST=0;
    private List<Banner> banners = new ArrayList<Banner>();

   private android.os.Handler mHandler=new android.os.Handler()
   {
       @Override
       public void handleMessage(Message msg)
       {
           switch (msg.what)
           {
               case REFRESH_LIST:
                   setAdView();
                   break;
           }
       }
   };

    @Override
    public int setLayoutResId()
    {
        return R.layout.page_auto_viewpager;
    }

    @Override
    public void initView()
    {
        ll_content=(LinearLayout)findViewById(R.id.bannerView);
    }

    @Override
    public void initData()
    {
        String url="http://portal-web.zhaidou.com/index/getBoardContent.action?boardCodes=05";
        CustomRequest customRequest=new CustomRequest(url,new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                if (jsonObject!=null)
                {
                    L.i(jsonObject.toString());

                    int status=jsonObject.optInt("status");
                    JSONObject dataObject=jsonObject.optJSONArray("data").optJSONObject(0);
                    if (status==200)
                    {
                        JSONArray jsonArray=dataObject.optJSONArray("programPOList");
                        for (int i = 0; i <jsonArray.length() ; i++)
                        {
                            JSONObject itemObject=jsonArray.optJSONObject(i);
                            Banner banner= JSON.parseObject(itemObject.toString(), Banner.class);
                            banners.add(banner);
                        }
                        mHandler.sendEmptyMessage(REFRESH_LIST);
                    }

                }
            }
        },null);
        BaseApplication.addToRequestQueue(customRequest, "");
    }

    /**
     * 广告轮播设置
     */
    private void setAdView()
    {
        if (customBannerView == null)
        {
            customBannerView = new CustomBannerView(mContext, banners, true);
            customBannerView.setLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(this) * 400 / 750);
            customBannerView.setOnBannerClickListener(new CustomBannerView.OnBannerClickListener()
            {
                @Override
                public void onClick(int postion)
                {
                    T.showLong(mContext, banners.get(postion).getName());
                }
            });
            ll_content.addView(customBannerView);
        } else
        {
            customBannerView.setImages(banners);
        }
    }

}
