package pers.lance.platform.base.aspect;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import pers.lance.platform.base.bean.BaseRecordEntity;
import pers.lance.platform.bean.vo.UserLoginVO;

import java.io.Serializable;

/**
 * 操作记录拦截器
 *
 * @author lance
 * @date 2018-05-06
 */
public class RecordEmptyInterceptor extends EmptyInterceptor {


    private static final String CREATE_NAME = "createName";
    private static final String CREATE_BY = "createBy";
    private static final String UPDATE_NAME = "updateName";
    private static final String UPDATE_BY = "updateBy";

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (BaseRecordEntity.class.isAssignableFrom(entity.getClass())) {
            UserLoginVO user = (UserLoginVO) SecurityUtils.getSubject().getPrincipal();
            for (int i = 0; i < propertyNames.length; i++) {
                if (StringUtils.equalsAny(propertyNames[i], CREATE_NAME, UPDATE_NAME)) {
                    state[i] = user.getName();
                } else if (StringUtils.equalsAny(propertyNames[i], CREATE_BY, UPDATE_BY)) {
                    state[i] = Long.parseLong(user.getId());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (BaseRecordEntity.class.isAssignableFrom(entity.getClass())) {
            UserLoginVO user = (UserLoginVO) SecurityUtils.getSubject().getPrincipal();
            for (int i = 0; i < propertyNames.length; i++) {
                if (StringUtils.equals(propertyNames[i], UPDATE_NAME)) {
                    currentState[i] = user.getName();
                } else if (StringUtils.equals(propertyNames[i], UPDATE_BY)) {
                    currentState[i] = Long.parseLong(user.getId());
                }
            }
            return true;
        }
        return false;
    }


}
