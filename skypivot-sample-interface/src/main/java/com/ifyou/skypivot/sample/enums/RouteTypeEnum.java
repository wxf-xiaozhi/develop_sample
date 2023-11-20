package com.ifyou.skypivot.sample.enums;

public enum RouteTypeEnum {

	ROAD("主干道行驶", 1), JOB_REGION("作业区行驶", 2), UNKNOW("未知", -1);

	private String displayName;
	private int code;


	RouteTypeEnum(String displayName, int code) {
		this.displayName = displayName;
		this.code = code;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public int getCode() {
		return code;
	}

	public static RouteTypeEnum valueOf(int value) {
        switch (value) {
        case -1:
        	return RouteTypeEnum.UNKNOW;
	    case 1:
	        return RouteTypeEnum.ROAD;
	    case 2:
	        return RouteTypeEnum.JOB_REGION;
	    default:
            return null;

        }
    }
}
