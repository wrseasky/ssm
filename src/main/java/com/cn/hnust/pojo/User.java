package com.cn.hnust.pojo;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private float age;
    
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", userName=" + userName + ", password=" + password + ", age=" + age + ", status=" + status + "]";
	}
	
	
    
}