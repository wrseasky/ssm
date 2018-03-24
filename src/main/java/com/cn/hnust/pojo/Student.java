package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

    private int sid;

    private String sname;

    private Classes classes;
    
    private List<Course> courses;

    private Integer version;
    
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", classes=" + classes + ", version=" + version + "]";
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	

}