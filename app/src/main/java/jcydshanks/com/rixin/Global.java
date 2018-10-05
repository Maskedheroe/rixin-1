package jcydshanks.com.rixin;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by JcyDS on 2018/6/9.
 * 全局变量/常量/方法
 * 封装Toast弹窗函数show，调用方法Global.show();
 * check用于检测customdialog的checkbox
 */

public class Global {


    public static Context gContext;

    public static boolean check = true;


    public static int[] TAB_IMGS=new int[]{R.drawable.note_selector,R.drawable.home_selector,R.drawable.user_selector};

    public static String[] TITLE=new String[]{"新闻","首页","我"};

    public static String BingURL=" http://bing.ioliu.cn/v1/";

    static {
        gContext=null;
    }

    public static void show(String paramString) {
        try
        {
            Toast.makeText(gContext, paramString, Toast.LENGTH_SHORT).show();
            return;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }
}
