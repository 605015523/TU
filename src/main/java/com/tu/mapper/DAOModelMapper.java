package com.tu.mapper;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.dao.group.Group;
import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.messages.Message;
import com.tu.dao.messages.MessagesDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.user.act.UserActVO;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserActDetailedVO;

public class DAOModelMapper {
	private static final Log LOGGER = LogFactory.getLog(DAOModelMapper.class);
	private MessagesDAOInterface msgDAO;
	private GroupDAOInterface groupDAO;
	private UserActDAOInterface userActDAO;
	private UserloginDAOInterface userloginDAO;
	private ActivityDAOInterface actDAO;
	

	public UserloginVO convertoToUserInfoVO(Userlogin userInfoPO) {
		UserloginVO userInfoVO = new UserloginVO();
		try {
			PropertyUtils.copyProperties(userInfoVO, userInfoPO);
		} catch (IllegalAccessException e) {
			LOGGER.error("出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOGGER.error("在出现了InvocationTargetException异常");
		} catch (NoSuchMethodException e) {
			LOGGER.error("Method not found" + e.getMessage());
		}
		return userInfoVO;
	}
	
	public ActivityVO convertActivityToActivityVO(Activity actPO) {
		ActivityVO actVO = new ActivityVO();
		try {
			BeanUtils.copyProperties(actVO, actPO);
			
			Group groupPO = groupDAO.findById(actPO.getGroupId());
			actVO.setGroupName(groupPO.getGroupName());

		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e);
		}
		
		return actVO;
	}
	
	public GroupActVO convertActivityToGroupActVO(Activity act) {
		GroupActVO actsPO = new GroupActVO();
		
		ActivityVO actVO = convertActivityToActivityVO(act);
		actsPO.setActivity(actVO);

		Integer actId = act.getActId();
		List<UserAct> useractPO = userActDAO.findByActId(actId);
		List<UserActVO> memberInVO = new ArrayList<UserActVO>();
		BigDecimal sum = new BigDecimal(0);
		Integer nbParticipants = 0;
		for (UserAct userAct : useractPO) {
			UserActVO oneMemberIn = new UserActVO();
			
			oneMemberIn.setNbParticipants(userAct.getNbParticipants());
			oneMemberIn.setConsumption(userAct.getConsumption());
			oneMemberIn.setRemark(userAct.getRemark());
			oneMemberIn.setActId(actId);
			sum.add(userAct.getConsumption());
			nbParticipants += userAct.getNbParticipants();
			Userlogin userPO = userloginDAO.findById(userAct.getUserId());
			oneMemberIn.setUser(convertoToUserInfoVO(userPO));
			memberInVO.add(oneMemberIn);
		}
		actsPO.setNbParticipants(nbParticipants);
		actsPO.setMemberInVO(memberInVO);
		actsPO.setSum(sum);
		
		return actsPO;
	}
	
	public UserActDetailedVO createUserActDetailed(UserAct userAct, Activity actPO) {
		UserActDetailedVO useractsVO = new UserActDetailedVO();
		
		Userlogin userPO = userloginDAO.findById(userAct.getUserId());
		
		useractsVO.setUser(convertoToUserInfoVO(userPO));
		useractsVO.setActId(userAct.getActId());
		useractsVO.setNbParticipants(userAct.getNbParticipants());
		useractsVO.setConsumption(userAct.getConsumption());
		useractsVO.setRemark(userAct.getRemark());

		useractsVO.setActivity(convertActivityToActivityVO(actPO));
		
		
		
		return useractsVO;
	}
	
	public MessagesDAOInterface getMsgDAO() {
		return msgDAO;
	}

	public void setMsgDAO(MessagesDAOInterface msgDAO) {
		this.msgDAO = msgDAO;
	}
	
	public GroupDAOInterface getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}
	
	public UserActDAOInterface getUserActDAO() {
		return userActDAO;
	}

	public void setUserActDAO(UserActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	public UserloginDAOInterface getUserloginDAO() {
		return userloginDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	public ActivityDAOInterface getActDAO() {
		return actDAO;
	}

	public void setActDAO(ActivityDAOInterface actDAO) {
		this.actDAO = actDAO;
	}

}
