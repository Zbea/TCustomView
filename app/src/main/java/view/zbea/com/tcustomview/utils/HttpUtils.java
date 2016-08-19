package view.zbea.com.tcustomview.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Http请求的工具类
 */
public class HttpUtils
{

    private static final int TIMEOUT_IN_MILLIONS = 5000;

    public interface OnResponseListener
    {
        void onResponse(String result);
    }


    /**
     * HttpGet请求
     *
     * @param urlStr
     * @param headers
     * @param onResponseListener
     */
    public static void doHttpGet(final String urlStr, final Map<String, String> headers, final OnResponseListener onResponseListener)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = GETHttpService(urlStr, headers);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    /**
     * httpPost请求
     *
     * @param url
     * @param headers
     * @param params
     * @param onResponseListener
     */
    public static void doHttpPost(final String url, final Map<String, String> headers, final Map<String, String> params, final OnResponseListener onResponseListener)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = GETHttpPostService(url, headers, params);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    /**
     * httpPut请求
     *
     * @param url
     * @param headers
     * @param params
     * @param onResponseListener
     */
    public static void doHttpPut(final String url, final Map<String, String> headers, final Map<String, String> params, final OnResponseListener onResponseListener)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = GETHttpPutService(url, headers, params);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    /**
     * HttpURLConnection异步的Get请求
     *
     * @param urlStr
     * @param onResponseListener
     */
    public static void doGetAsyn(final String urlStr, final OnResponseListener onResponseListener)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = doGet(urlStr);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            ;
        }.start();
    }


    /**
     * HttpURLConnection异步的Post请求
     *
     * @param urlStr
     * @param params
     * @param onResponseListener
     * @throws Exception
     */
    public static void doPostAsyn(final String urlStr, final String params,
                                  final OnResponseListener onResponseListener) throws Exception
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String result = doPost(urlStr, params);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(result);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            ;
        }.start();

    }


    private static String GETHttpService(String url, Map<String, String> headers)
    {
        String result = null;
        try
        {
            HttpGet httpGet = new HttpGet(url);
            if (headers != null)
            {
                for (String key : headers.keySet())
                {
                    httpGet.addHeader(key, headers.get(key));
                }
            }
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            } else
            {
                result = null;
            }
        } catch (ClientProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;

    }

    private static String GETHttpPostService(String url, Map<String, String> headers, Map<String, String> params)
    {
        String result = null;
        BufferedReader in = null;
        try
        {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            // 实例化HTTP方法
            HttpPost request = new HttpPost(url);
            if (headers != null)
            {
                for (String key : headers.keySet())
                {
                    request.addHeader(key, headers.get(key));
                }
            }
            // 创建名/值组列表
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            if (params != null)
            {
                Set<String> set = params.keySet();
                for (String key : set)
                {
                    parameters.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            // 创建UrlEncodedFormEntity对象
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            request.setEntity(formEntiry);
            // 执行请求
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
            return result;

        } catch (Exception e)
        {

        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String GETHttpPutService(String url, Map<String, String> headers, Map<String, String> params)
    {
        String result = null;
        BufferedReader in = null;
        try
        {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            // 实例化HTTP方法
            HttpPut request = new HttpPut(url);
            if (headers != null)
            {
                for (String key : headers.keySet())
                {
                    request.addHeader(key, headers.get(key));
                }
            }
            // 创建名/值组列表
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            if (params != null)
            {
                Set<String> set = params.keySet();
                for (String key : set)
                {
                    parameters.add(new BasicNameValuePair(key, params.get(key)));
                }
            }
            // 创建UrlEncodedFormEntity对象
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            request.setEntity(formEntiry);
            // 执行请求
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
            return result;

        } catch (Exception e)
        {

        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * Get请求，获得返回数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    private static String doGet(String urlStr)
    {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            if (conn.getResponseCode() == 200)
            {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1)
                {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else
            {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
            }
            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
            }
            conn.disconnect();
        }

        return null;

    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    private static String doPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

            if (param != null && !param.trim().equals(""))
            {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
