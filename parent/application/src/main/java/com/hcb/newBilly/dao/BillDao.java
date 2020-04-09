package com.hcb.newBilly.dao;

import com.hcb.newBilly.common.DicMethod;
import com.hcb.newBilly.dto.BillDTO;
import com.hcb.newBilly.dto.BillFindDTO;
import com.hcb.newBilly.po.BillPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillDao {
    @DicMethod()
    List<BillPO> findBillPO(BillFindDTO billFindDTO);

    BillPO getBillById(@Param("billId") String billId);

    void saveBill(BillPO billPO);

    void updateBill(BillDTO billDTO);

    void deleteBill(@Param("billId") String billId);

}
