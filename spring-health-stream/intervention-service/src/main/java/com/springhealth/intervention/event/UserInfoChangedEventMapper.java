package com.springhealth.intervention.event;

import com.springhealth.intervention.client.UserMapper;

public class UserInfoChangedEventMapper{
    private String type;
    private String operation;
    private UserMapper user;

    public UserInfoChangedEventMapper(){
        super();
    }

    public UserInfoChangedEventMapper(String type, String operation, UserMapper user) {
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

	public UserMapper getUser() {
		return user;
	}

	public void setUser(UserMapper user) {
		this.user = user;
	}

	@Override
    public String toString() {
        return "UserInfoChangedEventMapper [type=" + type +
                ", operation=" + operation +
                ", userId="  + user.getId() +"]";
    }
}
