package cn.edu.guet.insurance.controller;

import cn.edu.guet.insurance.bean.*;

import cn.edu.guet.insurance.common.ResponseData;
import cn.edu.guet.insurance.service.ModifyBasicInfoService;
import cn.edu.guet.insurance.service.ModifySummaryService;
import cn.edu.guet.insurance.service.PrefectureCountyService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;


@RestController
public class ModifyController {

    @Autowired

    ModifyBasicInfoService modifyBasicInfoService;

    @Autowired
    ModifySummaryService modifySummaryService;

    @Autowired
    PrefectureCountyService prefectureCountyService;


    @Transactional
    @PostMapping("/creatmodify")
    public ResponseData creatmodify(@RequestBody ModifyBasicInfo modifyBasicInfo) {
        return modifyBasicInfoService.creatmodify(modifyBasicInfo);
    }

    @PostMapping("/deleteModify")
    public ResponseData deleteModify(@RequestBody SearchModifyDTO searchModifyDTO) {
        System.out.println(searchModifyDTO.getDeleteId().size());
        return modifyBasicInfoService.deleteModify(searchModifyDTO);
    }

    @PostMapping("/updateModify")
    public ResponseData updateModify(@RequestBody ModifyBasicInfo modifyBasicInfo) {
        return modifyBasicInfoService.updateModify(modifyBasicInfo);
    }

    @PostMapping("/searchModify")
    public ResponseData searchModify(@RequestBody SearchModifyDTO searchModifyDTO) {
        IPage searchModify = modifyBasicInfoService.searchModify(searchModifyDTO);
        System.out.println("没有实力");
        return ResponseData.ok(searchModify);
    }

    @PostMapping("/getCountyByPrefecture")
    public ResponseData getCountyByPrefecture(@RequestBody SearchModifyDTO searchModifyDTO) {
        int prefecture = searchModifyDTO.getPrefecture();
        List<Integer> countyList = prefectureCountyService.getCountyByPrefecture(prefecture);
        return ResponseData.ok(countyList);
    }

    //迁改汇总
    @PostMapping("/searchModifySummary")
    public ResponseData searchModifySummary(@RequestBody SearchSummaryDTO searchSummaryDTO) {
        int year = Integer.parseInt(searchSummaryDTO.getCurrentYear());
        System.out.println(year);
        return modifySummaryService.searchModifySummary(searchSummaryDTO);
    }




}
