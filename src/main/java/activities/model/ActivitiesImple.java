package activities.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import org.apache.commons.beanutils.BeanUtils;
import activities.dao.Activities;
import activities.dao.ActivitiesDAOInterface;

public class ActivitiesImple extends Observable implements ActivitiesInterface {

	private ActivitiesDAOInterface actsDAO = null;

	public ActivitiesDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivitiesDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public ActivitiesImple() {
		// ���췽��
	}

	// ��ȡ���л
	public ArrayList<ActivitiesVO> doGetAllActivity() {
		ArrayList<ActivitiesVO> activitiesVOs = new ArrayList<ActivitiesVO>();
		ArrayList<Activities> activitiesPOs = new ArrayList<Activities>();
		activitiesPOs = (ArrayList) actsDAO.findAll();
		for (int i = 0; i < activitiesPOs.size(); i++) {
			Activities oneactivitiesPO = new Activities();
			oneactivitiesPO = activitiesPOs.get(i);
			ActivitiesVO oneactivitiesVO = new ActivitiesVO();
			try {
				BeanUtils.copyProperties(oneactivitiesVO, oneactivitiesPO);
				activitiesVOs.add(oneactivitiesVO);
			} catch (IllegalAccessException e) {
				System.out.println(" there is a IllegalAccessException");
			} catch (InvocationTargetException e) {
				System.out.println("there is a InvocationTargetException");
			}
		}
		return activitiesVOs;
	}

	// ͨ��actId��ȡһ���
	public ActivitiesVO doGetOneActById(Integer actId) {
		ActivitiesVO actVO = new ActivitiesVO();
		Activities actPO = new Activities();
		actPO = actsDAO.findById(actId);
		try {
			BeanUtils.copyProperties(actVO, actPO);

		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}
		return actVO;
	}

	// ���һ���
	public Integer doAddOneAct(ActivitiesVO oneActivitiesVO) {
		Activities oneactsPO = new Activities();
		Integer actId = null;
		String addMessage = null;

		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(oneactsPO, oneActivitiesVO);
		} catch (IllegalAccessException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������InvocationTargetException�쳣");
		}
		try {
			actId = actsDAO.save(oneactsPO);
			addMessage = "save success";
		} catch (Exception e) {
			addMessage = e.toString();
		}
		return actId;
	}

	// ����һ���
	public String doUpdateOneAct(ActivitiesVO oneActVO) {
		Activities oneActPO = new Activities();
		Integer actId = oneActVO.getActId();
		oneActPO = actsDAO.findById(actId);
		String OkOrNot = null;
		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(oneActPO, oneActVO);
		} catch (IllegalAccessException e) {
			System.out
					.println("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			System.out
					.println("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			actsDAO.merge(oneActPO);
			OkOrNot = "merge success!";
		} catch (Exception e) {
			OkOrNot = e.toString();
		}

		return OkOrNot;
	}

	// ɾ��һ���
	public String doDeleteOneActivities(ActivitiesVO oneActivitiesVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
