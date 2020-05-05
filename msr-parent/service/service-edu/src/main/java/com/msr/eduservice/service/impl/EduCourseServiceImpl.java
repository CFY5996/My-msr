package com.msr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.eduservice.entity.EduCourse;
import com.msr.eduservice.entity.EduCourseDescription;
import com.msr.eduservice.entity.inform.CourseInfoForm;
import com.msr.eduservice.entity.vo.CoursePublishVo;
import com.msr.eduservice.mapper.EduCourseMapper;
import com.msr.eduservice.query.CourseQuery;
import com.msr.eduservice.service.EduCourseDescriptionService;
import com.msr.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.servicebase.exception.MSRException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author msr
 * @since 2020-04-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;


    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //保存课程基本信息
        EduCourse course = new EduCourse();
        course.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            throw new MSRException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if(!resultDescription){
            throw new MSRException(20001, "课程详情信息保存失败");
        }

        return course.getId();
    }
    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        EduCourse course = this.getById(id);
        System.out.println("id:"+id);
        System.out.println("course:"+course);
        if(course == null){
            throw new MSRException(20001, "数据不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);

        EduCourseDescription courseDescription = courseDescriptionService.getById(id);
        if(course != null){
            courseInfoForm.setDescription(courseDescription.getDescription());
        }

        return courseInfoForm;
    }
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new MSRException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.updateById(courseDescription);
        if(!resultDescription){
            throw new MSRException(20001, "课程详情信息保存失败");
        }
    }
    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {

        return baseMapper.selectCoursePublishVoById(id);
    }
    @Override
    public void publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus(EduCourse.COURSE_NORMAL);
        baseMapper.updateById(course);
    }
    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

}
