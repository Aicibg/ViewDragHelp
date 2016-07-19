package com.app.viewdraghelpdemo.inter;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ViewInjectUtils Created on 2016/7/19-16:23
 * Description:
 * Created by DongHao
 */
public class ViewInjectUtils {
    final static String METOD_SET_CONTENTVIEW="setContentView";
    final static String METOD_FIND_VIEW_BY_ID="findViewById";

    public static void inJect(Activity activity){
        injectContentView(activity);
        inJectViews(activity);
    }

    private static void injectContentView(Activity activity){
        Class<? extends Activity> clazz=activity.getClass();
        //查询类上是否存在ContentView注解
        ContentView contentview=clazz.getAnnotation(ContentView.class);
        if(contentview!=null){
            int contentviewId=contentview.values();
            try {
                Method method=clazz.getMethod(METOD_SET_CONTENTVIEW,int.class);//拿到setContentView方法
                method.setAccessible(true);//是否允许访问，私有成员变量必须使用该方法
                method.invoke(activity,contentviewId);//调用setContentView方法
             } catch (Exception e){
                e.printStackTrace();
             }finally{

             }
        }
    }

    private static void inJectViews(Activity activity){
        Class<? extends Activity> clazz=activity.getClass();
        Field[] fields=clazz.getDeclaredFields();
        //遍历所有成员变量
        for(Field field:fields){
            //是否存在ViewInject注解
            ViewInject viewInjectAnnotation=field.getAnnotation(ViewInject.class);
            if(viewInjectAnnotation!=null){
                 int viewId=viewInjectAnnotation.values();
                if(viewId!=-1){
                    try {
                        Method method=clazz.getMethod(METOD_FIND_VIEW_BY_ID,int.class);//拿到findViewById方法
                        Object resView=method.invoke(activity,viewId);//执行findViewById方法
                        field.setAccessible(true);
                        field.set(activity,resView);//设置成员变量值
                     } catch (Exception e){
                        e.printStackTrace();
                     }finally{

                     }
                }
            }
        }
    }
}
