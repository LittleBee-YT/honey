package com.lbee.common.data.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbee.common.auth.client.OauthUser;
import com.lbee.common.auth.client.OauthContext;
import com.lbee.common.data.util.EntityUtils;
import com.lbee.common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class BaseDBServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T>
        implements BaseDBService<T> {

    // 获取登陆用户ID
    protected OauthUser getLoginUser() {
        if (SpringUtils.containsBean("oauthServer")) {
            OauthContext oauthServer = (OauthContext) SpringUtils.getBean("oauthServer");
            return oauthServer.getLoginUser();
        } else {
            return null;
        }
    }

    @Override
    public boolean save(T entity) {
        EntityUtils.preInsert(entity, getLoginUser());
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        EntityUtils.preUpdate(entity, getLoginUser());
        return super.updateById(entity);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        entityList.forEach(entity ->
                EntityUtils.preInsert(entity, getLoginUser())
        );
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        entityList.forEach(entity ->
                EntityUtils.preInsert(entity, getLoginUser())
        );
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        entityList.forEach(entity ->
                EntityUtils.preUpdate(entity, getLoginUser())
        );
        return super.updateBatchById(entityList);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        entityList.forEach(entity ->
                EntityUtils.preUpdate(entity, getLoginUser())
        );
        return super.updateBatchById(entityList, batchSize);
    }
}
