package com.msr.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.eduservice.entity.inform.CourseInfoForm;
import com.msr.eduservice.entity.vo.CoursePublishVo;
import com.msr.eduservice.query.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author msr
 * @since 2020-04-24
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);//取得页面输入信息
    /*
     * 根据id查询课程对象*/
    CourseInfoForm getCourseInfoFormById(String id);
    //修改对象
    void updateCourseInfoById(CourseInfoForm courseInfoForm);
    //查询
    CoursePublishVo getCoursePublishVoById(String id);
    //修改
    void publishCourseById(String id);
    //分页显示
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
}
