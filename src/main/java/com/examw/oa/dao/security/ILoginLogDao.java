package com.examw.oa.dao.security;

import java.util.List;

import com.examw.oa.dao.IBaseDao;
import com.examw.oa.domain.security.LoginLog;
import com.examw.oa.model.security.LoginLogInfo;

/**
 * 登录日志数据接口。
 * @author yangyong.
 * @since 2014-04-17.
 */
public interface ILoginLogDao extends IBaseDao<LoginLog> {
	/**
	 * 查询数据。
	 * @param info
	 * 查询条件。
	 * @return
	 * 结果数据。
	 */
	List<LoginLog> findLoginLogs(LoginLogInfo info);
	/**
	 * 查询数据总数。
	 * @param info
	 * 查询条件。
	 * @return
	 * 数据总数。
	 */
	Long total(LoginLogInfo info);
}