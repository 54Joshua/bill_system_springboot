package com.hcb.newBilly.service;

import com.hcb.newBilly.po.DictionaryPO;

import java.util.List;

public interface DictionaryService {
    void saveDictionary(DictionaryPO dictionaryPO);

    void deleteDictionary(String dicContentId);

    void updateDictionary(DictionaryPO dictionaryPO);

    List<DictionaryPO> findDictionaryByEnName(String enName,String userId);

}
