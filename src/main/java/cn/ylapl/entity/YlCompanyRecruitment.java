package cn.ylapl.entity;

import javax.persistence.*;

@Table(name = "yl_company_recruitment")
public class YlCompanyRecruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 职位信息json
     */
    private String recruitment;

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
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取职位信息json
     *
     * @return recruitment - 职位信息json
     */
    public String getRecruitment() {
        return recruitment;
    }

    /**
     * 设置职位信息json
     *
     * @param recruitment 职位信息json
     */
    public void setRecruitment(String recruitment) {
        this.recruitment = recruitment;
    }
}