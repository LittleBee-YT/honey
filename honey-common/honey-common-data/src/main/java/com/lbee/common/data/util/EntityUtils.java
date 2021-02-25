package com.lbee.common.data.util;

import com.lbee.common.auth.client.OauthUser;
import com.lbee.common.data.base.BaseEntity;
import com.lbee.common.data.dict.DelFlag;
import lombok.experimental.UtilityClass;
import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class EntityUtils {

    /**
     * 插入之前执行方法，需要手动调用
     */
    public <T extends BaseEntity> void preInsert(T entity, OauthUser loginUser) {
        if (null != loginUser) {
            entity.setCreateBy(loginUser.getUserId());
            entity.setUpdateBy(loginUser.getUserId());
        }

        LocalDateTime now = LocalDateTime.now();
        entity.setCreateDate(now);
        entity.setUpdateDate(now);
        entity.setDelFlag(DelFlag.DEL_FLAG_NORMAL);
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public <T extends BaseEntity> void preUpdate(T entity, OauthUser loginUser) {
        if (null != loginUser) {
            entity.setUpdateBy(loginUser.getUserId());
        }
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateDate(now);
    }

    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String[] s = uuid.split("-");
        return s[0] + s[1] + s[2] + s[3] + s[4];
    }

}
