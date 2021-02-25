package com.lbee.generator.service.impl;

import com.lbee.generator.entity.TableColumn;
import com.lbee.generator.service.GeneratorService;
import com.lbee.generator.service.TableColumnService;
import com.lbee.generator.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private TableColumnService tableColumnService;

    @Override
    public void generator(String project, String moduleName, String packageName, String className, String tableName) {
        List<TableColumn> tableColumnList = tableColumnService.getTableColumnList(tableName);
        if (tableColumnList.isEmpty()) {
            throw new RuntimeException("Table 不存在.");
        }
        String tableComment = tableColumnService.queryTableInfo(tableName);
        System.out.println("---------------===============");
        System.out.println(tableComment);


        GeneratorUtil.generatorCode(project,moduleName, tableName, packageName, className, tableComment, tableColumnList);
    }
}
