package com.hcb.newBilly.dao;

import com.hcb.newBilly.common.DicMethod;
import com.hcb.newBilly.dto.StatDictionaryDTO;
import com.hcb.newBilly.dto.StatYmdDTO;
import com.hcb.newBilly.dto.StatTypeDTO;
import com.hcb.newBilly.po.BillPO;
import com.hcb.newBilly.vo.DicStaticsVO;
import com.hcb.newBilly.vo.TimeSumStaticsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillStaticsDao {
    List<TimeSumStaticsVO> findTimeSumStatics(StatYmdDTO statisticsBillYMDDTO);

    Double getTimeSum(StatYmdDTO statisticsBillYMDDTO);

    @DicMethod
    List<DicStaticsVO> findDicSumCountStatics(StatDictionaryDTO statisticsBillDicDTO);

    @DicMethod
    List<BillPO> findTypeBills(StatTypeDTO statisticsTypeBillDTO);

    @DicMethod
    List<BillPO> findByTime(StatYmdDTO statYmdDTO);
}
