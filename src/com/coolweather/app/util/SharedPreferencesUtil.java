package com.coolweather.app.util;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * ==============================================
 *	------------���ߣ�����ΰ---------------
 * 	------------����֧�֣���㹤����----------
 *	------------���ڣ�2015-3-19------------
 *ͨ���˹�����õ��洢��ֵ
 *===============================================
 */
public class SharedPreferencesUtil {

	private static SharedPreferences getSharedPreferences(Context context){
		return context.getSharedPreferences("config",Context.MODE_PRIVATE);
	}
	
	
	public static void putValue(Context context,String key,String value){
		getSharedPreferences(context).edit().putString(key, value).commit();
		
	};
	/**
	 * 
	 * @param context ������
	 * @param key ����
	 * @param value ֵ
	 */
	public static void putValue(Context context,String key,boolean value){
		getSharedPreferences(context).edit().putBoolean(key, value).commit();
		
	};
	
	
	
	public static String getValue(Context context,String key,String defValue){
		return getSharedPreferences(context).getString(key, defValue);
	};

	

	public static boolean getValue(Context context,String key,boolean defValue){
		return getSharedPreferences(context).getBoolean(key, defValue);
	};
	

}
