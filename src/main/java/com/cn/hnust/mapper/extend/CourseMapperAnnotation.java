package com.cn.hnust.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cn.hnust.mapper.CourseMapper;
import com.cn.hnust.pojo.Course;

public interface CourseMapperAnnotation extends CourseMapper {

	@Insert("insert into jpa.course(course_name) value(#{courseName})")
	@Options(useGeneratedKeys = true, keyProperty = "id")//add后生成id
	public int add(Course course);

	@Delete("delete from jpa.course where id = #{id}")
	public int deleteById(int id);

	@Update("update jpa.course set course_name = #{courseName} where id = #{id}")
	public int update(Course course);

	@Select("select * from jpa.course where id = #{id}")
	public Course getCourseById(int id);

	@Select("select * from jpa.course")
	public List<Course> getAllCourses();
	
	//批量插入
	@InsertProvider(type = CourseMapperAnnotationProvider.class, method = "insertAll")//method指CourseMapperAnnotationProvider中方法
    void insertAllByAnnotation(@Param("list") List<Course> courses);
	//@InsertProvider会将courses集合以list为键传入到CourseMapperAnnotationProvider中insertAll方法的map集合中

}
