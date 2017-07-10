package org.sky.framework.test.guava.sort;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by roc on 6/28/2017.
 */
public class MapOrdering {

	private static final String _SIGNATURE = "_signature";
	private static final String _TIMESTAMP = "_timestamp";

	private static final Logger LOGGER = LoggerFactory.getLogger(MapOrdering.class);

	public static void main(String[] args) throws UnsupportedEncodingException {

		Map<String, String> parameters = Maps.newHashMap();

		parameters.put("a", "1");
		parameters.put("b", "2");
		parameters.put("name", "王鹏举");
		parameters.put("age", "28");
		parameters.put("sex", "男");

		List<String> keys =  Ordering.natural().sortedCopy(parameters.keySet());

		StringBuilder joinWithAnd = new StringBuilder();
		for (String key : keys) {
			joinWithAnd.append(key+ "=" + parameters.get(key)).append("&");
		}
		if(joinWithAnd.length() > 0){
			joinWithAnd.deleteCharAt(joinWithAnd.length() - 1);
		}
		String encodeParameter = URLEncoder.encode(joinWithAnd.toString(), "UTF-8");

		System.out.println(encodeParameter);


		Map<String, String> wrapped = wrapWithSignature(parameters);

		System.out.println(wrapped);

		Map<String, String> parameters1 = Maps.newHashMap();
		parameters1.put("json", "{'msgTemplateId':'QQ00000002','title':'this is title 哈哈哈哈','content':'this is content 哈哈哈哈','imgLink':'http://file.touker.com/fs/to/p?downCode\\u003dg3ujiuehD1CLd4QZuVgPYIFi8s4qN6eR0PnvAT%2FIg%2BdUS6SrUZuRNz1%2BqEpDi6Cqpn3%2BxqUnmmwR%0A5ct%2Fd5xyRA%3D%3D','detailLink':'http://m.touker.com/stock/affiche/detail/108','userId':'303644','notifyList':[{'jpushPlatform':'all','audience':{'tags':[],'aliases':['HB1'],'tagAnds':[],'extendKV':{}},'notification':{'android':{'extendKV':{}},'extendKV':{}},'options':{'timeToLive':'86400','extendKV':{}},'alert':'this is title','jtitle':'this is content','appKey':'78cddf9f1379524da3f0cb00','msgToLink':'http://m.touker.com/stock/affiche/detail/108','extras':{'map':{'id':'108','pushType':'21','url':'http://m.touker.com/stock/affiche/detail/108'}},'contentType':'text','isPushMsge':true,'extendKV':{}}],'extendKV':{}}");
		parameters1.put("_timestamp", "1498702353094");

		Map<String, String> sign = wrapWithSignature(parameters1);


		boolean result = validateSignature(sign, "1498702353094", sign.get(_SIGNATURE));

		System.out.println(result);
	}



	private static Map<String, String> wrapWithSignature(Map<String, String> paramsMap) {
		if(paramsMap == null || paramsMap.isEmpty()){
			return paramsMap;
		}

		String timestamp = new Date().getTime() + "";
		paramsMap.put(_TIMESTAMP, timestamp);

		List<String> keys = Ordering.natural().sortedCopy(paramsMap.keySet());
		StringBuilder parameterJoinWithAnd = new StringBuilder();
		for (String key : keys) {
			parameterJoinWithAnd.append(key).append("=").append(paramsMap.get(key)).append("&");
		}
		if (parameterJoinWithAnd.length() > 0) {
			parameterJoinWithAnd.deleteCharAt(parameterJoinWithAnd.length() - 1);
		}
		String encodeParameter;
		try {
			encodeParameter = URLEncoder.encode(parameterJoinWithAnd.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		String signature = Hashing.sha1().hashString(encodeParameter, Charsets.UTF_8).toString();

		Map<String, String> wrapParameters = Maps.newHashMap(paramsMap);
		wrapParameters.put(_SIGNATURE, signature);

		return wrapParameters;
	}

	private static boolean validateSignature(Map<String, String> paramsMap, String timestamp, String requestSignature) {

		if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(requestSignature)) {
			return false;
		}

		//使用timestamp实现防重放

		String[] names = paramsMap.keySet().toArray(new String[paramsMap.size()]);

		//1. 对key 进行自然排序
		Arrays.sort(names);

		//2. 拼成a=b&c=d
		StringBuilder parameterJoinWithAnd = new StringBuilder();
		for (String key : names) {
			if (_SIGNATURE.equals(key)) {
				continue;
			}
			String value = paramsMap.get(key);
			parameterJoinWithAnd.append(key).append("=").append(value).append("&");
		}
		if (parameterJoinWithAnd.length() > 0) {
			parameterJoinWithAnd.deleteCharAt(parameterJoinWithAnd.length() - 1);
		}

		//3. url encode
		String encodeParameter;
		try {
			encodeParameter = URLEncoder.encode(parameterJoinWithAnd.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		//4. signature
		String signature = Hashing.sha1().hashString(encodeParameter, Charsets.UTF_8).toString();

		LOGGER.debug("request signature={}, server signature={}", requestSignature, signature);

		return signature.equalsIgnoreCase(requestSignature);
	}

}
