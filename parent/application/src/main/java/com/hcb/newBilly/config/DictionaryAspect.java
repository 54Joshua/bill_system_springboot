package com.hcb.newBilly.config;

import com.hcb.newBilly.common.DicField;
import com.hcb.newBilly.dao.DictionaryDao;
import com.hcb.newBilly.vo.DictionaryVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Aspect
@Component
public class DictionaryAspect {
    private final DictionaryDao dictionaryDao;

    @Autowired
    public DictionaryAspect(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Pointcut("@annotation(com.hcb.newBilly.common.DicMethod)")
    public void useDictionary() {
    }

    @AfterReturning(pointcut = "useDictionary()", returning = "result")
    public void after(Object result) throws Exception {
        Class<?> classZ = null;
        if (result instanceof List) {
            List<Object> results = (List<Object>) result;
            for (Object obj : results) {
                if (classZ == null) {
                    classZ = obj.getClass();
                }
                Field[] fields = classZ.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    DicField fieldAnnotation = field.getAnnotation(DicField.class);
                    if (fieldAnnotation != null) {
                        DictionaryVO dicContentVO = (DictionaryVO) field.get(obj);
                        if (dicContentVO != null) {
                            DictionaryVO contentVO = dictionaryDao.getDictionaryById(dicContentVO.getDicId());
                            field.set(obj, contentVO);
                        }

                    }
                }
            }
        } else if (result != null) {
            Field[] fields = classZ.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                DicField fieldAnnotation = field.getAnnotation(DicField.class);
                if (fieldAnnotation != null) {
                    DictionaryVO dicContentVO = (DictionaryVO) field.get(result);
                    DictionaryVO contentVO = dictionaryDao.getDictionaryById(dicContentVO.getDicId());
                    field.set(result, contentVO);
                }
            }
        }
    }
}
