package org.sky.framework.test.reflect;



/**
 * @author <a href="193832433@qq.com">sky</a>
 * @description
 * <ul>
 *   <li>功能号 304103 客户当日委托查询</li>
 *   <li>功能号 304109 客户时时成交查询</li>
 *   <li>功能号 304111 客户时时成交汇总查询</li>
 *   <li>功能号 304116 查询指定委托号的委托详细信息</li>
 *   <li>功能号 304230 查询客户配售权益</li>
 *   <li>功能号 620011[快] 可买卖数量计算</li>
 *   <li>功能号 620001[快] 证券买卖委托</li>
 * </ul>
 * @date 2015年4月23日 下午3:27:56
 */
public class CustomerEntrustInfo extends BaseEntity {

    private static final long serialVersionUID = -4506317098290527778L;

    /**
     * 客户号
     */
    private String fid_khh;

    /**
     * 密码
     */
    private String fid_jymm;

    /**
     * 0查询所有委托，1表示只查询可以撤单的委托
     */
    private Integer fid_flag;

    /**
     * 0顺序1逆序
     */
    private Integer fid_sorttype;

    /**
     * 查询记录数
     */
    private Integer fid_rowcount;

    /**
     * 定位串
     */
    private String fid_browindex;

	/**
	 * 报盘股东号
	 */
	private String fid_bpgdh;
	/**
	 * 返回码
	 */
	private Integer fid_code;
	/**
	 * 返回说明
	 */
	private String fid_message;
	/**
	 * 成交时间
	 */
	private String fid_cjsj;
	/**
	 * 委托方式
	 */
	private Integer fid_wtfs;
	/**
	 * 证券类别
	 */
	private String fid_zqlb;
	/**
	 * 冻结资金
	 */
	private Double fid_djzj;
	/**
	 * 资金账号
	 */
	private String fid_zjzh;
	/**
	 * 资金冻结流水号
	 */
	private Integer fid_zjdjlsh;
	/**
	 * 证券冻结流水号
	 */
	private Integer fid_zqdjlsh;
	/**
	 * 委托申报日期
	 */
	private Integer fid_sbrq;
	/**
	 * 申报记录号
	 */
	private Integer fid_sbjlh;
	/**
	 * 委托日期
	 */
	private Integer fid_wtrq;
	/**
	 * IP地址
	 */
	private String fid_addr_ip;
	/**
	 * MAC地址
	 */
	private String fid_addr_mac;

    /**
     * 交易所
     */
    private String fid_jys;

    /**
     * 股东号
     */
    private String fid_gdh;

    /**
     * 委托号
     */
    private Integer fid_wth;

    /**
     * 交易类别/委托类别，见数据字典
     */
    private Integer fid_wtlb;

    /**
     * 撤销标志，W表示撤单记录，其它为委托记录
     */
    private String fid_cxbz;

    /**
     * 证券代码
     */
    private String fid_zqdm;

    /**
     * 证券名称
     */
    private String fid_zqmc;

    /**
     * 订单类型，0表示限价订单，其它见数据字典，FLDM=DDLX
     */
    private Integer fid_ddlx;

    /**
     * 委托数量
     */
    private Integer fid_wtsl;

    /**
     * 委托价格
     */
    private Double fid_wtjg;


    /**
     * 委托时间
     */
    private String fid_wtsj;

    /**
     * 申报时间
     */
    private String fid_sbsj;

    /**
     * "申报结果：0未申报，2已申报，5部分成功，6全部成交，8全部撤单，其它见数据字典FLDM=WTSBJG"
     */
    private Integer fid_sbjg;

    /**
     * 结果说明
     */
    private String fid_jgsm;

    /**
     * 撤单数量
     */
    private Integer fid_cdsl;

    /**
     * 成交数量
     */
    private Integer fid_cjsl;

    /**
     * 成交金额
     */
    private Double fid_cjje;

    /**
     * 成交均价
     */
    private Double fid_cjjg;

    /**
     * 币种
     */
    private String fid_bz;

    /**
     * 清算资金
     */
    private Double fid_qszj;

    /**
     * 委托站点信息
     */
    private String fid_node;

