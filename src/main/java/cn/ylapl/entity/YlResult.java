package cn.ylapl.entity;

import cn.ylapl.util.interfaceUtil.BuilderInterface;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yl_result")
public class YlResult {
    /**
     * id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * html内容
     */
    private String html;

    /**
     * 创建时间
     */
    private Date creatime;

    /**
     * 创建者
     */
    private Integer creatuser;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 更新者
     */
    private Integer updateuser;

    /**
     * 删除标示0未删除1已删除
     */
    private Integer deleteflag;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取html内容
     *
     * @return html - html内容
     */
    public String getHtml() {
        return html;
    }

    /**
     * 设置html内容
     *
     * @param html html内容
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 获取创建时间
     *
     * @return creatime - 创建时间
     */
    public Date getCreatime() {
        return creatime;
    }

    /**
     * 设置创建时间
     *
     * @param creatime 创建时间
     */
    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    /**
     * 获取创建者
     *
     * @return creatuser - 创建者
     */
    public Integer getCreatuser() {
        return creatuser;
    }

    /**
     * 设置创建者
     *
     * @param creatuser 创建者
     */
    public void setCreatuser(Integer creatuser) {
        this.creatuser = creatuser;
    }

    /**
     * 获取更新时间
     *
     * @return updatetime - 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取更新者
     *
     * @return updateuser - 更新者
     */
    public Integer getUpdateuser() {
        return updateuser;
    }

    /**
     * 设置更新者
     *
     * @param updateuser 更新者
     */
    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }

    /**
     * 获取删除标示0未删除1已删除
     *
     * @return deleteflag - 删除标示0未删除1已删除
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * 设置删除标示0未删除1已删除
     *
     * @param deleteflag 删除标示0未删除1已删除
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public YlResult() {
    }

    private YlResult(Build build) {
        this.html = build.html;
        this.creatime = build.creatime;
        this.creatuser = build.creatuser;
        this.updatetime = build.updatetime;
        this.updateuser = build.updateuser;
        this.deleteflag = build.deleteflag;
    }

    public static final class Build implements BuilderInterface<YlResult> {

        public static final Date now = new Date();

        /**
         * html内容
         */
        private String html;

        /**
         * 创建时间
         */
        private Date creatime;

        /**
         * 创建者
         */
        private Integer creatuser;

        /**
         * 更新时间
         */
        private Date updatetime;

        /**
         * 更新者
         */
        private Integer updateuser;

        /**
         * 删除标示0未删除1已删除
         */
        private Integer deleteflag;

        public String getHtml() {
            return html;
        }

        public Build setHtml(String html) {
            this.html = html;
            return this;
        }

        public Date getCreatime() {
            return creatime;
        }

        public Build setCreatime(Date creatime) {
            this.creatime = creatime;
            return this;
        }

        public Integer getCreatuser() {
            return creatuser;
        }

        public Build setCreatuser(Integer creatuser) {
            this.creatuser = creatuser;
            return this;
        }

        public Date getUpdatetime() {
            return updatetime;
        }

        public Build setUpdatetime(Date updatetime) {
            this.updatetime = updatetime;
            return this;
        }

        public Integer getUpdateuser() {
            return updateuser;
        }

        public Build setUpdateuser(Integer updateuser) {
            this.updateuser = updateuser;
            return this;
        }

        public Integer getDeleteflag() {
            return deleteflag;
        }

        public Build setDeleteflag(Integer deleteflag) {
            this.deleteflag = deleteflag;
            return this;
        }

        public Build() {
        }

        public Build(String html, Integer creatuser, Integer updateuser) {
            this.html = html;
            this.creatuser = creatuser;
            this.updateuser = updateuser;
            this.deleteflag = 0;
            this.updatetime = now;
        }

        @Override
        public YlResult build() {
            return new YlResult(this);
        }
    }
}