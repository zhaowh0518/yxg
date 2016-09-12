package com.zwh.social.api.view;

import com.zwh.social.api.model.User;

public class YuanfenView {
	//是否为推荐用户，1->为推荐用户,客户端需要大图显示（注意每9条数据中只能有一个用户为true）,
	private Integer isRecUser;
	//是否打过招呼，1->已打招呼, 0->未打招呼,
	private Integer isSayHello;
	private User userBase;
	public Integer getIsRecUser() {
		return isRecUser;
	}
	public void setIsRecUser(Integer isRecUser) {
		this.isRecUser = isRecUser;
	}
	public Integer getIsSayHello() {
		return isSayHello;
	}
	public void setIsSayHello(Integer isSayHello) {
		this.isSayHello = isSayHello;
	}
	public User getUserBase() {
		return userBase;
	}
	public void setUserBase(User userBase) {
		this.userBase = userBase;
	}
	
	
	public static YuanfenView  getYuanfenView(User user){
		YuanfenView view = new YuanfenView();
		view.setIsRecUser(0);
		view.setIsSayHello(0);
		view.setUserBase(user);
		return view;
	}
}
