package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        根据课程id查询关联的章节信息及章节关联的课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        根据courseId查询章节对应的课程信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /*
        添加章节信息
     */
    public void saveSection(CourseSection section);

    /*
        修改章节信息
     */
    public void updateSection(CourseSection section);

    /*
        修改章节状态
     */
    public void updateSectionStatus(Integer id,Integer status);

    /*
        保存课时信息
     */
    public void saveLesson(CourseLesson lesson);

    /*
        修改课时信息
     */
    public void updateLesson(CourseLesson lesson);
}
