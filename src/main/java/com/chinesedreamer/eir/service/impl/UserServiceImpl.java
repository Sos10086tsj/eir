package com.chinesedreamer.eir.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.eir.domain.dao.UserDao;
import com.chinesedreamer.eir.domain.model.User;
import com.chinesedreamer.eir.exception.ErrorMessage;
import com.chinesedreamer.eir.exception.ErrorMessageCode;
import com.chinesedreamer.eir.exception.user.UserException;
import com.chinesedreamer.eir.exception.user.UserLoginUnknownException;
import com.chinesedreamer.eir.exception.user.UserRegisterFailureException;
import com.chinesedreamer.eir.exception.user.UsernameNotExistException;
import com.chinesedreamer.eir.exception.user.UsernamePassNotMatchException;
import com.chinesedreamer.eir.service.UserService;
import com.chinesedreamer.eir.util.EncryptionUtil;
import com.chinesedreamer.eir.util.IdUtil;
import com.chinesedreamer.eir.util.SessionUtil;
import com.chinesedreamer.eir.util.StringUtil;
import com.chinesedreamer.eir.vo.model.user.UserPassVo;
import com.chinesedreamer.eir.vo.model.user.UserProfileVo;
import com.chinesedreamer.eir.vo.model.user.UserRegisterVo;

/**
 * Description:
 * Auth:Paris
 * Date:Nov 16, 2016
**/
@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger("EIR");

	@Resource
	private UserDao dao;
	@Override
	public UserProfileVo getUser(String username) {
		User user = this.dao.findByUsername(username);
		return this.model2Vo(user);
	}
	
	private UserProfileVo model2Vo(User user){
		UserProfileVo vo = new UserProfileVo();
		if (null != user) {
			vo.setName(vo.getName());
			vo.setUsername(user.getUsername());
		}
		return vo;
	}

	@Override
	public void updateProfile(UserProfileVo vo) {
		User user = this.dao.findByUsername(vo.getUsername());
		if (null == user) {
			this.logger.info("Not matched uername {}.", vo.getUsername());
			return;
		}
		user.setName(vo.getName());
		this.dao.update(user);
	}

	@Override
	public void updatePassword(UserPassVo vo) throws UserException {
		// TODO Auto-generated method stub
		User user = this.dao.findByUsername(vo.getUsername());
		if (null == user) {
			this.logger.info("Not matched uername:{}.", vo.getUsername());
			return; 
		}
		try {
			if (!EncryptionUtil.md5L32(vo.getOldPass() + user.getSalt(), null).equals(user.getPassword())) {
				ErrorMessage errorMessage = StringUtil.getErrorMessage(ErrorMessageCode.USER.CODE, ErrorMessageCode.USER.USERNAME_PASS_NOT_MATCH);
				throw new UsernamePassNotMatchException(errorMessage);
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
	}

	@Override
	public void update2ResetPassword(String username, String email) throws UserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update2RetrievePassword(String token) throws UserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRegister(UserRegisterVo vo) throws UserException {
		try {
			User user = new User();
			user.setName(vo.getName());
			user.setUsername(vo.getUsername());
			user.setSalt(IdUtil.generateSalt(6));
			user.setPassword(EncryptionUtil.md5L32(vo.getPassword() + user.getSalt(), null));
			this.dao.save(user);
		} catch (Exception e) {
			this.logger.error("{}",e);
			ErrorMessage errorMessage = StringUtil.getErrorMessage(ErrorMessageCode.USER.CODE, ErrorMessageCode.USER.USER_REGISTER_FAILURE);
			throw new UserRegisterFailureException(errorMessage);
		}
	}

	@Override
	public void login(String username, String password) throws UserException {
		User user = this.dao.findByUsername(username);
		if (null == user) {
			ErrorMessage errorMessage = StringUtil.getErrorMessage(ErrorMessageCode.USER.CODE, ErrorMessageCode.USER.USER_NOT_EXIST, username);
			throw new UsernameNotExistException(errorMessage);
		}
		try {
			if (!EncryptionUtil.md5L32(password + user.getSalt(), null).equals(user.getPassword())) {
				ErrorMessage errorMessage = StringUtil.getErrorMessage(ErrorMessageCode.USER.CODE, ErrorMessageCode.USER.USERNAME_PASS_NOT_MATCH);
				throw new UsernamePassNotMatchException(errorMessage);
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
			throw new UserLoginUnknownException(new ErrorMessage(ErrorMessageCode.SYSTEM.UNEXPECTED_ERROR_MESSAGE_CODE, ErrorMessageCode.SYSTEM.UNEXPECTED_ERROR_MESSAGE_MSG));
		}
		SessionUtil.addUserId(user.getId());
	}

}
