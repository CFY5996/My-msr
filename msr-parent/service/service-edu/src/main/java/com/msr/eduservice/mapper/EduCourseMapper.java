package com.msr.eduservice.mapper;

import com.msr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msr.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author msr
 * @since 2020-04-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
CoursePublishVo selectCoursePublishVoById(String id);
}