    /**
     * 申报委托号，与撤销标志要同时送，与委托号必须要送一个
     */
    private String fid_sbwth;

    /**
     * 清算金额
     */
    private Double fid_qsje;

    /**
     * 证券类别范围
     */
    private String fid_en_zqlb;

    /**
     * 营业部范围
     */
    private String fid_en_yyb;

    /**
     * 营业部
     */
    private String fid_yyb;

    /**
     * 证券数量（权益数量）
     */
    private String fid_zqsl;

    /**
     * 到帐日期（清算日期）
     */
    private String fid_dzrq;

    /**
     * 对方席位号
     */
    private String fid_dfxw;

    
    
    
    /**
	 * 加密方式，0
	 */
	private Integer fid_jmlx;
    
    
    
    public String getFid_khh() {
        return fid_khh;
    }

    public void setFid_khh(String fid_khh) {
        this.fid_khh = fid_khh;
    }

    public String getFid_jymm() {
        return fid_jymm;
    }

    public void setFid_jymm(String fid_jymm) {
        this.fid_jymm = fid_jymm;
    }

    public Integer getFid_flag() {
        return fid_flag;
    }

    public void setFid_flag(Integer fid_flag) {
        this.fid_flag = fid_flag;
    }

    public Integer getFid_sorttype() {
        return fid_sorttype;
    }

    public void setFid_sorttype(Integer fid_sorttype) {
        this.fid_sorttype = fid_sorttype;
    }

    public Integer getFid_rowcount() {
        return fid_rowcount;
    }

    public void setFid_rowcount(Integer fid_rowcount) {
        this.fid_rowcount = fid_rowcount;
    }

    public String getFid_browindex() {
        return fid_browindex;
    }

    public void setFid_browindex(String fid_browindex) {
        this.fid_browindex = fid_browindex;
    }

    public String getFid_jys() {
        return fid_jys;
    }

    public void setFid_jys(String fid_jys) {
        this.fid_jys = fid_jys;
    }

    public String getFid_gdh() {
        return fid_gdh;
    }

    public void setFid_gdh(String fid_gdh) {
        this.fid_gdh = fid_gdh;
    }

    public Integer getFid_wth() {
        return fid_wth;
    }

    public void setFid_wth(Integer fid_wth) {
        this.fid_wth = fid_wth;
    }

    public Integer getFid_wtlb() {
        return fid_wtlb;
    }

    public void setFid_wtlb(Integer fid_wtlb) {
        this.fid_wtlb = fid_wtlb;
    }

    public String getFid_cxbz() {
        return fid_cxbz;
    }

    public void setFid_cxbz(String fid_cxbz) {
        this.fid_cxbz = fid_cxbz;
    }

    public String getFid_zqdm() {
        return fid_zqdm;
    }

    public void setFid_zqdm(String fid_zqdm) {
        this.fid_zqdm = fid_zqdm;
    }

    public String getFid_zqmc() {
        return fid_zqmc;
    }

    public void setFid_zqmc(String fid_zqmc) {
        this.fid_zqmc = fid_zqmc;
    }

    public Integer getFid_ddlx() {
        return fid_ddlx;
    }

    public void setFid_ddlx(Integer fid_ddlx) {
        this.fid_ddlx = fid_ddlx;
    }

    public Integer getFid_wtsl() {
        return fid_wtsl;
    }

    public void setFid_wtsl(Integer fid_wtsl) {
        this.fid_wtsl = fid_wtsl;
    }

    public Double getFid_wtjg() {
        return fid_wtjg;
    }

    public void setFid_wtjg(Double fid_wtjg) {
        this.fid_wtjg = fid_wtjg;
    }


    public String getFid_wtsj() {
        return fid_wtsj;
    }

    public void setFid_wtsj(String fid_wtsj) {
        this.fid_wtsj = fid_wtsj;
    }

    public String getFid_sbsj() {
        return fid_sbsj;
    }

	/**
	 * @return the 报盘股东号
	 */
	public String getFid_bpgdh() {
		return fid_bpgdh;
	}

	/**
	 * @param 报盘股东号
	 *            the fid_bpgdh to set
	 */
	public void setFid_bpgdh(String fid_bpgdh) {
		this.fid_bpgdh = fid_bpgdh;
	}

