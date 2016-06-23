package domain;

import java.util.Date;

public class Coupon {
    private Integer id;

    private String code;

    private Integer userid;

    private String cardid;

    private Integer goodstypeid;

    private Integer cardtype;

    private Integer discount;

    private Integer leastcost;

    private Integer reducecost;

    private Integer giftday;

    private Date begintime;

    private Date endtime;

    private Integer status;

    private Date createtime;

    private Date lastupdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public Integer getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Integer goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getLeastcost() {
        return leastcost;
    }

    public void setLeastcost(Integer leastcost) {
        this.leastcost = leastcost;
    }

    public Integer getReducecost() {
        return reducecost;
    }

    public void setReducecost(Integer reducecost) {
        this.reducecost = reducecost;
    }

    public Integer getGiftday() {
        return giftday;
    }

    public void setGiftday(Integer giftday) {
        this.giftday = giftday;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}