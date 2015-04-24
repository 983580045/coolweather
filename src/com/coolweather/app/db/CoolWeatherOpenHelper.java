package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	
	public static String CREATE_CITY="create table city(" +
			"id integer primary key autoincrement," +//id ����
			"cityid text ," +//����������
			"citynm text," +//������
			"cityno text," +//����ƴ��
			"weaid text)";//���е�id
	
	
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase dbs) {
		//����һ�����б����Դ�ȡȫ�����еĳ���
		System.out.println("�����ڴ�����");
		dbs.execSQL(CREATE_CITY);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
