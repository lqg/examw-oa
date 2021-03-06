package com.examw.oa.dao.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.examw.oa.dao.impl.BaseDaoImpl;
import com.examw.oa.dao.security.IRoleDao;
import com.examw.oa.domain.security.Role;
import com.examw.oa.model.security.RoleInfo;

/**
 * 角色数据接口实现类。
 * @author yangyong.
 * @since 2014-05-05.
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.dao.admin.IRoleDao#findRoles(com.examw.netplatform.model.admin.RoleInfo)
	 */
	@Override
	public List<Role> findRoles(RoleInfo info) {
		String hql = "from Role r  where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(!StringUtils.isEmpty(info.getSort())){
			hql += " order by r." + info.getSort() + " " + info.getOrder();
		}
		return this.find(hql, parameters, info.getPage(), info.getRows());
	}
	/*
	 * 查询数据总数。
	 * @see com.examw.netplatform.dao.admin.IRoleDao#total(com.examw.netplatform.model.admin.RoleInfo)
	 */
	@Override
	public Long total(RoleInfo info) {
		String hql = "select count(*) from Role r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.count(hql, parameters);
	}
	/**
	 * 添加查询条件到HQL。
	 * @param info
	 * 查询条件。
	 * @param hql
	 * HQL
	 * @param parameters
	 * 参数。
	 * @return
	 * HQL
	 */
	protected String addWhere(RoleInfo info, String hql, Map<String, Object> parameters){
		if(!StringUtils.isEmpty(info.getName())){
			hql += "  and (r.name like :name)";
			parameters.put("name", "%" + info.getName()+ "%");
		}
		if(info.getStatus() != null){
			hql += " and (r.status = :status)";
			parameters.put("status", info.getStatus());
		}
		return hql;
	}
}