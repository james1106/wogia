package com.magic.wogia.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.magic.wogia.service.ProjectService;

/**
 * 
 * 功能：定时器
 * 编写人员：lzh
 * 时间：2016年10月14日上午9:14:38
 */
@Component("StringTask")
public class StringTask {

	@Resource
	private ProjectService projectService;
	/**
	 * 盯一盯定时推送
	 */
	@Scheduled(cron="0 0 */2 * * ?")
	public void stareOneStareTask(){
		try {
			projectService.stareOneStareTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
