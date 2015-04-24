package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.NullCipher;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.Citys;
import com.coolweather.app.model.Citys.City;

public class CoolWeatherDB {

	public  static String DB_NAME="CoolWeather";
	public static int VERSION=1;
	public static CoolWeatherDB coolWeatherDB;
	public static SQLiteDatabase db;
	private  CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper helper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
		db=helper.getWritableDatabase();
	}
	//�õ�һ��ʵ��
	public static CoolWeatherDB getInstance(Context context){
		/*if(coolWeatherDB==null){//����
			synchronized (CoolWeatherDB.class) {
				if(coolWeatherDB==null){
					coolWeatherDB=new CoolWeatherDB(context);
				}
			}
		
		}
		*/
		synchronized (CoolWeatherDB.class) {
			if(coolWeatherDB==null){//����
				coolWeatherDB=new CoolWeatherDB(context);
			}
		};
		return coolWeatherDB;
	}
	//�������еĳ�����Ϣ�����Ϊ�����Ч�ʣ���ѯһ���������
	public void saveAllCity(List<City> list){
		/*db.beginTransaction();*/
		for(City city:list){
			System.out.println("������ݿ������ǣ�"+city.cityid);
			ContentValues values = new ContentValues();
			values.putNull("id");
			values.put("cityid",city.cityid);
			values.put("citynm",city.citynm);
			values.put("cityno",city.cityno);
			values.put("weaid",city.weaid);
			db.insert("city",null, values);
		};
		/*
		db.setTransactionSuccessful();
		db.endTransaction();
		*/
	}
	/**
	 * �����õ����еĳ���
	 * @return
	 */
	public List<City> loadAllCitys(){
		Cursor cursor = null;
		ArrayList<City> list = null;
		try {
			cursor = db.rawQuery("select * from city", null);
			list = new ArrayList<City>();
			City city;
			while (cursor.moveToNext()) {
				city=new City();
				city.id=cursor.getInt(0);
				city.cityid=cursor.getString(1);
				city.citynm=cursor.getString(2);
				city.cityno=cursor.getString(3);
				city.weaid=cursor.getString(4);
				list.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
			
		}
		return list;
		
	}
	
	
	public int getColumnSum(){
		Cursor cursor = db.rawQuery("select id from city", null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	};
	/**ģ����ѯ���п�����ë��������������ѯһ��
	 * @param confidition ͨ���������в�ѯ
 	 * @return ����ֵ��һ��ģ����ѯ�ļ���
	 */
	public List<City> loadByConfidition(String confidition){
		Cursor cursor = null ;
		List<City> list = null;
		try {
			cursor = db.rawQuery("select * from city where cityno =?",new String[]{confidition.trim()});
			list = new ArrayList<City>();
			City city;
			while (cursor.moveToNext()) {
				city=new City();
				city.id=cursor.getInt(0);
				city.cityid=cursor.getString(1);
				city.citynm=cursor.getString(2);
				city.cityno=cursor.getString(3);
				city.weaid=cursor.getString(4);
				list.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return list;
	}
	
	public String getWeaidByCityName(String name){

		Cursor cursor = db.rawQuery("select cityid from city where citynm=?",new String[]{name});
		String weaid = null;
		while (cursor.moveToNext()) {
			 weaid=cursor.getString(0);
		}
		cursor.close();
		return weaid;
	}
	
	
}
