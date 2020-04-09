package com.hcb.newBilly.serviceImp;

import com.hcb.newBilly.common.BillyException;
import com.hcb.newBilly.common.IdCreator;
import com.hcb.newBilly.dao.DictionaryDao;
import com.hcb.newBilly.po.DictionaryPO;
import com.hcb.newBilly.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceImp implements DictionaryService {
    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public void saveDictionary(DictionaryPO dictionaryPO) {
        if (StringUtils.isBlank(dictionaryPO.getDictionaryContent())) {
            throw new BillyException("字典内容不能为空");
        }
        int count = dictionaryDao.checkRepeate(dictionaryPO.getDictionaryEn(), dictionaryPO.getDictionaryContent(), null);
        if (count > 0) {
            throw new BillyException("字典【" + dictionaryPO.getDictionaryContent() + "】重复");
        }
        String dicContentId = IdCreator.getNextId();
        dictionaryPO.setDicId(dicContentId);
        dictionaryDao.saveDictionary(dictionaryPO);
    }

    @Override
    public void deleteDictionary(String dicContentId) {
        dictionaryDao.deleteDictionary(dicContentId);
    }

    @Override
    public void updateDictionary(DictionaryPO dictionaryPO) {
        int count = dictionaryDao.checkRepeate(dictionaryPO.getDictionaryEn(), dictionaryPO.getDictionaryContent(), dictionaryPO.getDicId());
        if (count > 0) {
            throw new BillyException("字典【" + dictionaryPO.getDictionaryContent() + "】重复");
        }
        dictionaryDao.updateDictionary(dictionaryPO);
    }

    @Override
    public List<DictionaryPO> findDictionaryByEnName(String enName,String userId) {
        List<DictionaryPO> dicContentPOS = dictionaryDao.findDictionaryByEnName(enName,userId);
        if (null == dicContentPOS) {
            dicContentPOS = new ArrayList<>();
        }
        return dicContentPOS;
    }


}
