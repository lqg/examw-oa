package com.examw.oa.service.security.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.examw.oa.dao.security.IRightDao;
import com.examw.oa.domain.security.Right;
import com.examw.oa.model.security.RightInfo;
import com.examw.oa.service.impl.BaseDataServiceImpl;
import com.examw.oa.service.security.IRightService;


/**
 * 基础权限服务接口实现。
 * @author yangyong.
 * @since 2014-05-03.
 */
public class RightServiceImpl extends BaseDataServiceImpl<Right,RightInfo> implements IRightService {
	private IRightDao rightDao;
	private Map<Integer, String> rightNameMap;
	/**
	 * 设置基础权限数据接口。
	 * @param rightDao
	 * 基础权限数据接口。
	 */
	public void setRightDao(IRightDao rightDao) {
		this.rightDao = rightDao;
	}
	/**
	 * 设置权限名称。
	 * @param rightNameMap
	 * 权限名称。
	 */
	public void setRightNameMap(Map<Integer, String> rightNameMap) {
		this.rightNameMap = rightNameMap;
	}
	/*
	 * 查询数据。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#find(java.lang.Object)
	 */
	@Override
	protected List<Right> find(RightInfo info) {
		return this.rightDao.findRights(info);
	}
	/*
	 * 数据类型转换。
	 * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#changeModel(java.lang.Object)
	 */
	@Override
	protected RightInfo changeModel(Right data) {
		if(data == null) return null;
		RightInfo info = new RightInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
    /*
     * 查询数据总数。
     * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#total(java.lang.Object)
     */
	@Override
	protected Long total(RightInfo info) {
		return this.rightDao.total(info);
	}
    /*
     * 更新数据。
     * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#update(java.lang.Object)
     */
	@Override
	public RightInfo update(RightInfo info) {
		if(info == null || StringUtils.isEmpty(info.getId())) return null;
		boolean isAdded = false;
		Right data =  this.rightDao.load(Right.class, info.getId());
		if(isAdded = (data == null)){
			if(StringUtils.isEmpty(info.getId())){
				info.setId(UUID.randomUUID().toString());
			}
			data = new Right();
		}
		BeanUtils.copyProperties(info, data);
		if(isAdded)this.rightDao.save(data);
		return info;
	}
    /*
     * 删除数据。
     * @see com.examw.netplatform.service.impl.BaseDataServiceImpl#delete(java.lang.String[])
     */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			Right data = this.rightDao.load(Right.class, ids[i]);
			if(data != null) this.rightDao.delete(data);
		}
	}
	/*
	 * 初始化数据。
	 * @see com.examw.netplatform.service.admin.IRightService#init()
	 */
	@Override
	public void init() throws Exception {
		//查看
		this.update(new RightInfo(((Integer)Right.VIEW).toString(), this.getRightName(Right.VIEW), Right.VIEW));
		//修改
		this.update(new RightInfo(((Integer)Right.UPDATE).toString(), this.getRightName(Right.UPDATE), Right.UPDATE));
		//删除
		this.update(new RightInfo(((Integer)Right.DELETE).toString(), this.getRightName(Right.DELETE), Right.DELETE));
	}
	/*
	 * 加载权限名称。
	 * @see com.examw.netplatform.service.admin.IRightService#getRightName(int)
	 */
	@Override
	public String getRightName(int right) {
		if(this.rightNameMap == null || this.rightNameMap.size() == 0) return null;
		return this.rightNameMap.get(right);
	}
}