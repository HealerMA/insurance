package cn.edu.guet.insurance.service.impl;


import cn.edu.guet.insurance.bean.InsuranceList;
import cn.edu.guet.insurance.bean.SearchInsurancesDTO;
import cn.edu.guet.insurance.common.ResponseData;
import cn.edu.guet.insurance.mapper.InsuranceListMapper;
import cn.edu.guet.insurance.service.InsuranceSummaryService;
import cn.edu.guet.insurance.service.InsurancesInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huanglusong
 * @description 针对表【modify_basic_info(modify_basic_info)】的数据库操作Service实现
 * @createDate 2023-07-22 21:05:27
 */
@Service
public class InsurancesInfoServiceImpl implements InsurancesInfoService {

    final Integer NO_FACTER = 0;

    @Autowired
    private InsuranceListMapper infoMapper;
    @Autowired
    private InsuranceSummaryService insuranceSummaryService;

    @Override
    public ResponseData createInsurances(InsuranceList insuranceList) {

        infoMapper.insert(insuranceList);

        //月份
        Date date = insuranceList.getCaseOccurrenceTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1; // 注意要加1，因为月份值从0开始
//年份
        int year = calendar.get(Calendar.YEAR);

        int prefecture = insuranceList.getPrefecture();
//总金额
        BigDecimal compensationAmount = insuranceList.getCompensationReceivedAmount();

        insuranceSummaryService.updateMonthlyCountAndTotalAmount(prefecture, month, year, compensationAmount);

        return ResponseData.ok("保存成功！");
    }

    @Override
    public ResponseData deleteInsurances(SearchInsurancesDTO insurancesDTO) {
        int delete = infoMapper.deleteBatchIds(insurancesDTO.getId());
        if (delete > 0) {
            return ResponseData.ok("删除成功");
        } else {
            return ResponseData.fail("删除失败");
        }
    }


    @Override
    public ResponseData updateInsurances(InsuranceList insuranceList) {
        int update = infoMapper.updateById(insuranceList);
        if (update > 0) {
            return ResponseData.ok("更新成功");
        } else {
            return ResponseData.fail("更新失败");
        }
    }

    @Override
    public IPage searchInsurances(SearchInsurancesDTO insurancesDTO) {
        Page page = new Page();
        page.setSize(insurancesDTO.getPageSize());
        page.setCurrent(insurancesDTO.getCurrentPage());
        QueryWrapper<InsuranceList> qw = new QueryWrapper();
        if (insurancesDTO.getPrefecture() != NO_FACTER) {
            qw.eq("prefecture", insurancesDTO.getPrefecture());
        }
        if (insurancesDTO.getCounty() != NO_FACTER) {
            qw.eq("county", insurancesDTO.getCounty());
        }
        if (!StringUtils.isBlank(insurancesDTO.getCaseName())) {
            qw.like("case_name", insurancesDTO.getCaseName());
        }
        if (CollectionUtil.isNotEmpty(insurancesDTO.getOverWorkTime())) {
            qw.between("case_occurrence_time", insurancesDTO.getOverWorkTime().get(0), insurancesDTO.getOverWorkTime().get(1));
        }
        if (insurancesDTO.getNetworkHierarchy() != NO_FACTER) {
            qw.eq("network_hierarchy", insurancesDTO.getNetworkHierarchy());
        }
        if (insurancesDTO.getCaseProgressSituation() != NO_FACTER) {
            qw.eq("case_progress_situation", insurancesDTO.getCaseProgressSituation());
        }
        if (insurancesDTO.getReportingReasonsEngine() != NO_FACTER) {
            qw.eq("reporting_reasons_engine", insurancesDTO.getReportingReasonsEngine());
        }
        if (insurancesDTO.getCompensationEngine() != NO_FACTER) {
            qw.eq("compensation_engine", insurancesDTO.getCompensationEngine());
        }
        if (insurancesDTO.getCompensationProgress() != NO_FACTER) {
            qw.eq("compensation_progress", insurancesDTO.getCompensationProgress());
        }
        qw.orderByDesc("prefecture");
        IPage listResult = infoMapper.selectPage(page, qw);
        return listResult;
    }

}




