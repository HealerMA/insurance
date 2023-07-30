package cn.edu.guet.insurance.controller;


import cn.edu.guet.insurance.bean.InsuranceList;
import cn.edu.guet.insurance.bean.SearchInsurancesDTO;
import cn.edu.guet.insurance.bean.SearchSummaryDTO;
import cn.edu.guet.insurance.common.ResponseData;
import cn.edu.guet.insurance.service.InsuranceSummaryService;
import cn.edu.guet.insurance.service.InsurancesInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InsurancesController {


    @Autowired
    private InsurancesInfoService infoService;
    @Autowired
    private InsuranceSummaryService insuranceSummaryService;

    @Transactional
    @PostMapping("/createInsurances")
    public ResponseData createInsurances(@RequestBody InsuranceList insuranceList) {
        System.out.println(insuranceList+"+++++++++++++++++++++++++++");
        return infoService.createInsurances(insuranceList);
    }

    @PostMapping("/deleteInsurances")
    public ResponseData deleteInsurances(@RequestBody SearchInsurancesDTO insurancesDTO) {
        return infoService.deleteInsurances(insurancesDTO);
    }

    @PostMapping("/updateInsurances")
    public ResponseData updateInsurances(@RequestBody InsuranceList insuranceList) {
        return infoService.updateInsurances(insuranceList);
    }

    @PostMapping("/searchInsurances")
    public ResponseData searchInsurances(@RequestBody SearchInsurancesDTO insuranceDTO) {
        IPage searchModify = infoService.searchInsurances(insuranceDTO);
        return ResponseData.ok(searchModify);
    }

    //保险汇总
    @Transactional
    @PostMapping("/searchInsuranceSummary")
    public ResponseData searchInsuranceSummary(@RequestBody SearchSummaryDTO summaryDTO) {
        int year = Integer.parseInt(summaryDTO.getCurrentYear());
        System.out.println(year);
        return insuranceSummaryService.searchInsuranceSummary(summaryDTO);
    }

}
