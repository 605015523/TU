package util;

import java.util.List;
import java.util.Map;

public interface FuzzyQueryDAOInterface {

	/**
	 * param obj ���ݲ�ѯ��JAVABEAN���� param map �����û������ģ����ѯ����
	 * map.put("��������",ActionForm.getXXX());
	 */
	public List findAllByCriteria(Object obj, Map map);
}