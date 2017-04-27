package org.silkroad.controller;

import org.silkroad.history.CalUserViewTimes;
import org.silkroad.history.CompleteUserViewedResInfo;
import org.silkroad.history.GenUserViewedRes;
import org.silkroad.hot.CalHotResources;
import org.silkroad.hot.CompleteHotResInfo;
import org.silkroad.processing.logs.ClassifyLogsByRole;
import org.silkroad.processing.logs.ProcessLogs;

/**
* @author : wuke
* @date   : 2017年4月27日下午4:45:16
* Title   : InitializeSilkRoad
* Description : 
*/
public class InitializeSilkRoad {
	private static final String PATH = "E:\\data\\silkroad_logs.txt"; // the catalog where logs are stored
	
	public static void main(String[] args) {
		InitializeSilkRoad.initSilkRoad();
	}
	
    public static void initSilkRoad() {
		long start = System.currentTimeMillis();
    	
		ProcessLogs.initProcessLogs(PATH);
		ClassifyLogsByRole.classifyLogsByRole();
		System.out.println("******************** 日志处理成功！********************");
		long tag1 = System.currentTimeMillis();
		System.out.println("用了 " + (tag1 - start)/1000 + " 秒");
		
		GenUserViewedRes.readLogs();
		System.out.println("******************** 用户浏览历史生成成功！********************");
		long tag2 = System.currentTimeMillis();
		System.out.println("用了 " + (tag2 - tag1)/1000 + " 秒");
		
		CalUserViewTimes.calAllUsersViewedTimes();
		CalHotResources.calAllRolesHotRes();
		System.out.println("******************** 用户浏览次数及热度资源计算成功！********************");
		long tag3 = System.currentTimeMillis();
		System.out.println("用了 " + (tag3 - tag2)/1000 + " 秒");
		
		CompleteUserViewedResInfo.completeAllKindsResInfo();
		CompleteHotResInfo.completeAllHotResInfo();
		System.out.println("******************** 浏览历史资源及热度资源信息补全成功！********************");
		long tag4 = System.currentTimeMillis();
		System.out.println("用了 " + (tag4 - tag3)/1000 + " 秒");
		
		System.out.println("总共用了 " + (tag4 - start)/1000 + " 秒");
	}
}
