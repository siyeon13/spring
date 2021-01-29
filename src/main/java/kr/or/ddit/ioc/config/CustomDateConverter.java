package kr.or.ddit.ioc.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date>{
	
	private String dateFormat; 		// xml 에서 날짜 형식을 바꿔줌
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	// source : 2021-01-11, yyyy-MM-dd
	@Override
	public Date convert(String source) {
		
		SimpleDateFormat sdf = new  SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

}
