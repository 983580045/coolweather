package com.coolweather.app.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import com.coolweather.app.Content;
import com.coolweather.app.activity.MainActivity;
import com.coolweather.app.receiver.UpdataReceiver;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.SharedPreferencesUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class AutoUpdataService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		loadWeather();
		AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour=8*60*60*1000;
		long trigglerAtTime=SystemClock.elapsedRealtime()+anHour;
		Intent i = new Intent(this,UpdataReceiver.class);
		PendingIntent operation =PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,trigglerAtTime, operation);
		return super.onStartCommand(intent, flags, startId);
	}
	public void loadWeather() {
		//һ��ʼ������ʱ�򣬲鿴���Ѿ������˵ط�����,��������˾���ʾ��ǰ���õط���������
		//�������ͨ����λ�õ���ǰ��λ�ã���ʵ��ǰλ�õ�����������ת����ѡ����е�ҳ��
		//Ĭ����ʾ���Ǳ���������
		String weaid = SharedPreferencesUtil.getValue(this,"location","1");
		//�õ����������
		HttpUtil.loadData(HttpMethod.GET,Content.REQUEST_CITY_WEATURE_TODAY_URL+"&weaid="+weaid,null,new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				SharedPreferencesUtil.putValue(AutoUpdataService.this,"Today",responseInfo.result);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(AutoUpdataService.this,"����ʧ�ܣ�", 1).show();
			}
		});
		//�õ�δ�����������
		HttpUtil.loadData(HttpMethod.GET,Content.REQUEST_CITY_WEATHER_URL+"&weaid="+weaid,null,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(AutoUpdataService.this,"����ʧ�ܣ�", 1).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> requesInfo) {
				SharedPreferencesUtil.putValue(AutoUpdataService.this,"Future",requesInfo.result);
			}
			
		});
	}
}
