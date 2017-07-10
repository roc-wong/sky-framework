package org.sky.framework.test.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MatchMap {

	/**
	 * 
	 * [{id=49.0, code=000420, name=吉林化纤, pinYinAbbrName=JLHX, exchange=SZ,
	 * tradeDate=2015-06-03 10:09:56, currentPrice=10.07, changes=0.92,
	 * changesPercent=10.05, wave=6.78, totalNum=2.2639219E7,
	 * totalAmt=2.2750680065E8, previousPrice=9.15, openPrice=10.07,
	 * upPrice=10.07, lowPrice=9.45, earningsMultiple=182.63, buyPrice=10.07,
	 * buyOnePrice=10.07, saleOnePrice=0.0, buyOneNum=9249165.0, saleOneNum=0.0,
	 * buyTwoPrice=10.06, saleTwoPrice=0.0, buyTwoNum=61640.0, saleTwoNum=0.0,
	 * buyThreePrice=10.05, saleThreePrice=0.0, buyThreeNum=28500.0,
	 * saleThreeNum=0.0, buyFourPrice=10.04, saleFourPrice=0.0,
	 * buyFourNum=8200.0, saleFourNum=0.0, buyFivePrice=10.03,
	 * saleFivePrice=0.0, buyFiveNum=16000.0, saleFiveNum=0.0, type=[A股],
	 * haltState=上市, followId=134719, followsort=1058, followType=4,
	 * bondType=20}, {id=618.0, code=300149, name=量子高科, pinYinAbbrName=LZGK,
	 * exchange=SZ, tradeDate=2015-06-03 10:09:56, currentPrice=27.46,
	 * changes=2.5, changesPercent=10.02, wave=1.84, totalNum=7927107.0,
	 * totalAmt=2.1743392944E8, previousPrice=24.96, openPrice=27.45,
	 * upPrice=27.46, lowPrice=27.0, earningsMultiple=268.68, buyPrice=27.46,
	 * buyOnePrice=27.46, saleOnePrice=0.0, buyOneNum=7355253.0, saleOneNum=0.0,
	 * buyTwoPrice=27.45, saleTwoPrice=0.0, buyTwoNum=18000.0, saleTwoNum=0.0,
	 * buyThreePrice=27.44, saleThreePrice=0.0, buyThreeNum=100.0,
	 * saleThreeNum=0.0, buyFourPrice=27.43, saleFourPrice=0.0,
	 * buyFourNum=1400.0, saleFourNum=0.0, buyFivePrice=27.42,
	 * saleFivePrice=0.0, buyFiveNum=22300.0, saleFiveNum=0.0, type=[A股],
	 * haltState=上市, followId=133472, followsort=390, followType=4,
	 * bondType=20}]
	 * 
	 * 
	 * TODO
	 * 
	 * @param args
	 *            void
	 * @author <a href="193832433@qq.com">sky</a>
	 * @date 2015年6月3日 上午10:40:14
	 */

	public static void main(String[] args) {
		LinkedList<Map> listReturn = new LinkedList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "000420");
		map.put("name", "吉林化纤");
		map.put("bondType", "20");
		listReturn.add(map);
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("code", "300149");
		map1.put("name", "量子高科");
		map1.put("bondType", "20");
		listReturn.add(map1);

		Map<String, String[]> parameters = null;
		StringBuilder parameter = new StringBuilder();
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			parameter.append(entry.getKey()).append("=").append(entry.getValue());
		}
		System.out.println(parameter);
	}
}
