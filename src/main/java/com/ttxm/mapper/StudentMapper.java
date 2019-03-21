package com.ttxm.mapper;

import com.ttxm.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

//    @Select("select * from student where stu_id=#{stuId}")
    Student selectStudent(@Param("stuId") Integer stuId);

    List<Student> batchSelectStudents(@Param("stuIds") List<Integer> stuIds);

    List<Student> selectStudents(Student student);

    Integer insertStudent(Student student);

    Integer batchInsertStudents(@Param("stus") List<Student> stus);

    Integer updateStudent(Student student);

    Integer deleteStudent(@Param("stuId") Integer stuId);
}
