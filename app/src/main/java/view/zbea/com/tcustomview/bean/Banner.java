package view.zbea.com.tcustomview.bean;

import java.io.Serializable;

/**
 * Created by Zbea on 16/8/19.
 */
public class Banner implements Serializable
{

    private String name;
    private String type;
    private String url;
    private String pictureUrl;
    private String pcImage;
    private String code;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPcImage()
    {
        return pcImage;
    }

    public void setPcImage(String pcImage)
    {
        this.pcImage = pcImage;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
