package view.zbea.com.tcustomview;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Zbea on 16/8/19.
 */
public class BaseApplication extends Application
{
    public static RequestQueue mRequestQueue;
    public static final String TAG = "VolleyPatterns";

    @Override
    public void onCreate()
    {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        initImageLoad();
    }

    public static RequestQueue newRequestQueue()
    {
        return mRequestQueue;
    }



    public static <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    /**
     * universal_image_loader基本配置
     */
    private void initImageLoad() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "custom/image_cache/");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)//线程池加载的数量
                .diskCacheFileCount(50)//最大缓存数量
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCache(new LimitedAgeDiscCache(cacheDir, 48 * 60 * 60 * 1000))//设置缓存路径
//                .memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024))
                .memoryCache(new WeakMemoryCache())
                .build();
        ImageLoader.getInstance().init(configuration);
    }


    @Override
    public void onLowMemory() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
        System.gc();
        super.onLowMemory();
    }
}
