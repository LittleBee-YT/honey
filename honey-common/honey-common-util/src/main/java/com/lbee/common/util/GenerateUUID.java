package com.lbee.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class GenerateUUID {
	/**
	 * 生成36位的UUID
	 * 
	 * @return
	 */
	public static String generateUUIDAt32() {
		String uuid = UUID.randomUUID().toString();
		if(StringUtils.isBlank(uuid)){
			return "";
		}
		uuid = uuid.replace("-", "");
		return uuid.toUpperCase();
	}


	public static String generateUUIDAt36() {
		String uuid = UUID.randomUUID().toString();
		if(StringUtils.isBlank(uuid)){
			return "";
		}
		//uuid = uuid.replace("-", "");
		return uuid.toUpperCase();
	}

	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
			System.out.println(generateUUIDAt36());
			//System.out.println("========" + generateUUIDAt36().length());
		}
	}
}
