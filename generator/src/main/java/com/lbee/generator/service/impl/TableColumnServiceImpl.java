package com.lbee.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbee.generator.entity.TableColumn;
import com.lbee.generator.mapper.TableColumnMapper;
import com.lbee.generator.service.TableColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumn>
        implements TableColumnService {

    @Resource
    private TableColumnMapper tableColumnMapper;

    @Override
    public List<TableColumn> getTableColumnList(String tableName) {
        return tableColumnMapper.getTableColumnList(tableName);
    }

    @Override
    public String queryTableInfo(String tableName) {
        return tableColumnMapper.queryTableInfo(tableName);
    }
}
