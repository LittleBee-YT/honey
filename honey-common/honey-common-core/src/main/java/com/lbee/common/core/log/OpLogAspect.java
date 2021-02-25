package com.lbee.common.core.log;

import com.alibaba.fastjson.JSONObject;
import com.lbee.common.core.entity.OperationErrorLog;
import com.lbee.common.core.entity.OperationLog;
import com.lbee.common.core.service.OperationErrorLogService;
import com.lbee.common.core.service.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OpLogAspect {

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private OperationErrorLogService operationErrorLogService;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.lbee.common.core.log.OpLog)")
    public void opLogPointcutCut() {
    }

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.*.*.*.controller..*.*(..))")
    public void opExceptionLogPointcutCut() {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "opLogPointcutCut()", returning = "keys")
    public void saveOpLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperationLog operationLog = new OperationLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OpLog opLog = method.getAnnotation(OpLog.class);
            if (opLog != null) {
                String module = opLog.module();
                String type = opLog.type();
                String desc = opLog.desc();
                operationLog.setOpModule(module); // 操作模块
                operationLog.setOpType(type); // 操作类型
                operationLog.setOpDesc(desc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            operationLog.setMethod(methodName); // 请求方法
            operationLog.setUri(request.getRequestURI()); // 请求URI
            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONObject.toJSONString(rtnMap);
            operationLog.setRequestParam(params); // 请求参数
            operationLog.setResultBody(JSONObject.toJSONString(keys)); // 返回结果
//            operlog.setOperUserId(UserShiroUtil.getCurrentUserLoginName()); // 请求用户ID
//            operlog.setOperUserName(UserShiroUtil.getCurrentUserName()); // 请求用户名称
//            operlog.setOperIp(IPUtil.getRemortIP(request)); // 请求IP
//            operlog.setOperCreateTime(new Date()); // 创建时间
//            operlog.setOperVer(operVer); // 操作版本
//            operationLogService.insert(operlog);
            operationLogService.save(operationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "opExceptionLogPointcutCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperationErrorLog errorLog = new OperationErrorLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OpLog opLog = method.getAnnotation(OpLog.class);
            if (opLog != null) {
                String module = opLog.module();
                String type = opLog.type();
                String desc = opLog.desc();
                errorLog.setOpModule(module); // 操作模块
                errorLog.setOpType(type); // 操作类型
                errorLog.setOpDesc(desc); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            errorLog.setUri(request.getRequestURI()); // 请求URI
            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONObject.toJSONString(rtnMap);
            errorLog.setRequestParam(params); // 请求参数
            errorLog.setMethod(methodName); // 请求方法名
//            errorLog.setExcName(e.getClass().getName()); // 异常名称
            errorLog.setErrorMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
//            errorLog.setOperUserId(UserShiroUtil.getCurrentUserLoginName()); // 操作员ID
//            errorLog.setOperUserName(UserShiroUtil.getCurrentUserName()); // 操作员名称
//            errorLog.setOperUri(request.getRequestURI()); // 操作URI
//            errorLog.setOperIp(IPUtil.getRemortIP(request)); // 操作员IP
//            errorLog.setOperVer(operVer); // 操作版本号
//            errorLog.setOperCreateTime(new Date()); // 发生异常时间

            operationErrorLogService.save(errorLog);

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        System.out.println("==================================");
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

}
