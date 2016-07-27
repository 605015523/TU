package com.tu.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.group.Group;
import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.messages.Message;
import com.tu.dao.messages.MessagesDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.user.msg.UserMsg;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.leaderview.MemberInVO;
import com.tu.model.messages.MessageVO;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;

public class DAOModelMapper {
	private static final Log LOGGER = LogFactory.getLog(DAOModelMapper.class);
	private MessagesDAOInterface msgDAO;
	private GroupDAOInterface groupDAO;
	private UserActDAOInterface userActDAO;
	private UserloginDAOInterface userloginDAO;
	

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
	
	public UserMsgVO convertoUserMsgToUserMsgVO(UserMsg userMsg) {
		UserMsgVO oneUserMsgVO = new UserMsgVO();
		Integer oneMsgId = userMsg.getMsgId();
		Message oneMessage = msgDAO.findById(oneMsgId);
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneUserMsgVO, oneMessage);
			oneUserMsgVO.setUserId(userMsg.getUserId());
			oneUserMsgVO.setReadState(userMsg.getReadState());
			Group group = groupDAO.findById(oneMessage.getGroupId());
			oneUserMsgVO.setGroupName(group.getGroupName());
			
			MessageVO message = new MessageVO();
			BeanUtils.copyProperties(message, oneMessage);
			
			ActivityVO activity = new ActivityVO();
			BeanUtils.copyProperties(activity, oneMessage);
			message.setActivity(activity);
			
			oneUserMsgVO.setMessage(message);
			
		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException while copy oneMessage to oneUserMsgVO: ", e);
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException while copy oneMessage to oneUserMsgVO: ", e);
		}
		
		return oneUserMsgVO;
	}
	
	public Message convertMesssageVOTOMessage(MessageVO oneMessagesVO) {
		Message onemsgPO = new Message();
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(onemsgPO, oneMessagesVO);
			BeanUtils.copyProperties(onemsgPO, oneMessagesVO.getActivity());
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e);
		}
		return onemsgPO;
	}
	
	public ActivityVO convertActivityToActivityVO(Activity actPO) {
		ActivityVO actVO = new ActivityVO();
		try {
			BeanUtils.copyProperties(actVO, actPO);

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

		List<UserAct> useractPO = userActDAO.findByActId(act
				.getActId());
		List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();
		float sum = 0;
		Integer nbParticipants = 0;
		for (UserAct userAct : useractPO) {
			MemberInVO oneMemberIn = new MemberInVO();
			oneMemberIn.setUserId(userAct.getUserId());
			oneMemberIn.setNbParticipants(userAct.getNbParticipants());
			oneMemberIn.setConsumption(userAct.getConsumption());
			oneMemberIn.setRemark(userAct.getRemark());
			sum += userAct.getConsumption();
			nbParticipants += userAct.getNbParticipants();
			Userlogin userPO = userloginDAO.findById(userAct.getUserId());
			oneMemberIn.setUserName(userPO.getUserName());
			oneMemberIn.setUserDept(userPO.getUserDept());
			memberInVO.add(oneMemberIn);
		}
		actsPO.setNbParticipants(nbParticipants);
		actsPO.setMemberInVO(memberInVO);
		actsPO.setSum(sum);
		
		return actsPO;
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

}
