package org.sky.framework.test.array;

import java.util.ArrayList;
import java.util.List;

public class Snippet {
	public static void main(String[] args) {
		     List<Integer> list = new ArrayList();
		     list.add(1);
		     list.add(1);
		     list.add(2);
		     list.add(3);
		     list.add(4);
		     list.add(5);
		     list.add(6);
		     list.add(5);
		     list.add(6);
			System.out.println(list);
			
			List<Follow> follows = new ArrayList();
			
			Follow portalFollow0 = new Follow();
			portalFollow0.setFollowId(0);
			portalFollow0.setBondCode("000001");
			portalFollow0.setFollowType(4);
			portalFollow0.setBondType(21);
			
			
			Follow portalFollow1 = new Follow();
			portalFollow1.setFollowId(1);
			portalFollow1.setBondCode("000001");
			portalFollow1.setFollowType(4);
			portalFollow1.setBondType(21);
			
			Follow portalFollow2 = new Follow();
			portalFollow2.setFollowId(2);
			portalFollow2.setBondCode("000002");
			portalFollow2.setFollowType(3);
			portalFollow2.setBondType(22);
			
			
			Follow portalFollow3 = new Follow();
			portalFollow3.setFollowId(3);
			portalFollow3.setBondCode("000002");
			portalFollow3.setFollowType(3);
			portalFollow3.setBondType(22);
			
			Follow portalFollow4 = new Follow();
			portalFollow4.setFollowId(4);
			portalFollow4.setBondCode("000004");
			portalFollow4.setFollowType(5);
			portalFollow4.setBondType(20);
			
			
			Follow portalFollow5 = new Follow();
			portalFollow5.setFollowId(5);
			portalFollow5.setBondCode("000001");
			portalFollow5.setFollowType(4);
			portalFollow5.setBondType(21);
			
			follows.add(portalFollow0);
			follows.add(portalFollow1);
			follows.add(portalFollow2);
			follows.add(portalFollow3);
			follows.add(portalFollow4);
			follows.add(portalFollow5);
			removeDuplicate2(follows);
			for (Follow portalFollow : follows) {
				System.out.println(portalFollow);
			}
		}
		
		
		/**
		 * 
		* 倒着删除
		* @param list    
		* void  
		* @author <a href="193832433@qq.com">sky</a>   
		* @date 2016年3月9日 下午1:45:49
		 */
		public static void removeDuplicate(List<Follow> list) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = list.size() - 1; j > i; j--) {
					if (list.get(j).getBondCode().equals(list.get(i).getBondCode()) &&
							list.get(j).getFollowType() == list.get(i).getFollowType()) {
						list.remove(j);
					}
				}
			}
		}
		
		/**
		 * 
		* 正序删除
		* @param list    
		* void  
		* @author <a href="193832433@qq.com">sky</a>   
		* @date 2016年3月9日 下午1:45:59
		 */
		public static void removeDuplicate2(List<Follow> list) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i+1; j < list.size(); j++) {
					if (list.get(j).getBondCode().equals(list.get(i).getBondCode()) &&
							list.get(j).getFollowType() == list.get(i).getFollowType()) {
						list.remove(j);
					}
				}
			}
		}
		
		public static void removeDuplicate1(List list) {
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = list.size() - 1; j > i; j--) {
					if (list.get(j).equals(list.get(i))) {
						list.remove(j);
					}
				}
			}
			System.out.println(list);
		}
}


class Follow {

	private int FollowId;
	private String bondCode;
	private int followType;
	private int BondType;

	/**
	 * @return the followId
	 */
	public int getFollowId() {
		return FollowId;
	}

	/**
	 * @param followId
	 *            the followId to set
	 */
	public void setFollowId(int followId) {
		FollowId = followId;
	}

	/**
	 * @return the bondCode
	 */
	public String getBondCode() {
		return bondCode;
	}

	/**
	 * @param bondCode
	 *            the bondCode to set
	 */
	public void setBondCode(String bondCode) {
		this.bondCode = bondCode;
	}

	/**
	 * @return the followType
	 */
	public int getFollowType() {
		return followType;
	}

	/**
	 * @param followType
	 *            the followType to set
	 */
	public void setFollowType(int followType) {
		this.followType = followType;
	}

	/**
	 * @return the bondType
	 */
	public int getBondType() {
		return BondType;
	}

	/**
	 * @param bondType
	 *            the bondType to set
	 */
	public void setBondType(int bondType) {
		BondType = bondType;
	}

}


