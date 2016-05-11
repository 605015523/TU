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
	 * param obj 传递查询的JAVABEAN对象 param map 保存用户输入的模糊查询条件
	 * map.put("属性名称",ActionForm.getXXX());
	 */
	public List findAllByCriteria(Object obj, Map map) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(obj
				.getClass());
		Class classType = obj.getClass();
		Field[] fields = classType.getDeclaredFields(); // 利用反射得到传递进来javabean的所有属性
		for (int i = 0; i < fields.length; i++) {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				System.out.println("Map中的key值：" + key);
				System.out.println("属性名称：" + fields[i].getName());
				if (fields[i].getName().equals(key)) { // 判断属性名称是否等于Map中的key值
					System.out.println("属性名称是否等于Map中的key值："
							+ fields[i].getName().equals(key));
					if (!"".equals(map.get(key).toString())
							&& null != map.get(key).toString()) {
						System.out.println("DAO利用反射得到传递进来javabean的属性："
								+ fields[i].getName());
						System.out.println("DAO利用map得到传递进来javabean的属性：" + key
								+ "  " + map.get(key));
						// 判断传递的属性是什么类型，项目中查询的是两个时间段之间
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
						// 如果是字符串类型
						else if (map.get(key) instanceof String) {
							detachedCriteria.add(Restrictions.ilike(fields[i]
									.getName().toString(), map.get(key)
									.toString(), MatchMode.ANYWHERE));
						}
						// 如果是整型
						else if (map.get(key) instanceof Integer) {
							detachedCriteria.add(Restrictions.eq(fields[i]
									.getName().toString(), map.get(key)));
						}
						// 如果是实体对象
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
		System.out.println("DAO通用模糊查询得到的记录个数：" + currpagedatas.size());
		return currpagedatas;
	}

}