package com.springhealth.user.event;

import com.springhealth.user.domain.User;

public class UserInfoChangedEvent{
	//事件类型
    private String type;
    //事件所对应的操作
    private String operation;
    //事件对应的领域模型
    private User user;

    public  UserInfoChangedEvent(String type, String operation, User user) {
        super();
        this.type   = type;
        this.operation = operation;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}