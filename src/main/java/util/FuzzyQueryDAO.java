package util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class FuzzyQueryDAO extends HibernateDaoSupport implements
		FuzzyQueryDAOInterface {

	/**
	 * param obj ���ݲ�ѯ��JAVABEAN���� param map �����û������ģ����ѯ����
	 * map.put("��������",ActionForm.getXXX());
	 */
	public List findAllByCriteria(Object obj, Map map) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(obj
				.getClass());
		Class classType = obj.getClass();
		Field[] fields = classType.getDeclaredFields(); // ���÷���õ����ݽ���javabean����������
		for (int i = 0; i < fields.length; i++) {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				System.out.println("Map�е�keyֵ��" + key);
				System.out.println("�������ƣ�" + fields[i].getName());
				if (fields[i].getName().equals(key)) { // �ж����������Ƿ����Map�е�keyֵ
					System.out.println("���������Ƿ����Map�е�keyֵ��"
							+ fields[i].getName().equals(key));
					if (!"".equals(map.get(key).toString())
							&& null != map.get(key).toString()) {
						System.out.println("DAO���÷���õ����ݽ���javabean�����ԣ�"
								+ fields[i].getName());
						System.out.println("DAO����map�õ����ݽ���javabean�����ԣ�" + key
								+ "  " + map.get(key));
						// �жϴ��ݵ�������ʲô���ͣ���Ŀ�в�ѯ��������ʱ���֮��
						if (map.get(key) instanceof Date[]) {
							Date[] dates = (Date[]) map.get(key);
							try {
								if (!"".equals(dates[0]) && null != dates[0]) {
									detachedCriteria.add(Restrictions.ge(
											fields[i].getName().toString(),
											dates[0]));
								}
								if (!"".equals(dates[1]) && null != dates[1]) {
									detachedCriteria.add(Restrictions.le(
											fields[i].getName().toString(),
											dates[1]));
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						// ������ַ�������
						else if (map.get(key) instanceof String) {
							detachedCriteria.add(Restrictions.ilike(fields[i]
									.getName().toString(), map.get(key)
									.toString(), MatchMode.ANYWHERE));
						}
						// ���������
						else if (map.get(key) instanceof Integer) {
							detachedCriteria.add(Restrictions.eq(fields[i]
									.getName().toString(), map.get(key)));
						}
						// �����ʵ�����
						else {
							detachedCriteria.add(Restrictions.eq(fields[i]
									.getName().toString(), map.get(key)));
						}
					}
				}
			}
		}
		detachedCriteria.setProjection(null);
		List currpagedatas = getHibernateTemplate().findByCriteria(
				detachedCriteria);
		System.out.println("DAOͨ��ģ����ѯ�õ��ļ�¼������" + currpagedatas.size());
		return currpagedatas;
	}

}