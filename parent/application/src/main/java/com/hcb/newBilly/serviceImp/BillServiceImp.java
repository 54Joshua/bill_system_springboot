package com.hcb.newBilly.serviceImp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hcb.newBilly.common.BillyException;
import com.hcb.newBilly.common.EInOut;
import com.hcb.newBilly.common.EntityUtils;
import com.hcb.newBilly.common.IdCreator;
import com.hcb.newBilly.dao.BillDao;
import com.hcb.newBilly.dao.BillStaticsDao;
import com.hcb.newBilly.dto.*;
import com.hcb.newBilly.po.BillPO;
import com.hcb.newBilly.service.BillService;
import com.hcb.newBilly.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillDao billDao;
    @Autowired
    private BillStaticsDao billStaticsDao;


    @Override
    public Page<BillPO> findBillPO(BillFindDTO billFindDTO) {
        PageHelper.startPage(billFindDTO.getPageNum(), billFindDTO.getSize());
        List<BillPO> billPOS = billDao.findBillPO(billFindDTO);
        Page<BillPO> result = (Page<BillPO>) billPOS;
        return result;
    }

    @Override
    @Transactional(rollbackFor = BillyException.class)
    public void saveBill(BillDTO billDTO, String userId) {
        String inOutStatus = billDTO.getInOutStatus();
        BillPO billPO = new BillPO();
        EntityUtils.copyProperty(billDTO, billPO);
        billPO.setBillId(IdCreator.getNextId());
        billPO.setOperator(userId);
        billPO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        billPO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (EInOut.OUT.getDicId().equals(inOutStatus)) {
            //出
            billPO.setSum(EntityUtils.getMinus(billPO.getSum()));
        } else {//进
            billPO.setSum(EntityUtils.getPositive(billPO.getSum()));
        }
        billDao.saveBill(billPO);

    }

    @Override
    public void updateBill(BillDTO billDTO) {
        BillPO billPO = billDao.getBillById(billDTO.getBillId());
        Double sum;
        if (EInOut.OUT.getDicId().equals(billPO.getInOutStatus())) {
            sum = EntityUtils.getMinus(billDTO.getSum());
        } else {
            sum = EntityUtils.getPositive(billDTO.getSum());
        }
        billDTO.setSum(sum);
        billDTO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        billDao.updateBill(billDTO);
    }

    @Override
    public void deleteBill(String billId) {
        billDao.deleteBill(billId);
    }

    @Override
    public YMDStaticsVO getYMDStatics(StatYmdDTO statYmdDTO) {
        statYmdDTO.setTimeType(statYmdDTO.getTimeType());
        statYmdDTO.setTime(statYmdDTO.getTime());
        YMDStaticsVO ymdStaticsVO = new YMDStaticsVO();
        List<TimeSumStaticsVO> timeSumStatics = billStaticsDao.findTimeSumStatics(statYmdDTO);
        ymdStaticsVO.setTimeSumStaticsVOS(timeSumStatics);
        double timeSum = billStaticsDao.getTimeSum(statYmdDTO) == null ? 0 : billStaticsDao.getTimeSum(statYmdDTO);
        String tilte = "";
        String timeTypeStr = "";
        Calendar calendar = Calendar.getInstance();
        Date date = statYmdDTO.getTime();
        switch (statYmdDTO.getTimeType()) {
            case 1:
                tilte = "每年消费统计 净消费：" + timeSum + "元";
                timeTypeStr = "年";
                break;
            case 2:
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                tilte = year + "年每月消费统计 净消费：" + timeSum + "元";
                timeTypeStr = "月";
                break;
            case 3:
                calendar.setTime(date);
                int theYear = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                tilte = theYear + "年" + month + "月每天消费统计 净消费：" + timeSum + "元";
                timeTypeStr = "号";
                break;
            default:
                break;
        }
        ymdStaticsVO.setTimeTypeStr(timeTypeStr);
        ymdStaticsVO.setTitle(tilte);
        return ymdStaticsVO;
    }

    @Override
    public List<BillPO> findByTime(StatYmdDTO statYmdDTO) {
        List<BillPO> poList = billStaticsDao.findByTime(statYmdDTO);
        return poList;
    }

    @Override
    public StatTypeVO getStatType(StatDictionaryDTO statisticsBillDicDTO) {
        List<SelectStaticsVO> selectStaticsVOS = new ArrayList<>();
        List<DicStaticsVO> dicSumCountStatics = billStaticsDao.findDicSumCountStatics(statisticsBillDicDTO);
        int totalCount=0;
        BigDecimal totalSum=new BigDecimal("0");
        if (!CollectionUtils.isEmpty(dicSumCountStatics)) {
            for (DicStaticsVO dicStaticsVO:dicSumCountStatics) {
                SelectStaticsVO selectStaticsVO = new SelectStaticsVO();
                DictionaryVO dicContentVO = dicStaticsVO.getDicContentVO();
                if (dicContentVO != null) {
                    selectStaticsVO.setName(dicContentVO.getDictionaryContent());
                    selectStaticsVO.setUseFor(dicContentVO.getDicId());
                } else {
                    selectStaticsVO.setName("");
                    selectStaticsVO.setUseFor("");
                }
                selectStaticsVO.setCount(dicStaticsVO.getCount());
                totalCount+=dicStaticsVO.getCount();
                selectStaticsVO.setSum(dicStaticsVO.getSum().doubleValue());
                totalSum = totalSum.add(dicStaticsVO.getSum());
                selectStaticsVOS.add(selectStaticsVO);
            }
        }
        StatTypeVO statTypeVO=new StatTypeVO();
        statTypeVO.setSelectStaticsVOS(selectStaticsVOS);
        statTypeVO.setTotalCount(totalCount);
        statTypeVO.setTotalSum(totalSum.doubleValue());
        return statTypeVO;
    }

    @Override
    public List<BillPO> findTypeBill(StatTypeDTO statisticsTypeBillDTO) {
        List<BillPO> typeBills = billStaticsDao.findTypeBills(statisticsTypeBillDTO);
        return typeBills;
    }


}
