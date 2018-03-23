package com.study.blog.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
* @ClassName: ReflectTools
* @Description: TODO(服务工具类)
* @author hongboz
* @date 2017-8-25 下午7:22:59
*
 */
public class ReflectTools {



	@SuppressWarnings("rawtypes")
	public static Object list2Json(List list) {
		Object str = JSON.toJSON(list);
		return str;
	}

	public static String getStackMsg(Exception e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}

	public static Object getObj(String outCode, String outMsg, String type) {
		Object returnObj = "";
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("rtnCode", outCode);
		outMap.put("rtnMsg", outMsg);
		outList.add(outMap);

		if ("json".equalsIgnoreCase(type)) {
			returnObj = list2Json(outList);
		} else {
			returnObj = outMsg;
		}
		return returnObj;
	}

	/**
	 * 获取请求参数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map getParameterMap(HttpServletRequest request){
		String name = "";
		String value = "";
		Entry entry;
		Map parameterMap = getParameterMapUrl(request);
		Map parameterMapBody = getParameterMapBody(request);
		Iterator entries = parameterMapBody.entrySet().iterator();
		while(entries.hasNext()){
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if("startNum".equals(name) || "pageSize".equals(name)){
				parameterMap.put(name, Integer.valueOf(valueObj.toString()));
			}else{
				parameterMap.put(name, valueObj);
			}
		}
		return parameterMap;
	}

	/**
	 * 获取请求体中
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map getParameterMapBody(HttpServletRequest request){
		JSONObject parameterJson = getInputParamJsonBody(request);
		return  parameterJson == null ? new HashMap():(Map)parameterJson;
	}

	/**
	 * 获取请求体中参数
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getInputParamJsonBody(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			char[] buff = new char[1024];
			int len;
			while (reader != null && (len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return JSONObject.parseObject(sb.toString());
	}


	/**
	 * 获取请求路径中参数
	 * @param request
	 * @return
	 */
	public static Map getParameterMapUrl(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			if("startNum".equals(name) || "pageSize".equals(name)){
				returnMap.put(name, Integer.valueOf(value.toString()));
			}else{
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}

	/**
	 *
	 * @Title: instanceObject
	 * @Description: TODO( rpc服务实例)
	 * @param @param classPath
	 * @param @param extend1
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Object    返回类型
	 * @throws
	 */
	public static Object instanceObject(String classPath,String extend1) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		Class<?> clazz = Class.forName(classPath);
		Method method = clazz.getMethod(extend1);
		Object object = method.invoke(clazz.newInstance());
		return object;
	}
}
