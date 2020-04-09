package com.hcb.newBilly.dao;

import com.hcb.newBilly.po.DictionaryPO;
import com.hcb.newBilly.vo.DictionaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface DictionaryDao {

    @CacheEvict(value = "billEhcache", allEntries = true)
    void saveDictionary(DictionaryPO dictionaryPO);

    @CacheEvict(value = "billEhcache", allEntries = true)
    void updateDictionary(DictionaryPO dictionaryPO);

    @CacheEvict(value = "billEhcache", allEntries = true)
    void deleteDictionary(@Param("dicContentId") String dicContentId);

    @Cacheable(value = "billEhcache", key = "'findDicContentByEnName_'+#enName+'_'+#userId")
    List<DictionaryPO> findDictionaryByEnName(@Param("enName") String enName,@Param("userId")String userId);

    @Cacheable(value = "billEhcache", key = "'getDictionaryById_'+#dicId")
    DictionaryVO getDictionaryById(@Param("dicId") String dicId);

    int checkRepeate(@Param("enName") String enName, @Param("content") String content, @Param("dicId") String dicId);

}
