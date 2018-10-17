package com.tb.rx_retrofit.tools;

import android.util.Log;

import com.tb.tbretrofit.BuildConfig;

/**
 * @描述： －
 * -
 * @作者：zhusw
 * @创建时间：17/11/16 上午11:13
 * @最后更新时间：17/11/16 上午11:13
 */
public class TBHttpLog {

	private static boolean deBug = true;
	private static String str = "";
	private final static  int LOG_MAXLENGTH = 1000;

	public static  void setDeBug(boolean isDebug){
		deBug = isDebug;
	}

	public static void printI(String TAG, String MSG){
		if(deBug){
			TAG = "TBHttpLog:"+TAG;
			int strLenght  = MSG.length();
			int start  = 0;
			int end = LOG_MAXLENGTH;
			for(int i = 0;i<100;i++){
				if((strLenght-end)> LOG_MAXLENGTH ){
					Log.i(TAG,MSG.substring(start,end));
					start = end;
					end += LOG_MAXLENGTH;
				}else {
					Log.i(TAG+":END",MSG.substring(start,strLenght));
					break;
				}
			}
		}
	}

	public static void printE(String TAG, String MSG){
		if(deBug){
			TAG = "TBHttpLog:"+TAG;
			int strLenght  = MSG.length();
			int start  = 0;
			int end = LOG_MAXLENGTH;
			for(int i = 0;i<100;i++){
				if((strLenght-end)> LOG_MAXLENGTH ){
					Log.e(TAG,MSG.substring(start,end));
					start = end;
					end += LOG_MAXLENGTH;
				}else {
					Log.e(TAG+":END",MSG.substring(start,strLenght));
					break;
				}
			}
		}
	}

}
