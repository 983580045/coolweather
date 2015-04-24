package com.coolweather.app.activity;

import com.coolweather.app.Content;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.Citys;
import com.coolweather.app.model.Citys.City;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.JsonUtil;
import com.coolweather.app.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class IndexActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//���Ȳ鿴�����б������ݿ����Ƿ��Ѿ��������������û�л��棬��������أ����浽���ݿ���,����Ͳ��úͷ������˵��������бȽ���
	if(CoolWeatherDB.getInstance(this).getColumnSum()<=0){
		HttpUtil.loadData(HttpMethod.GET,Content.RQUEST_CITY_URL,null,new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String resutl = JsonUtil.ObjectToArray(responseInfo.result);
				if(!TextUtils.isEmpty(resutl)){
					Gson gson=new Gson();
					Citys citys = gson.fromJson(resutl,Citys.class);
					//System.out.println("���ϰ�������"+citys.result.size());
					CoolWeatherDB.getInstance(IndexActivity.this).saveAllCity(citys.result);
				}
					
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(IndexActivity.this, "���س�������ʧ�ܣ������¼���", 1).show();
			}
		});
	};
	//�鿴��ǰ�ĳ����Ƿ��Ѿ��趨������Ѿ��趨����ת������ʵ����Ԥ����ҳ��
	String location = SharedPreferencesUtil.getValue(this, "location",null);
	
	if(TextUtils.isEmpty(location)){
		//���û���趨��ǰ�ĳ�����ת�������ó��е�ҳ��
		Intent intent = new Intent(this,SettingCity.class);
		startActivity(intent);
	}else {
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
		
	
	}
}
