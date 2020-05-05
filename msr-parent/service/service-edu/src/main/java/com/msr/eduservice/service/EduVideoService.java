package com.msr.eduservice.service;

import com.msr.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msr.eduservice.entity.inform.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author msr
 * @since 2020-04-24
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean getCountByChapterId(String chapterId);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

}