	/**
	 * @return the 返回码
	 */
	public Integer getFid_code() {
		return fid_code;
	}

	/**
	 * @param 返回码
	 *            the fid_code to set
	 */
	public void setFid_code(Integer fid_code) {
		this.fid_code = fid_code;
	}

	/**
	 * @return the 返回说明
	 */
	public String getFid_message() {
		return fid_message;
	}

	/**
	 * @param 返回说明
	 *            the fid_message to set
	 */
	public void setFid_message(String fid_message) {
		this.fid_message = fid_message;
	}

	/**
	 * @return the 成交时间
	 */
	public String getFid_cjsj() {
		return fid_cjsj;
	}

	/**
	 * @param 成交时间
	 *            the fid_cjsj to set
	 */
	public void setFid_cjsj(String fid_cjsj) {
		this.fid_cjsj = fid_cjsj;
	}

	/**
	 * @return the 委托方式
	 */
	public Integer getFid_wtfs() {
		return fid_wtfs;
	}

	/**
	 * @param 委托方式
	 *            the fid_wtfs to set
	 */
	public void setFid_wtfs(Integer fid_wtfs) {
		this.fid_wtfs = fid_wtfs;
	}

	/**
	 * @return the 证券类别
	 */
	public String getFid_zqlb() {
		return fid_zqlb;
	}

	/**
	 * @param 证券类别
	 *            the fid_zqlb to set
	 */
	public void setFid_zqlb(String fid_zqlb) {
		this.fid_zqlb = fid_zqlb;
	}

	/**
	 * @return the 冻结资金
	 */
	public Double getFid_djzj() {
		return fid_djzj;
	}

	/**
	 * @param 冻结资金
	 *            the fid_djzj to set
	 */
	public void setFid_djzj(Double fid_djzj) {
		this.fid_djzj = fid_djzj;
	}

	/**
	 * @return the 资金账号
	 */
	public String getFid_zjzh() {
		return fid_zjzh;
	}

	/**
	 * @param 资金账号
	 *            the fid_zjzh to set
	 */
	public void setFid_zjzh(String fid_zjzh) {
		this.fid_zjzh = fid_zjzh;
	}

	/**
	 * @return the 资金冻结流水号
	 */
	public Integer getFid_zjdjlsh() {
		return fid_zjdjlsh;
	}

	/**
	 * @param 资金冻结流水号
	 *            the fid_zjdjlsh to set
	 */
	public void setFid_zjdjlsh(Integer fid_zjdjlsh) {
		this.fid_zjdjlsh = fid_zjdjlsh;
	}

	/**
	 * @return the 证券冻结流水号
	 */
	public Integer getFid_zqdjlsh() {
		return fid_zqdjlsh;
	}

	/**
	 * @param 证券冻结流水号
	 *            the fid_zqdjlsh to set
	 */
	public void setFid_zqdjlsh(Integer fid_zqdjlsh) {
		this.fid_zqdjlsh = fid_zqdjlsh;
	}

	/**
	 * @return the 委托申报日期
	 */
	public Integer getFid_sbrq() {
		return fid_sbrq;
	}

	/**
	 * @param 委托申报日期
	 *            the fid_sbrq to set
	 */
	public void setFid_sbrq(Integer fid_sbrq) {
		this.fid_sbrq = fid_sbrq;
	}

	/**
	 * @return the 申报记录号
	 */
	public Integer getFid_sbjlh() {
		return fid_sbjlh;
	}

	/**
	 * @param 申报记录号
	 *            the fid_sbjlh to set
	 */
	public void setFid_sbjlh(Integer fid_sbjlh) {
		this.fid_sbjlh = fid_sbjlh;
	}

	/**
	 * @return the 委托日期
	 */
	public Integer getFid_wtrq() {
		return fid_wtrq;
	}

	/**
	 * @param 委托日期
	 *            the fid_wtrq to set
	 */
	public void setFid_wtrq(Integer fid_wtrq) {
		this.fid_wtrq = fid_wtrq;
	}

	/**
	 * @return the IP地址
	 */
	public String getFid_addr_ip() {
		return fid_addr_ip;
	}

