package com.gw.das.web.controller.market;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.das.common.easyui.PageGrid;
import com.gw.das.common.enums.CommitStatusEnum;
import com.gw.das.common.enums.SendStatusEnum;
import com.gw.das.common.enums.SmsChannelEnum;
import com.gw.das.common.utils.StringUtil;
import com.gw.das.dao.market.entity.SmsDetailEntity;
import com.gw.das.service.market.SmsDetailService;
import com.gw.das.web.controller.system.BaseController;

@Controller
@RequestMapping("/SmsDetailController")
public class SmsDetailController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SmsDetailController.class);

	@Autowired
	private SmsDetailService smsDetailService;
	
	/**
	 * 跳转管理页面
	 */
	@RequestMapping(value = "/page", method = { RequestMethod.GET })
	public String page(HttpServletRequest request) {
		try {
			request.setAttribute("commitStatus", CommitStatusEnum.getList());
			request.setAttribute("sendStatus", SendStatusEnum.getList());
			request.setAttribute("smsId", request.getParameter("smsId"));
			return "/market/sms/smsDetail";
		} catch (Exception e) {
			logger.error("系统出现异常:" + e.getMessage(), e);
			return "/404";
		}
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pageList", method = { RequestMethod.POST })
	@ResponseBody
	public PageGrid<SmsDetailEntity> pageList(HttpServletRequest request,
			@ModelAttribute SmsDetailEntity smsDetailEntity) {
		try {
			PageGrid<SmsDetailEntity> pageGrid = smsDetailService.findPageList(super.createPageGrid(request, smsDetailEntity));
			for(Object obj: pageGrid.getRows()){
				SmsDetailEntity record = (SmsDetailEntity)obj;
				record.setCommitStatus(CommitStatusEnum.format(record.getCommitStatus()));
				record.setSendStatus(SendStatusEnum.format(record.getSendStatus()));
				record.setPhone(StringUtil.formatPhone(record.getPhone()));
				record.setInterfaceType(SmsChannelEnum.format(record.getInterfaceType()));
			}
			return pageGrid;
		} catch (Exception e) {
			logger.error("系统出现异常:" + e.getMessage(), e);
			return new PageGrid<SmsDetailEntity>();
		}
	}
	
}
