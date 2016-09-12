package com.zwh.social.api.model;

import java.util.Date;

public class Pay {
    private Integer payid;

    private String orderno;

    private Integer reguserid;

    private Float amount;
    
    private Integer orderid;
    
    private Integer ordertype;
    
    private String ordersubject;
    
    private String orderbody;

    private String channel;
    
    private Integer orderstate;

	private Date createdate;
	
	/**
	 * 客户端ip非持久化字段，用于提供给支付接口
	 */
	private String clientip;

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
	
	/**
	 * 微信openid，非持久化字段，用于提供给支付接口
	 */
	private String openid;	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * PC支付成功跳转的url
	 */
	private String successurl;
		
	public String getSuccessurl() {
		return successurl;
	}

	public void setSuccessurl(String successurl) {
		this.successurl = successurl;
	}

	public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Integer getReguserid() {
        return reguserid;
    }

    public void setReguserid(Integer reguserid) {
        this.reguserid = reguserid;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    
    public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrdersubject() {
		return ordersubject;
	}

	public void setOrdersubject(String ordersubject) {
		this.ordersubject = ordersubject;
	}

	public String getOrderbody() {
		return orderbody;
	}

	public void setOrderbody(String orderbody) {
		this.orderbody = orderbody;
	}


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}
	
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}