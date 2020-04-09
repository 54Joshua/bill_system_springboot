package com.hcb.newBilly.service;

import com.github.pagehelper.Page;
import com.hcb.newBilly.dto.*;
import com.hcb.newBilly.po.BillPO;
import com.hcb.newBilly.vo.SelectStaticsVO;
import com.hcb.newBilly.vo.StatTypeVO;
import com.hcb.newBilly.vo.YMDStaticsVO;

import java.util.List;

public interface BillService {
    Page<BillPO> findBillPO(BillFindDTO billFindDTO);

    void saveBill(BillDTO billDTO, String userId);

    void updateBill(BillDTO billDTO);

    void deleteBill(String billId);

    YMDStaticsVO getYMDStatics(StatYmdDTO statisticsBillYMDDTO);

    List<BillPO> findByTime(StatYmdDTO statYmdDTO);

    StatTypeVO getStatType(StatDictionaryDTO statisticsBillDicDTO);

    List<BillPO> findTypeBill(StatTypeDTO statisticsTypeBillDTO);


}
