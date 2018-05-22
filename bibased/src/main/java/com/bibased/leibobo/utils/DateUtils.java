package com.bibased.leibobo.utils;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by boboLei on 2018/5/10.
 */
public class DateUtils {
	//yyyy-MM-dd
	public String getStringDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date2 = df.format(new Date());
		return date2;
	}
	public Date getDate(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		return strToDate(dateFormat.format(date));
	}

	public  String getNextCountDay(Date date,int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, count);
		date = calendar.getTime();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public  Date strToDate(String strDate) {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    ParsePosition pos = new ParsePosition(0);
		    Date strtodate = formatter.parse(strDate, pos);
		    return strtodate;
		 }
}
