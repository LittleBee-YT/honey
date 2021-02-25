package com.lbee.common.data.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbee.common.data.base.BaseEntity;

public interface BaseDBMapper <T extends BaseEntity> extends BaseMapper<T> {

}
