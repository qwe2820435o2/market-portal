package com.kris.portal.service.impl;

import com.kris.portal.mapper.SysUserMapper;
import com.kris.portal.pojo.SysUser;
import com.kris.portal.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Resource
	private SysUserMapper sysUserMapper;


	@Override
	public SysUser getById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
}
