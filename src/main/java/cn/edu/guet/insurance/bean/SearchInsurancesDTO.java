package cn.edu.guet.insurance.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInsurancesDTO extends Page {


    /**
     * 页码
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 地州
     */
    @TableField(value = "prefecture")
    private Integer prefecture;

    /**
     *
     * 区县
     */
    @TableField(value = "county")
    private Integer county;

    /**
     * 项目名称
     */
    @TableField(value = "case_name")
    private String caseName;

    /**
     * 项目完成时间
     */
    @TableField(value = "case_occurrence_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date caseOccurrenceTime;
    /**
     * 网络层级
     */
    @TableField(value = "network_hierarchy")
    private Integer networkHierarchy;
    /**
     * 案件进展情况
     */
    @TableField(value = "case_progress_situation")
    private Integer caseProgressSituation;
    /**
     * 保险报案原因分类
     */
    @TableField(value = "reporting_reasons_engine")
    private Integer reportingReasonsEngine;
    /**
     * 赔付类型
     */
    @TableField(value = "compensation_engine")
    private Integer compensationEngine;
    /**
     * 赔付进度
     */
    @TableField(value = "compensation_progress")
    private Integer compensationProgress;

    private List<String> overWorkTime;

    private List<Integer> id;
}
