package cn.edu.guet.insurance.controller;


import cn.edu.guet.insurance.bean.InsuranceList;
import cn.edu.guet.insurance.bean.SearchInsurancesDTO;
import cn.edu.guet.insurance.common.ResponseData;
import cn.edu.guet.insurance.service.InsurancesInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsurancesController {


    @Autowired
    private InsurancesInfoService infoService;

    @PostMapping("/createInsurances")
    public ResponseData createInsurances(@RequestBody InsuranceList insuranceList) {
        return infoService.createInsurances(insuranceList);
    }

    @PostMapping("/searchInsurances")
    public ResponseData searchInsurances(@RequestBody SearchInsurancesDTO insuranceDTO) {
        IPage searchModify = infoService.searchInsurances(insuranceDTO);
        return ResponseData.ok(searchModify);
    }

    @PostMapping("/deleteInsurances")
    public ResponseData deleteInsurances(@RequestParam("ids") List<Long> ids) {
        return infoService.deleteInsurances(ids);
    }

    @PostMapping("/updateInsurances")
    public ResponseData updateInsurances(@RequestBody InsuranceList insuranceList) {

        return infoService.updateInsurances((InsuranceList) infoService);

    }

}