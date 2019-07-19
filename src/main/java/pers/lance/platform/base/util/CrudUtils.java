package pers.lance.platform.base.util;


import pers.lance.platform.base.bean.CommonQueryParams;
import pers.lance.platform.base.bean.BaseEntity;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 通用CURD方法
 *
 * @author lance
 * @date 2018-05-06
 */
public class CrudUtils {

    private static final String SET_ID = "setId";
    private static final String GET_ID = "getId";

    /**
     * 分页查询前置操作
     *
     * @param queryParams
     */
    public static <T extends CommonQueryParams> void pageBefore(T queryParams) {
        ErrorUtils.isNull(queryParams);
        queryParams.init();
        PageHelper.startPage(queryParams.getPage(), queryParams.getRows());
    }

    /**
     * 通用保存方法
     *
     * @param source
     * @param clazz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K extends BaseEntity> K save(T source, Class<K> clazz, JpaRepository repository) {
        K target = newInstance(clazz, source);
        repository.save(target);
        return target;
    }

    /**
     * 通用批量保存方法
     *
     * @param list
     * @param clazz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K extends BaseEntity> List<K> batchSave(List<T> list, Class<K> clazz, JpaRepository repository) {
        List<K> entities = new ArrayList<K>(list.size());
        for (T source : list) {
            K obj = newInstance(clazz, source);
            entities.add(obj);
        }
        repository.saveAll(entities);
        return entities;
    }


    /**
     * 通用更改方法
     *
     * @param source
     * @param clazz
     * @param repository
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> K update(T source, Class<K> clazz, JpaRepository repository) {
        K target = null;
        try {
            Method getId = source.getClass().getMethod(GET_ID);
            Long id = (Long) getId.invoke(source);
            target = (K) repository.getOne(id);
            CustomCommonUtils.copyNonNullProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtils.message();
        }
        return target;
    }

    /**
     * 通用批量更改方法
     *
     * @param list
     * @param clazz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<K> batchUpdate(List<T> list, Class<K> clazz, JpaRepository repository) {
        List<K> entities = new ArrayList<K>(list.size());
        for (T source : list) {
            Long id = null;
            try {
                Method getId = source.getClass().getMethod(GET_ID);
                id = (Long) getId.invoke(source);
            } catch (Exception e) {
                e.printStackTrace();
                ErrorUtils.message();
            }
            K target = (K) repository.getOne(id);
            CustomCommonUtils.copyNonNullProperties(source, target);
            entities.add(target);
        }

        return entities;
    }


    /**
     * 删除全部 list
     *
     * @param repository
     * @param list
     */
    public static <T extends BaseEntity> void deleteAll(JpaRepository repository, List<T> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            repository.deleteInBatch(list);
        }
    }

    /**
     * 删除 entity
     *
     * @param repository
     * @param entity
     */
    public static <T> void delete(JpaRepository repository, T entity) {
        if (Objects.isNull(entity)) {
            return;
        }
        repository.delete(entity);
    }

    /**
     * 通过 list 获取全部符合条件的 实体
     *
     * @param repository
     * @param list
     * @return
     */
    public static <T> List<T> findAllByIdList(JpaRepository<T, Long> repository, List<String> list) {
        List<Long> idList = new ArrayList<>();
        for (String s : list) {
            idList.add(Long.parseLong(s));
        }
        return repository.findAllById(idList);
    }

    private static <T, K extends BaseEntity> K newInstance(Class<K> clazz, T source) {
        K obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtils.message();
        }
        BeanUtils.copyProperties(source, obj);
        obj.setId(null);
        return obj;
    }

}

