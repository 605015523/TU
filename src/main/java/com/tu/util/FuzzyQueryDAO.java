package com.tu.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class FuzzyQueryDAO extends HibernateDaoSupport implements
		FuzzyQueryDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(FuzzyQueryDAO.class);

	/**
	 * param obj 传递查询的JAVABEAN对象 param map 保存用户输入的模糊查询条件
	 * map.put("属性名称",ActionForm.getXXX());
	 */
	@Override
	public List findAllByCriteria(Object obj, Map map) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(obj
				.getClass());
		Class classType = obj.getClass();
		Field[] fields = classType.getDeclaredFields(); // 利用反射得到传递进来javabean的所有属性
		for (int i = 0; i < fields.length; i++) {
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = map.get(key);
				
				LOGGER.info("Map中的key值: " + key);
				LOGGER.info("属性名称: " + fields[i].getName());
				if (fields[i].getName().equals(key)) { // 判断属性名称是否等于Map中的key值
					LOGGER.info("属性名称是否等于Map中的key值: "
							+ fields[i].getName().equals(key));
					if (StringUtils.isNotEmpty(value.toString())) {
						LOGGER.info("DAO利用反射得到传递进来javabean的属性: "
								+ fields[i].getName());
						LOGGER.info("DAO利用map得到传递进来javabean的属性: " + key
								+ "  " + value);
						// 判断传递的属性是什么类型，项目中查询的是两个时间段之间
						if (value instanceof Date[]) {
							Date[] dates = (Date[]) value;
							try {
								if (null != dates[0]) {
									detachedCriteria.add(Restrictions.ge(
											fields[i].getName().toString(),
											dates[0]));
								}
								if (null != dates[1]) {
									detachedCriteria.add(Restrictions.le(
											fields[i].getName().toString(),
											dates[1]));
								}
							} catch (Exception e) {
								LOGGER.error(e.toString());
							}
						}
						// 如果是字符串类型
						else if (value instanceof String) {
							detachedCriteria.add(Restrictions.ilike(fields[i]
									.getName().toString(), value
									.toString(), MatchMode.ANYWHERE));
						}
						// 如果是整型
						else if (value instanceof Integer) {
							detachedCriteria.add(Restrictions.eq(fields[i]
									.getName().toString(), value));
						}
						// 如果是实体对象
						else {
							detachedCriteria.add(Restrictions.eq(fields[i]
									.getName().toString(), value));
						}
					}
				}
			}
		}
		detachedCriteria.setProjection(null);
		List currpagedatas = getHibernateTemplate().findByCriteria(
				detachedCriteria);
		LOGGER.info("DAO通用模糊查询得到的记录个数: " + currpagedatas.size());
		return currpagedatas;
	}

}