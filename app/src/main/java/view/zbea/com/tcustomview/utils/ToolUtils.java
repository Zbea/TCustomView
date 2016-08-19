package view.zbea.com.tcustomview.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zbea on 16/8/19.
 */
public class ToolUtils
{

    /**
     * 判断手机号码格式是否正确
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneOk(String phone)
    {
        if (TextUtils.isEmpty(phone))
        {
            return false;
        }
        Pattern p = Pattern.compile("(1[3456789]\\d{9})");
//        Pattern p=Pattern.compile("(1[358]\\d{9})|(14[57]\\d{8})|(17[0678]\\d{8})");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 判断邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmailOK(String email)
    {
        if (email != null & email.length() > 0)
        {
//            Pattern p=Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+");
            Pattern p = Pattern.compile("^[a-zA-Z][\\\\w\\\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\\\w\\\\.-]*[a-zA-Z0-9]\\\\.[a-zA-Z][a-zA-Z\\\\.]*[a-zA-Z]$");
            Matcher m = p.matcher(email);
            return m.matches();
        } else
        {
            return false;
        }
    }
}