	/**
	 * @param IP地址
	 *            the fid_addr_ip to set
	 */
	public void setFid_addr_ip(String fid_addr_ip) {
		this.fid_addr_ip = fid_addr_ip;
	}

	/**
	 * @return the MAC地址
	 */
	public String getFid_addr_mac() {
		return fid_addr_mac;
	}

	/**
	 * @param MAC地址
	 *            the fid_addr_mac to set
	 */
	public void setFid_addr_mac(String fid_addr_mac) {
		this.fid_addr_mac = fid_addr_mac;
	}

    public void setFid_sbsj(String fid_sbsj) {
        this.fid_sbsj = fid_sbsj;
    }

    public Integer getFid_sbjg() {
        return fid_sbjg;
    }

    public void setFid_sbjg(Integer fid_sbjg) {
        this.fid_sbjg = fid_sbjg;
    }

    public String getFid_jgsm() {
        return fid_jgsm;
    }

    public void setFid_jgsm(String fid_jgsm) {
        this.fid_jgsm = fid_jgsm;
    }

    public Integer getFid_cdsl() {
        return fid_cdsl;
    }

    public void setFid_cdsl(Integer fid_cdsl) {
        this.fid_cdsl = fid_cdsl;
    }

    public Integer getFid_cjsl() {
        return fid_cjsl;
    }

    public void setFid_cjsl(Integer fid_cjsl) {
        this.fid_cjsl = fid_cjsl;
    }

    public Double getFid_cjje() {
        return fid_cjje;
    }

    public void setFid_cjje(Double fid_cjje) {
        this.fid_cjje = fid_cjje;
    }

    public Double getFid_cjjg() {
        return fid_cjjg;
    }

    public void setFid_cjjg(Double fid_cjjg) {
        this.fid_cjjg = fid_cjjg;
    }

    public String getFid_bz() {
        return fid_bz;
    }

    public void setFid_bz(String fid_bz) {
        this.fid_bz = fid_bz;
    }

    public Double getFid_qszj() {
        return fid_qszj;
    }

    public void setFid_qszj(Double fid_qszj) {
        this.fid_qszj = fid_qszj;
    }

    public String getFid_node() {
        return fid_node;
    }

    public void setFid_node(String fid_node) {
        this.fid_node = fid_node;
    }

    public String getFid_sbwth() {
        return fid_sbwth;
    }

    public void setFid_sbwth(String fid_sbwth) {
        this.fid_sbwth = fid_sbwth;
    }

    public Double getFid_qsje() {
        return fid_qsje;
    }

    public void setFid_qsje(Double fid_qsje) {
        this.fid_qsje = fid_qsje;
    }

    public String getFid_en_zqlb() {
        return fid_en_zqlb;
    }

    public void setFid_en_zqlb(String fid_en_zqlb) {
        this.fid_en_zqlb = fid_en_zqlb;
    }

    public String getFid_en_yyb() {
        return fid_en_yyb;
    }

    public void setFid_en_yyb(String fid_en_yyb) {
        this.fid_en_yyb = fid_en_yyb;
    }

    public String getFid_yyb() {
        return fid_yyb;
    }

    public void setFid_yyb(String fid_yyb) {
        this.fid_yyb = fid_yyb;
    }

    public String getFid_zqsl() {
        return fid_zqsl;
    }

    public void setFid_zqsl(String fid_zqsl) {
        this.fid_zqsl = fid_zqsl;
    }

    public String getFid_dzrq() {
        return fid_dzrq;
    }

    public void setFid_dzrq(String fid_dzrq) {
        this.fid_dzrq = fid_dzrq;
    }

    public String getFid_dfxw() {
        return fid_dfxw;
    }

    public void setFid_dfxw(String fid_dfxw) {
        this.fid_dfxw = fid_dfxw;
    }
    
    
	/**
	 * @return the 加密方式，0
	 */
	public Integer getFid_jmlx() {
		return fid_jmlx;
	}

	/**
	 * @param 加密方式，0 the fid_jmlx to set
	 */
	public void setFid_jmlx(Integer fid_jmlx) {
		this.fid_jmlx = fid_jmlx;
	}
}
