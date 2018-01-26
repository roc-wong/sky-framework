package org.sky.multi.menu.dao;

/**
 * @author roc
 * @date 2018/01/15
 */
public class CategoryDAO {

    /**
     * 最多三个级别
     */
    public static final int MAX_GRADE = 3;
    /**
     * 每个级别用两位数字表示
     */
    public static final int LEVEL_LENGTH = 2;

    private int calculateCategoryNo(Integer parentId, Integer grade) {

        int maxCategoryNo = selectMaxCategoryNo(parentId);

        //计算节点的基数,如用两位表示就是100，三位表示就是1000等
        int baseNumber = (int) Math.pow(10, LEVEL_LENGTH);
        
        //要加上的数字
        int numberToAdd = (int) Math.pow(baseNumber, MAX_GRADE - grade);

        //要加入的节点是该父节点下的第一个子节点
        if (maxCategoryNo == 0) {
            //最顶层节点
            if (parentId == 0) {
                return numberToAdd;
            } else {
                //其他层节点
                //查询父节点的序号
                int parentCno = selectParentCategoryNo(parentId);
                return parentCno + numberToAdd;
            }
        } else {
            //加入的节点不是该父节点下的第一个
            return maxCategoryNo + numberToAdd;
        }
    }

    private int selectParentCategoryNo(Integer parentId) {
        //select category_no from category where parent_id = parentId
        return 0;
    }

    private int selectMaxCategoryNo(Integer parentId) {
        //select max(category_no) from category where parent_id = parentId
        return 0;
    }

}
