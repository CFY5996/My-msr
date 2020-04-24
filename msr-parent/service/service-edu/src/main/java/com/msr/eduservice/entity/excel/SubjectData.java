package com.msr.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    //  一级分类的属性
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    //二级分类的属性
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
