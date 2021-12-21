package com.lagou.service.imp;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return list;
    }

    /*
        根据courseId查询章节对应的课程信息
     */
    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /*
        添加章节信息
     */
    @Override
    public void saveSection(CourseSection section) {

        // 补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        courseContentMapper.saveSection(section);

    }

    /*
        修改章节信息
     */
    public void updateSection(CourseSection section) {

        // 补全信息
        section.setUpdateTime(new Date());

        // 调用updateSection
        courseContentMapper.updateSection(section);

    }

    /*
        修改章节状态
     */
    public void updateSectionStatus(Integer id,Integer status) {

        // 封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setId(id);
        courseSection.setUpdateTime(new Date());

        // 调用updateSectionStatus
        courseContentMapper.updateSectionStatus(courseSection);

    }

    /*
        保存课时信息
     */
    public void saveLesson(CourseLesson lesson) {

        // 补全课时信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        lesson.setIsDel(0);

        // 调用saveLesson
        courseContentMapper.saveLesson(lesson);

    }

    /*
        修改课时信息
     */
    public void updateLesson(CourseLesson lesson){

        // 补全课时信息
        Date date = new Date();
        lesson.setUpdateTime(date);

        // 调用updateLesson
        courseContentMapper.updateLesson(lesson);

    }

}
