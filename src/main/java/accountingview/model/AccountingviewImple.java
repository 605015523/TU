package accountingview.model;

import java.text.SimpleDateFormat;
import java.util.*;

import leaderview.model.memberInVO;

import activities.dao.Activities;
import activities.dao.ActivitiesDAOInterface;

import user_act.dao.User_act;
import user_act.dao.User_actDAOInterface;

import userlogin.dao.Userlogin;
import userlogin.dao.UserloginDAOInterface;

import group.dao.Group;
import group.dao.GroupDAOInterface;

public class AccountingviewImple extends Observable implements
		AccountingviewInterface {

	private UserloginDAOInterface userloginDAO = null;
	private ActivitiesDAOInterface actsDAO = null;
	private User_actDAOInterface user_actDAO = null;
	private GroupDAOInterface groupDAO = null;

	public AccountingviewImple() {

	}

	public UserloginDAOInterface getUserloginDAO() {
		return this.userloginDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	public ActivitiesDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivitiesDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public User_actDAOInterface getUser_actDAO() {
		return this.user_actDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		this.user_actDAO = userActDAO;
	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	// ͨ��ѡ��year�ķ�ʽ��ʾ�����û���һ��Ļ���������ʵ��ϸ��
	public List<UserGroupCostVO> doGetAllActsByYear(Integer year) {
		List<UserGroupCostVO> allUserGroupCostVO = new ArrayList<UserGroupCostVO>();
		List<Activities> allacts = new ArrayList<Activities>();
		allacts = actsDAO.findAll();
		List<Activities> validateacts = new ArrayList<Activities>();

		// ��ȡ�����Լ���validate�Ļ
		for (int i = 0; i < allacts.size(); i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(allacts.get(i).getActDate());
			if (allacts.get(i).getState().equals("validate")
					&& year.equals(cal.get(Calendar.YEAR))) {
				validateacts.add(allacts.get(i));
			}
		}
		ArrayList<Userlogin> allUser = new ArrayList<Userlogin>();
		allUser = (ArrayList) userloginDAO.findAll();

		// ��ȡ�����û������ұ��������û���������
		for (int i = 0; i < allUser.size(); i++) {
			UserGroupCostVO oneUserGroupCostVO = new UserGroupCostVO();
			Integer userId = allUser.get(i).getUserId();
			Userlogin oneuser = userloginDAO.findById(userId);
			oneUserGroupCostVO.setUserId(userId);
			oneUserGroupCostVO.setUserName(allUser.get(i).getUserName());
			ArrayList<GroupCostVO> allGroupCost = new ArrayList<GroupCostVO>();
			List groups = groupDAO.findAll();
			for (int j = 0; j < groups.size(); j++) {
				GroupCostVO onegroupCostVO = new GroupCostVO();
				onegroupCostVO.setGroupId(((Group) groups.get(j)).getGroupId());
				onegroupCostVO.setCost(0);

				allGroupCost.add(onegroupCostVO);
			}
			for (int j = 0; j < validateacts.size(); j++) {
				User_act oneuser_act = new User_act();
				GroupCostVO oneGroupCost = new GroupCostVO();
				Integer actId = validateacts.get(j).getActId();
				Integer groupId = validateacts.get(j).getGroupId();
				try {
					oneuser_act = user_actDAO.findByUserIdAndActId(userId,
							actId);
				} catch (Exception e) {
				}
				if (oneuser_act.getConsumption() != null) {
					for (int m = 0; m < allGroupCost.size(); m++) {
						if (groupId.equals(allGroupCost.get(m).getGroupId())) {
							float sum = (allGroupCost.get(m).getCost());
							allGroupCost.get(m).setCost(
									sum + oneuser_act.getConsumption());
						}
					}
				}
			}
			float sum = 0;
			for (int n = 0; n < allGroupCost.size(); n++) {
				sum += allGroupCost.get(n).getCost();
			}
			oneUserGroupCostVO.setSum(sum);
			oneUserGroupCostVO.setQuota(oneuser.getQuota());
			oneUserGroupCostVO
					.setDifferent(oneUserGroupCostVO.getQuota() - sum);
			oneUserGroupCostVO.setGroupCostVO(allGroupCost);
			allUserGroupCostVO.add(oneUserGroupCostVO);
		}

		return allUserGroupCostVO;
	}

	// ͨ��ѡ��group�ķ�ʽ��ʾ����group��һ��Ļ���������ʵ��ϸ��
	public List<GroupActVO> doGetAllActsByGroupId(Integer groupId) {
		List<Activities> acts = new ArrayList<Activities>();
		acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (int i = 0; i < acts.size(); i++) {
			GroupActVO actsPO = new GroupActVO();
			actsPO.setActId(acts.get(i).getActId());
			actsPO.setActName(acts.get(i).getActName());
			actsPO.setGroupId(acts.get(i).getGroupId());
			actsPO.setActMoney(acts.get(i).getActMoney());
			actsPO.setDescription(acts.get(i).getDescription());
			actsPO.setState(acts.get(i).getState());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String daterange = formatter.format(acts.get(i)
					.getEnrollStartDate())
					+ " - "
					+ formatter.format(acts.get(i).getEnrollEndDate());
			actsPO.setDaterange(daterange);
			actsPO.setActDate(formatter.format(acts.get(i).getActDate()));
			actsPO.setComment(acts.get(i).getComment());
			List<User_act> useractPO = user_actDAO.findByActId(acts.get(i)
					.getActId());
			ArrayList<memberInVO> memberInVO = new ArrayList<memberInVO>();
			float sum = 0;
			Integer participatorNO = 0;
			for (int j = 0; j < useractPO.size(); j++) {
				memberInVO oneMemberIn = new memberInVO();
				oneMemberIn.setUserId(useractPO.get(j).getUserId());
				oneMemberIn.setParticipatorNO(useractPO.get(j)
						.getParticipatorNO());
				oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
				oneMemberIn.setRemark(useractPO.get(j).getRemark());
				sum += useractPO.get(j).getConsumption();
				participatorNO += useractPO.get(j).getParticipatorNO();
				Userlogin userPO = new Userlogin();
				userPO = userloginDAO.findById(useractPO.get(j).getUserId());
				oneMemberIn.setUserName(userPO.getUserName());
				oneMemberIn.setUserDept(userPO.getUserDept());
				memberInVO.add(oneMemberIn);
			}
			actsPO.setParticipatorNO(participatorNO);
			actsPO.setMemberInVO(memberInVO);
			actsPO.setSum(sum);
			actsVO.add(actsPO);
		}
		List<GroupActVO> InverseactsVO = new ArrayList<GroupActVO>();
		for (int j = 0; j < actsVO.size(); j++) {
			InverseactsVO.add(actsVO.get(actsVO.size() - j - 1));
		}

		return InverseactsVO;
	}

	// ��ȡ����validate�Ļ�Ļϸ�ڵľ���ʵ��
	public GroupActVO doGetAllValidateDetails(Integer actId) {
		Activities acts = new Activities();
		acts = actsDAO.findById(actId);
		GroupActVO actVO = new GroupActVO();

		actVO.setActId(acts.getActId());
		actVO.setActName(acts.getActName());
		actVO.setGroupId(acts.getGroupId());
		actVO.setActMoney(acts.getActMoney());
		actVO.setDescription(acts.getDescription());
		actVO.setState(acts.getState());

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String daterange = formatter.format(acts.getEnrollStartDate()) + " - "
				+ formatter.format(acts.getEnrollEndDate());
		actVO.setDaterange(daterange);
		actVO.setActDate(formatter.format(acts.getActDate()));
		actVO.setComment(acts.getComment());
		List<User_act> useractPO = user_actDAO.findByActId(acts.getActId());
		ArrayList<memberInVO> memberInVO = new ArrayList<memberInVO>();
		float sum = 0;
		Integer participatorNO = 0;
		for (int j = 0; j < useractPO.size(); j++) {
			memberInVO oneMemberIn = new memberInVO();
			oneMemberIn.setUserId(useractPO.get(j).getUserId());
			oneMemberIn.setParticipatorNO(useractPO.get(j).getParticipatorNO());
			oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
			oneMemberIn.setRemark(useractPO.get(j).getRemark());
			sum += useractPO.get(j).getConsumption();
			participatorNO += useractPO.get(j).getParticipatorNO();
			Userlogin userPO = new Userlogin();
			userPO = userloginDAO.findById(useractPO.get(j).getUserId());
			oneMemberIn.setUserName(userPO.getUserName());
			oneMemberIn.setUserDept(userPO.getUserDept());
			memberInVO.add(oneMemberIn);
		}
		actVO.setParticipatorNO(participatorNO);
		actVO.setMemberInVO(memberInVO);
		actVO.setSum(sum);

		return actVO;
	}

	// ��ȡ������Ҫ��check��validate�Ļ
	public List<GroupActVO> doGetAllCheckValidateActs() {
		List<Activities> acts = new ArrayList<Activities>();
		acts = actsDAO.findAll();
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (int i = 0; i < acts.size(); i++) {
			GroupActVO actsPO = new GroupActVO();
			actsPO.setActId(acts.get(i).getActId());
			actsPO.setActName(acts.get(i).getActName());
			actsPO.setGroupId(acts.get(i).getGroupId());
			actsPO.setActMoney(acts.get(i).getActMoney());
			actsPO.setDescription(acts.get(i).getDescription());
			actsPO.setState(acts.get(i).getState());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String daterange = formatter.format(acts.get(i)
					.getEnrollStartDate())
					+ " - "
					+ formatter.format(acts.get(i).getEnrollEndDate());
			actsPO.setDaterange(daterange);
			actsPO.setActDate(formatter.format(acts.get(i).getActDate()));
			actsVO.add(actsPO);

		}
		List<GroupActVO> InverseactsVO = new ArrayList<GroupActVO>();
		for (int j = 0; j < actsVO.size(); j++) {
			InverseactsVO.add(actsVO.get(actsVO.size() - j - 1));
		}
		return InverseactsVO;

	}
}
