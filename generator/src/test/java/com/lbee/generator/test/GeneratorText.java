package com.lbee.generator.test;

import com.lbee.generator.GeneratorApplication;
import com.lbee.generator.service.GeneratorService;
import com.lbee.generator.util.Attributes;
import com.lbee.generator.util.ProjectContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GeneratorApplication.class})// 指定启动类
public class GeneratorText {

    @Autowired
    private GeneratorService generatorService;

    @Test
    public void generator() {
        ProjectContext.add(Attributes.AUTHOR, "Little bee");
        ProjectContext.add(Attributes.BASE_PACKAGE_NAME, "com.lbee.common");

//        generatorService.generator("honey-admin", "sys", "com.lbee.honey", "User", "sys_user");
//        generatorService.generator("honey-admin", "sys", "com.lbee.honey", "UserDetail", "sys_user_detail");
//        generatorService.generator("honey-admin", "sys", "com.lbee.honey", "Role", "sys_role");
//        generatorService.generator("honey-admin", "sys", "com.lbee.honey", "RelationUserRole", "sys_relation_user_role");

//        generatorService.generator("honey-auth2", "sys", "com.lbee.honey", "User", "sys_user");
//        generatorService.generator("honey-auth2", "sys", "com.lbee.honey", "Role", "sys_role");
        generatorService.generator("honey-admin", "core", "com.lbee.honey", "OperationLog", "sys_operation_log");
        generatorService.generator("honey-admin", "core", "com.lbee.honey", "OperationErrorLog", "sys_operation_error_log");


    }

}
