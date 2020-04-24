package com.msr.eduservice.service;

import com.msr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.eduservice.entity.vo.CourseInfoForm;

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

}
