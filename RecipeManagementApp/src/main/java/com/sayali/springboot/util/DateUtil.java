package com.sayali.springboot.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {
	
	public static Date getCurrentDateTime() {
		
		return Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
		
	}

}
