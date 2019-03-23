package com.ttxm;

import com.ttxm.mapper.StudentMapper;
import com.ttxm.pojo.Student;
import com.ttxm.util.SqlSessionFactorySingleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentTest
{


    @Test
    public void testSelect()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Integer stuId = 1;

            Student student = studentMapper.selectStudent(stuId);

            Assert.assertNotNull("can't find student with stuid=" + stuId, student);


        }
    }

    @Test
    public void testBatchSelect()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            List<Student> studentList = studentMapper.batchSelectStudents(Arrays.asList(1,2,3,5,9,10,12));

            Logger logger = LogManager.getLogger(getClass().getName());
            logger.trace("trace test");
            logger.debug("debug test");
            logger.info("info test");
            logger.warn("warn test");
            logger.error("error test");
        }
    }

    @Test
    public void testSelects()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Student student = new Student();
            student.setStuId(6);
            student.setStuName("%isabella%");

            List<Student> students = studentMapper.selectStudents(student);

            Assert.assertEquals(1, students.size());

        }
    }

    @Test
    public void testInsert()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Student student = new Student();
            student.setStuName("isabella");
            student.setAge(18);
            student.setBirth(LocalDate.of(1991, 12, 10));

            Integer result = studentMapper.insertStudent(student);
            Assert.assertEquals(1, result.longValue());

        }
    }

    @Test
    public void testBatchInsert()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            List<Student> studentList = new ArrayList<>();

            Student student1 = new Student();
            student1.setStuName("abby");
            student1.setAge(16);
            student1.setBirth(LocalDate.of(2000, 1, 1));

            studentList.add(student1);

            Student student2 = new Student();
            student2.setStuName("bella");
            student2.setAge(22);
            student2.setBirth(LocalDate.of(1998, 2, 2));

            studentList.add(student2);

            Student student3 = new Student();
            student3.setStuName("angella");
            student3.setAge(12);
            student3.setBirth(LocalDate.of(2008, 3, 3));

            studentList.add(student3);

            Integer result = studentMapper.batchInsertStudents(studentList);

            Assert.assertEquals(3, result.longValue());
        }
    }

    @Test
    public void testUpdate()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Student student = new Student();
            student.setStuId(27);
            student.setStuName("isabella ling");
            student.setBirth(LocalDate.of(1991, 12, 30));

            Integer result = studentMapper.updateStudent(student);

            Assert.assertEquals(1, result.longValue());
        }
    }

    @Test
    public void testDelete()
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactorySingleton.getInstance();

        try(SqlSession session = sqlSessionFactory.openSession(true))
        {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Integer result = studentMapper.deleteStudent(26);

            Assert.assertEquals(1, result.longValue());
        }
    }


}
