package com.kh.strap.admin.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.strap.admin.service.AdminService;
import com.kh.strap.admin.store.AdminStore;
import com.kh.strap.member.domain.Member;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private SqlSession session;
	@Autowired
	private AdminStore aStore;
	
	@Override
	public String adminPwdById(String memberId) {
		String aOne = aStore.selectAdminPwdById(session, memberId);
		return aOne;
	}

	@Override
	public Member adminById(String memberId) {
		Member mOne = aStore.selectAdminById(session, memberId);
		return mOne;
	}
	
	
	
}
