package com.coolweather.app.util;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpUtil {

	public static void loadData(HttpRequest.HttpMethod method ,String url,RequestParams params,RequestCallBack<String> callBack){
		System.out.println("�������url�ǣ�"+url);
		HttpUtils http=new HttpUtils();
		//���û���ʧЧ������
		http.configCurrentHttpCacheExpiry(1000 * 1);
		//������������ڿ�
		if (params != null) {
		
		} else {
		//����������ڿ�		
			params = new RequestParams();
		}
http://api.cnmo.com/client?module=api_libraries_sjdbg_articlelist&offset=0&isclass=1&cid=3&rows=10&apiid=3&timestamp=1428499320&token1=daf0482e632d427a4b039f3f6b371705&returnformat=json&encoding=utf8

		
		//�豸ID �������������ͷ�ļ�������һЩ�����������ֻ����ͺ�
		//params.addHeader("x-deviceid", app.deviceId);
		//������ͳ����
		//params.addHeader("x-channel", app.channel);
		
		//����һ��http����
		http.send(method, url, params, callBack);
		
	}
}
