package com.examw.oa.controllers.security;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.model.DataGrid;
import com.examw.model.Json;
import com.examw.oa.model.security.RightInfo;
import com.examw.oa.service.security.IRightService;
/**
 * 权限控制器。
 * @author yangyong.
 * @since 2014-05-03.
 */
@Controller
@RequestMapping(value = "/security/right")
public class RightController {
	private static Logger logger = Logger.getLogger(RightController.class);
	
	@Resource
	private IRightService rightService;
	/**
	 * 加载列表页面。
	 * @return
	 * 列表页面。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value = {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		//model.addAttribute("PER_UPDATE", ModuleConstant.SECURITY_RIGHT + ":" + Right.UPDATE);
		//model.addAttribute("PER_DELETE", ModuleConstant.SECURITY_RIGHT + ":" + Right.DELETE);
		return "security/right_list";
	}
	/**
	 * 查询数据。
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.VIEW})
	@RequestMapping(value="/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<RightInfo> datagrid(RightInfo info){
		return this.rightService.datagrid(info);
	}
	/**
	 * 初始化数据。
	 * @return
	 * 初始化结果。
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value="/init", method = RequestMethod.POST)
	@ResponseBody
	public Json init(){
		Json result = new Json();
		try {
			this.rightService.init();
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("初始化基础权限数据发生异常", e);
		}
		return result;
	}
	/**
	 * 删除数据。
	 * @param id
	 * @return
	 */
	//@RequiresPermissions({ModuleConstant.SECURITY_RIGHT + ":" + Right.UPDATE})
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Json delete(String id){
		Json result = new Json();
		try {
			this.rightService.delete(id.split("\\|"));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			logger.error("删除数据["+id+"]时发生异常:", e);
		}
		return result;
	}
}