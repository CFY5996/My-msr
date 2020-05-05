package com.msr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msr.eduservice.entity.EduVideo;
import com.msr.eduservice.entity.inform.VideoInfoForm;
import com.msr.eduservice.mapper.EduVideoMapper;
import com.msr.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msr.servicebase.exception.MSRException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author msr
 * @since 2020-04-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }
    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm){
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,video);
        boolean result  = this.save(video);

        if (!result){
            throw new MSRException(20001,"课时信息保存失败");
        }
    }
    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        //从video表中取数据
        EduVideo video = this.getById(id);
        if(video == null){
            throw new MSRException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);

        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        //保存课时基本信息
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.updateById(video);
        if(!result){
            throw new MSRException(20001, "课时信息保存失败");
        }
    }
    @Override
    public boolean removeVideoById(String id) {


        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }
}
