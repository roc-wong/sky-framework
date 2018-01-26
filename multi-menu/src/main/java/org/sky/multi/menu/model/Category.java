package org.sky.multi.menu.model;

/**
 * @author roc
 * @date 2018/01/15
 */
public class Category {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 级别，消息中心暂时支持三级，一级：1，二级：2，三级：3
     */
    private Integer grade;

    /**
     * 栏目序号，根据grade和parentId计算
     */
    private Integer categoryNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }
}
