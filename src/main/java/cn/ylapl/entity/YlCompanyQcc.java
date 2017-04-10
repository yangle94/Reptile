package cn.ylapl.entity;

import javax.persistence.*;

@Table(name = "yl_company_qcc")
public class YlCompanyQcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司信息
     */
    @Column(name = "company_info")
    private String companyInfo;

    /**
     * 工商信息
     */
    @Column(name = "company_business_information")
    private String companyBusinessInformation;

    /**
     * 股东信息
     */
    @Column(name = "company_shareholder")
    private String companyShareholder;

    /**
     * 主要人员
     */
    @Column(name = "company_key_personnel")
    private String companyKeyPersonnel;

    /**
     * 分支机构
     */
    private String branch;

    /**
     * 变更记录
     */
    @Column(name = "company_change_record")
    private String companyChangeRecord;

    /**
     * 裁判文书
     */
    private String documents;

    /**
     * 法院公告
     */
    @Column(name = "court_notice")
    private String courtNotice;

    /**
     * 税务信息
     */
    private String tax;

    /**
     * 产品信息
     */
    private String product;

    /**
     * 融资信息
     */
    private String financing;

    /**
     * 财务总览
     */
    private String financial;

    /**
     * 对外投资
     */
    private String outbound;

    /**
     * 企业年报
     */
    private String annual;

    /**
     * 商标信息
     */
    private String trademark;

    /**
     * 著作权信息
     */
    private String copyright;

    /**
     * 软件著作权信息
     */
    @Column(name = "soft_copyright")
    private String softCopyright;

    /**
     * 网站信息
     */
    private String website;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取公司信息
     *
     * @return company_info - 公司信息
     */
    public String getCompanyInfo() {
        return companyInfo;
    }

    /**
     * 设置公司信息
     *
     * @param companyInfo 公司信息
     */
    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    /**
     * 获取工商信息
     *
     * @return company_business_information - 工商信息
     */
    public String getCompanyBusinessInformation() {
        return companyBusinessInformation;
    }

    /**
     * 设置工商信息
     *
     * @param companyBusinessInformation 工商信息
     */
    public void setCompanyBusinessInformation(String companyBusinessInformation) {
        this.companyBusinessInformation = companyBusinessInformation;
    }

    /**
     * 获取股东信息
     *
     * @return company_shareholder - 股东信息
     */
    public String getCompanyShareholder() {
        return companyShareholder;
    }

    /**
     * 设置股东信息
     *
     * @param companyShareholder 股东信息
     */
    public void setCompanyShareholder(String companyShareholder) {
        this.companyShareholder = companyShareholder;
    }

    /**
     * 获取主要人员
     *
     * @return company_key_personnel - 主要人员
     */
    public String getCompanyKeyPersonnel() {
        return companyKeyPersonnel;
    }

    /**
     * 设置主要人员
     *
     * @param companyKeyPersonnel 主要人员
     */
    public void setCompanyKeyPersonnel(String companyKeyPersonnel) {
        this.companyKeyPersonnel = companyKeyPersonnel;
    }

    /**
     * 获取分支机构
     *
     * @return branch - 分支机构
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置分支机构
     *
     * @param branch 分支机构
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取变更记录
     *
     * @return company_change_record - 变更记录
     */
    public String getCompanyChangeRecord() {
        return companyChangeRecord;
    }

    /**
     * 设置变更记录
     *
     * @param companyChangeRecord 变更记录
     */
    public void setCompanyChangeRecord(String companyChangeRecord) {
        this.companyChangeRecord = companyChangeRecord;
    }

    /**
     * 获取裁判文书
     *
     * @return documents - 裁判文书
     */
    public String getDocuments() {
        return documents;
    }

    /**
     * 设置裁判文书
     *
     * @param documents 裁判文书
     */
    public void setDocuments(String documents) {
        this.documents = documents;
    }

    /**
     * 获取法院公告
     *
     * @return court_notice - 法院公告
     */
    public String getCourtNotice() {
        return courtNotice;
    }

    /**
     * 设置法院公告
     *
     * @param courtNotice 法院公告
     */
    public void setCourtNotice(String courtNotice) {
        this.courtNotice = courtNotice;
    }

    /**
     * 获取税务信息
     *
     * @return tax - 税务信息
     */
    public String getTax() {
        return tax;
    }

    /**
     * 设置税务信息
     *
     * @param tax 税务信息
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * 获取产品信息
     *
     * @return product - 产品信息
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置产品信息
     *
     * @param product 产品信息
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 获取融资信息
     *
     * @return financing - 融资信息
     */
    public String getFinancing() {
        return financing;
    }

    /**
     * 设置融资信息
     *
     * @param financing 融资信息
     */
    public void setFinancing(String financing) {
        this.financing = financing;
    }

    /**
     * 获取财务总览
     *
     * @return financial - 财务总览
     */
    public String getFinancial() {
        return financial;
    }

    /**
     * 设置财务总览
     *
     * @param financial 财务总览
     */
    public void setFinancial(String financial) {
        this.financial = financial;
    }

    /**
     * 获取对外投资
     *
     * @return outbound - 对外投资
     */
    public String getOutbound() {
        return outbound;
    }

    /**
     * 设置对外投资
     *
     * @param outbound 对外投资
     */
    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

    /**
     * 获取企业年报
     *
     * @return annual - 企业年报
     */
    public String getAnnual() {
        return annual;
    }

    /**
     * 设置企业年报
     *
     * @param annual 企业年报
     */
    public void setAnnual(String annual) {
        this.annual = annual;
    }

    /**
     * 获取商标信息
     *
     * @return trademark - 商标信息
     */
    public String getTrademark() {
        return trademark;
    }

    /**
     * 设置商标信息
     *
     * @param trademark 商标信息
     */
    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    /**
     * 获取著作权信息
     *
     * @return copyright - 著作权信息
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 设置著作权信息
     *
     * @param copyright 著作权信息
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 获取软件著作权信息
     *
     * @return soft_copyright - 软件著作权信息
     */
    public String getSoftCopyright() {
        return softCopyright;
    }

    /**
     * 设置软件著作权信息
     *
     * @param softCopyright 软件著作权信息
     */
    public void setSoftCopyright(String softCopyright) {
        this.softCopyright = softCopyright;
    }

    /**
     * 获取网站信息
     *
     * @return website - 网站信息
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置网站信息
     *
     * @param website 网站信息
     */
    public void setWebsite(String website) {
        this.website = website;
    }
}