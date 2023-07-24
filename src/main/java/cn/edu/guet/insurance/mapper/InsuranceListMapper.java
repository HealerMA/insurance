package cn.edu.guet.insurance.mapper;

import cn.edu.guet.insurance.bean.InsuranceList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 27258
* @description 针对表【t_insurance_list(保险清单表;保险清单表)】的数据库操作Mapper
* @createDate 2023-07-24 17:25:59
* @Entity cn.edu.guet.insurance.bean.InsuranceList
*/
@Mapper
public interface InsuranceListMapper extends BaseMapper<InsuranceList> {

}




