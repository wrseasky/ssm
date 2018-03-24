package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Set;

public class Classes implements Serializable {
	private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    private Set<Student> students;
    
    private Integer version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + ", version=" + version + "]";
	}
}
