package cn.ylapl.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "yl_company")
public class YlCompany implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 拉钩上公司ID
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 公司全名
     */
    @Column(name = "company_full_name")
    private String companyFullName;

    /**
     * 公司短名字
     */
    @Column(name = "company_short_name")
    private String companyShortName;

    /**
     * 公司图片
     */
    @Column(name = "company_logo")
    private String companyLogo;

    private String city;

    @Column(name = "industry_field")
    private String industryField;

    @Column(name = "company_features")
    private String companyFeatures;

    @Column(name = "finance_stage")
    private String financeStage;

    @Column(name = "interview_remark_num")
    private Integer interviewRemarkNum;

    @Column(name = "position_num")
    private Integer positionNum;

    @Column(name = "process_rate")
    private Integer processRate;

    private Integer approve;

    @Column(name = "country_score")
    private Integer countryScore;

    @Column(name = "city_score")
    private Integer cityScore;

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
     * 获取拉钩上公司ID
     *
     * @return company_id - 拉钩上公司ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置拉钩上公司ID
     *
     * @param companyId 拉钩上公司ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取公司全名
     *
     * @return company_full_name - 公司全名
     */
    public String getCompanyFullName() {
        return companyFullName;
    }

    /**
     * 设置公司全名
     *
     * @param companyFullName 公司全名
     */
    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    /**
     * 获取公司短名字
     *
     * @return company_short_name - 公司短名字
     */
    public String getCompanyShortName() {
        return companyShortName;
    }

    /**
     * 设置公司短名字
     *
     * @param companyShortName 公司短名字
     */
    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    /**
     * 获取公司图片
     *
     * @return company_logo - 公司图片
     */
    public String getCompanyLogo() {
        return companyLogo;
    }

    /**
     * 设置公司图片
     *
     * @param companyLogo 公司图片
     */
    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return industry_field
     */
    public String getIndustryField() {
        return industryField;
    }

    /**
     * @param industryField
     */
    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    /**
     * @return company_features
     */
    public String getCompanyFeatures() {
        return companyFeatures;
    }

    /**
     * @param companyFeatures
     */
    public void setCompanyFeatures(String companyFeatures) {
        this.companyFeatures = companyFeatures;
    }

    /**
     * @return finance_stage
     */
    public String getFinanceStage() {
        return financeStage;
    }

    /**
     * @param financeStage
     */
    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    /**
     * @return interview_remark_num
     */
    public Integer getInterviewRemarkNum() {
        return interviewRemarkNum;
    }

    /**
     * @param interviewRemarkNum
     */
    public void setInterviewRemarkNum(Integer interviewRemarkNum) {
        this.interviewRemarkNum = interviewRemarkNum;
    }

    /**
     * @return position_num
     */
    public Integer getPositionNum() {
        return positionNum;
    }

    /**
     * @param positionNum
     */
    public void setPositionNum(Integer positionNum) {
        this.positionNum = positionNum;
    }

    /**
     * @return process_rate
     */
    public Integer getProcessRate() {
        return processRate;
    }

    /**
     * @param processRate
     */
    public void setProcessRate(Integer processRate) {
        this.processRate = processRate;
    }

    /**
     * @return approve
     */
    public Integer getApprove() {
        return approve;
    }

    /**
     * @param approve
     */
    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    /**
     * @return country_score
     */
    public Integer getCountryScore() {
        return countryScore;
    }

    /**
     * @param countryScore
     */
    public void setCountryScore(Integer countryScore) {
        this.countryScore = countryScore;
    }

    /**
     * @return city_score
     */
    public Integer getCityScore() {
        return cityScore;
    }

    /**
     * @param cityScore
     */
    public void setCityScore(Integer cityScore) {
        this.cityScore = cityScore;
    }
}