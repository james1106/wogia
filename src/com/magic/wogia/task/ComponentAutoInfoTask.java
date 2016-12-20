package com.magic.wogia.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.magic.wogia.service.ComponentDeviceService;

/**
 *    定时查询 推送 到期10天内需要保养的零件
 * @author QimouXie
 *
 */
public class ComponentAutoInfoTask extends QuartzJobBean {

	private ComponentDeviceService componentDeviceService;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		try {
			SchedulerContext skedCtx = arg0.getScheduler().getContext();
			componentDeviceService = (ComponentDeviceService) skedCtx.get("componentDeviceService");
			componentDeviceService.getAllComponentWillDue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
