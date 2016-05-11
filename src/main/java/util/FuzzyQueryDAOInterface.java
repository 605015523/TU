package util;

import java.util.List;
import java.util.Map;

public interface FuzzyQueryDAOInterface {

	/**
	 * param obj 传递查询的JAVABEAN对象 param map 保存用户输入的模糊查询条件
	 * map.put("属性名称",ActionForm.getXXX());
	 */
	public List findAllByCriteria(Object obj, Map map);
}