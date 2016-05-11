package group.model;

import group.dao.Group;
import group.dao.GroupDAOInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;

public class GroupImple extends Observable implements GroupInterface {

	private GroupDAOInterface groupDAO = null;

	public GroupImple() {

	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	public GroupVO dogetOneGroup(Integer userId) {
		GroupVO groupVO = new GroupVO();
		Group groupPO = new Group();
		groupPO = groupDAO.findByUserId(userId);
		try {
			BeanUtils.copyProperties(groupVO, groupPO);

		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}

		return groupVO;
	}

	public GroupVO dogetOneGroupByName(String groupName) {
		GroupVO groupVO = new GroupVO();
		Group groupPO = new Group();
		groupPO = groupDAO.findByGroupName(groupName);
		try {
			BeanUtils.copyProperties(groupVO, groupPO);

		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}

		return groupVO;
	}

	public ArrayList<GroupVO> dogetAllGroup() {
		ArrayList<GroupVO> allGroupVO = new ArrayList<GroupVO>();
		ArrayList<Group> allGroupPO = new ArrayList<Group>();
		allGroupPO = (ArrayList<Group>) groupDAO.findAll();
		for (int i = 0; i < allGroupPO.size(); i++) {
			GroupVO oneGroupVO = new GroupVO();
			Group oneGroupPO = allGroupPO.get(i);
			try {
				BeanUtils.copyProperties(oneGroupVO, oneGroupPO);
				allGroupVO.add(oneGroupVO);

			} catch (IllegalAccessException e) {
				System.out.println(" there is a IllegalAccessException");
			} catch (InvocationTargetException e) {
				System.out.println("there is a InvocationTargetException");
			}
		}

		return allGroupVO;
	}

}
