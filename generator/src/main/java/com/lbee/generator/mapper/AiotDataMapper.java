package com.lbee.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbee.generator.entity.TableColumn;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface AiotDataMapper extends BaseMapper<TableColumn> {

    /**
     * 查询所有菜单id
     *
     * @return
     */
    List<String> findAiotSysmMenuIdList();

    /**
     * 查询可以给超级管理员分配的菜单及按钮
     *
     * @return
     */
    LinkedList<Map<String, String>> findAiotSysmMenuListAtSuperadmin();

}
