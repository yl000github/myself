package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Quotation {
    private Integer id;

    private String quotationcustomernumber;

    private Integer goodsid;

    private Integer goodstypeid;

    private Integer cityid;

    private Integer supplierid;

    private Integer carid;

    private Integer userid;

    private String carlicenseno;

    private String carframeno;

    private String carengineno;

    private String carowner;

    private String carownerphoneno;

    private String ownercardid;

    private String insuredname;

    private String insuredcardid;

    private String businessinsurance;

    private String trafficinsurance;

    private String protectionplan;

    private Date jqxstartdate;

    private Date jqxenddate;

    private Date syxstartdate;

    private Date syxenddate;

    private String jqxproposalno;

    private String syxproposalno;

    private BigDecimal amount;

    private String quotemode;

    private Integer quotationstatus;

    private Date timeexpire;

    private String isvalid;

    private Integer lockstatus;

    private String lockowner;

    private String faildescription;

    private Date createtime;

    private Date lastupdate;

    private Date jqxlastyearenddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuotationcustomernumber() {
        return quotationcustomernumber;
    }

    public void setQuotationcustomernumber(String quotationcustomernumber) {
        this.quotationcustomernumber = quotationcustomernumber == null ? null : quotationcustomernumber.trim();
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Integer goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCarlicenseno() {
        return carlicenseno;
    }

    public void setCarlicenseno(String carlicenseno) {
        this.carlicenseno = carlicenseno == null ? null : carlicenseno.trim();
    }

    public String getCarframeno() {
        return carframeno;
    }

    public void setCarframeno(String carframeno) {
        this.carframeno = carframeno == null ? null : carframeno.trim();
    }

    public String getCarengineno() {
        return carengineno;
    }

    public void setCarengineno(String carengineno) {
        this.carengineno = carengineno == null ? null : carengineno.trim();
    }

    public String getCarowner() {
        return carowner;
    }

    public void setCarowner(String carowner) {
        this.carowner = carowner == null ? null : carowner.trim();
    }

    public String getCarownerphoneno() {
        return carownerphoneno;
    }

    public void setCarownerphoneno(String carownerphoneno) {
        this.carownerphoneno = carownerphoneno == null ? null : carownerphoneno.trim();
    }

    public String getOwnercardid() {
        return ownercardid;
    }

    public void setOwnercardid(String ownercardid) {
        this.ownercardid = ownercardid == null ? null : ownercardid.trim();
    }

    public String getInsuredname() {
        return insuredname;
    }

    public void setInsuredname(String insuredname) {
        this.insuredname = insuredname == null ? null : insuredname.trim();
    }

    public String getInsuredcardid() {
        return insuredcardid;
    }

    public void setInsuredcardid(String insuredcardid) {
        this.insuredcardid = insuredcardid == null ? null : insuredcardid.trim();
    }

    public String getBusinessinsurance() {
        return businessinsurance;
    }

    public void setBusinessinsurance(String businessinsurance) {
        this.businessinsurance = businessinsurance == null ? null : businessinsurance.trim();
    }

    public String getTrafficinsurance() {
        return trafficinsurance;
    }

    public void setTrafficinsurance(String trafficinsurance) {
        this.trafficinsurance = trafficinsurance == null ? null : trafficinsurance.trim();
    }

    public String getProtectionplan() {
        return protectionplan;
    }

    public void setProtectionplan(String protectionplan) {
        this.protectionplan = protectionplan == null ? null : protectionplan.trim();
    }

    public Date getJqxstartdate() {
        return jqxstartdate;
    }

    public void setJqxstartdate(Date jqxstartdate) {
        this.jqxstartdate = jqxstartdate;
    }

    public Date getJqxenddate() {
        return jqxenddate;
    }

    public void setJqxenddate(Date jqxenddate) {
        this.jqxenddate = jqxenddate;
    }

    public Date getSyxstartdate() {
        return syxstartdate;
    }

    public void setSyxstartdate(Date syxstartdate) {
        this.syxstartdate = syxstartdate;
    }

    public Date getSyxenddate() {
        return syxenddate;
    }

    public void setSyxenddate(Date syxenddate) {
        this.syxenddate = syxenddate;
    }

    public String getJqxproposalno() {
        return jqxproposalno;
    }

    public void setJqxproposalno(String jqxproposalno) {
        this.jqxproposalno = jqxproposalno == null ? null : jqxproposalno.trim();
    }

    public String getSyxproposalno() {
        return syxproposalno;
    }

    public void setSyxproposalno(String syxproposalno) {
        this.syxproposalno = syxproposalno == null ? null : syxproposalno.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getQuotemode() {
        return quotemode;
    }

    public void setQuotemode(String quotemode) {
        this.quotemode = quotemode == null ? null : quotemode.trim();
    }

    public Integer getQuotationstatus() {
        return quotationstatus;
    }

    public void setQuotationstatus(Integer quotationstatus) {
        this.quotationstatus = quotationstatus;
    }

    public Date getTimeexpire() {
        return timeexpire;
    }

    public void setTimeexpire(Date timeexpire) {
        this.timeexpire = timeexpire;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public Integer getLockstatus() {
        return lockstatus;
    }

    public void setLockstatus(Integer lockstatus) {
        this.lockstatus = lockstatus;
    }

    public String getLockowner() {
        return lockowner;
    }

    public void setLockowner(String lockowner) {
        this.lockowner = lockowner == null ? null : lockowner.trim();
    }

    public String getFaildescription() {
        return faildescription;
    }

    public void setFaildescription(String faildescription) {
        this.faildescription = faildescription == null ? null : faildescription.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Date getJqxlastyearenddate() {
        return jqxlastyearenddate;
    }

    public void setJqxlastyearenddate(Date jqxlastyearenddate) {
        this.jqxlastyearenddate = jqxlastyearenddate;
    }
}