package com.yx.servlet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestOperation {
	public Object excute(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		Map<String, String[]> requestMap = request.getParameterMap();
		Iterator<String> it = requestMap.keySet().iterator();
		Map<String, Object> objMap = new HashMap<String, Object>();
		Map<String, Method> fieldMethod = new HashMap<String, Method>();
		clazzPara(fieldMethod, objMap, obj, "");
		while(it.hasNext()){
			String para = it.next();
			String objPara = para.substring(0, para.lastIndexOf("."));
			Class<?> typeClass = fieldMethod.get(para).getParameterTypes()[0];
			if (request.getParameter(para) != null && !request.getParameter(para).isEmpty()){
				if (typeClass == int.class || typeClass == Integer.class){
					fieldMethod.get(para).invoke(objMap.get(objPara), Integer.parseInt(request.getParameter(para)));
				} else {
					fieldMethod.get(para).invoke(objMap.get(objPara), request.getParameter(para));
				}
			} else {
				if (typeClass == String.class) {
					fieldMethod.get(para).invoke(objMap.get(objPara), "");
				} else if (typeClass == Integer.class){
					fieldMethod.get(para).invoke(objMap.get(objPara), 0);
				}
			}
		}
		
		Iterator<String> objIt = objMap.keySet().iterator();
		while(objIt.hasNext()){
			String para = objIt.next();
			if(para.indexOf(".") > 0){
				String objPara = para.substring(0, para.lastIndexOf("."));
				String objP = para.substring(para.lastIndexOf(".") + 1);
				String objMethod = para.substring(0, para.lastIndexOf(".") + 1) + objP.substring(0, 1).toLowerCase() + objP.substring(1);
				fieldMethod.get(objMethod).invoke(objMap.get(objPara), objMap.get(para));
			}
		}
		return objMap.get(obj.getClass().getSimpleName());
	}

	private void clazzPara(Map<String, Method> fieldMethod, Map<String, Object> objMap, Object obj, String key) throws Exception {
		Class<?> clazz = obj.getClass();
		if("".equals(key)){
			key = clazz.getSimpleName();
		} else {
			key = key + "." + clazz.getSimpleName();
		}
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getMethods();
		for(Field field : fields){
			String fieldName = field.getName();
			String mapKey = key + "." + field.getName();
			if(!"List".equals(field.getType().getSimpleName()) && !"Map".equals(field.getType().getSimpleName())) {
				String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				for (Method m : methods) {
					if (("set" + methodName).equals(m.getName())) {
						if(!fieldMethod.containsKey(mapKey)){
							fieldMethod.put(mapKey, m);
						}
						if(!objMap.containsKey(key)){
							objMap.put(key, obj);
						}
						break;
					}
				}
			}
			
			if(!field.getType().isPrimitive() && !"String".equals(field.getType().getSimpleName()) 
				&& !"List".equals(field.getType().getSimpleName()) && !"Map".equals(field.getType().getSimpleName())
				&& !"Integer".equals(field.getType().getSimpleName()) && !"Long".equals(field.getType().getSimpleName())
				&& !"Fload".equals(field.getType().getSimpleName()) && !"Date".equals(field.getType().getSimpleName())){
				Object paraObj = field.getType().newInstance();
				clazzPara(fieldMethod, objMap, paraObj, key);
			}
		}
	}
}