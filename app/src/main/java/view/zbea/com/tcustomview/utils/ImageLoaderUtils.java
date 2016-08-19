package view.zbea.com.tcustomview.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by roy on 16/8/19.
 */
public class ImageLoaderUtils
{


    /**
     * 图片异步加载（缓存图片方法）
     *
     * @param url
     * @param imageView
     */
    public static final void setImageCacheUrl(String url, ImageView imageView)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.icon_loading_defalut)
//                .showImageOnFail(R.drawable.icon_loading_defalut)
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .build();

        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 图片异步加载（缓存图片方法）切圆角
     *
     * @param url
     * @param imageView
     */
    public static final void setImageCacheRoundUrl(String url, ImageView imageView, int i, int resId)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(i))//设置圆角半径
                .showImageOnLoading(resId)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();

        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 图片异步加载（缓存图片方法）
     *
     * @param url
     * @param imageView
     * @param resId     设置加载过程中背景底图
     */
    public static final void setImageCacheUrl(String url, ImageView imageView, int resId)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(resId)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
//                .delayBeforeLoading(100)//载入图片前稍做延时可以提高整体滑动的流畅度
                .build();

        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 设置图片不复位
     *
     * @param url
     * @param imageView
     * @param resId
     */
    public static final void setImageNoResetUrl(String url, final ImageView imageView, int resId)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(resId)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();

        ImageLoader.getInstance().loadImage(url, options, new SimpleImageLoadingListener()
        {
            @Override
            public void onLoadingStarted(String s, View view)
            {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason)
            {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap)
            {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view)
            {
            }
        });
    }

    /**
     * 图片异步加载（不缓存图片设置）
     *
     * @param url
     * @param imageView
     */
    public static final void setImageUrl(String url, ImageView imageView)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 图片异步加载（不缓存图片设置）
     *
     * @param url
     * @param imageView
     * @param resId     设置加载过程中背景底图
     */
    public static final void setImageUrl(String url, ImageView imageView, int resId)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(resId)
                .showImageForEmptyUri(resId)
                .showImageOnFail(resId)
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 本地图片异步加载（不缓存图片设置，处理内存溢出）
     *
     * @param url
     * @param imageView
     */
    public static final void setImagePreventMemoryLeaksUrl(String url, ImageView imageView)
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)//default 设置图片在加载前是否重置、复位
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }


}
