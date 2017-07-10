package org.sky.framework.test.reflect;


/**
 * @author <a href="193832433@qq.com">sky</a>
 * @description 抽取实体公用属性
 * <ul>
 * <li>功能号 610301 交易密码校验</li>
 * <li>功能号 204500 检测客户证券风险匹配 用于客户买入委托前</li>
 * <li>功能号 204503 可委托数量计算</li>
 * <li>功能号 204501 股票买卖委托</li>
 * <li>功能号 204502 股票委托撤单</li>
 * <li>功能号 204537 网络投票委托</li>
 * </ul>
 * @date 2015年4月23日 下午3:27:56
 */
public class BaseEntity implements java.io.Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 849667488851132049L;

    /**
     * 返回码
     */
    protected Integer fid_code;
    /**
     * 返回说明
     */
    protected String fid_message;

    // 排序是Desc还是Asc
    private String dirField;

    // 排序字段
    private String sortField;

    // 开始查询页数
    private Integer pageNo;

    // 每页显示的行数
    private Integer pageSize;

    // 是否包含自选状态 默认为不包含
    private String isHasFollow;

    // 用户头像
    private String avatarurl;

    // 用户个性签名
    private String signature;

    // 用户名称
    private String username;

    /**
     * 申请号
     */
    protected String fid_sqh;

    /**
     * 可委托数量
     */
    protected Integer fid_wtsl;

    /**
     * 委托号，一般是数字类型
     */
    protected Integer fid_wth;

    /**
     * 委托批次号
     */
    public Integer fid_wtpch;

    public BaseEntity() {
    }

    public BaseEntity(Integer remoteErrorCode, String resultDesc) {
        this.fid_code = remoteErrorCode;
        this.fid_message = resultDesc;
    }

    public Integer getFid_code() {
        return fid_code;
    }

    public void setFid_code(Integer fid_code) {
        this.fid_code = fid_code;
    }

    public String getFid_message() {
        return fid_message;
    }

    public void setFid_message(String fid_message) {
        this.fid_message = fid_message;
    }

    public String getDirField() {
        return dirField;
    }

    public void setDirField(String dirField) {
        this.dirField = dirField;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getIsHasFollow() {
        return isHasFollow;
    }

    public void setIsHasFollow(String isHasFollow) {
        this.isHasFollow = isHasFollow;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFid_sqh() {
        return fid_sqh;
    }

    public void setFid_sqh(String fid_sqh) {
        this.fid_sqh = fid_sqh;
    }

    public Integer getFid_wtsl() {
        return fid_wtsl;
    }

    public void setFid_wtsl(Integer fid_wtsl) {
        this.fid_wtsl = fid_wtsl;
    }

    public Integer getFid_wth() {
        return fid_wth;
    }

    public void setFid_wth(Integer fid_wth) {
        this.fid_wth = fid_wth;
    }

    public Integer getFid_wtpch() {
        return fid_wtpch;
    }

    public void setFid_wtpch(Integer fid_wtpch) {
        this.fid_wtpch = fid_wtpch;
    }


}
