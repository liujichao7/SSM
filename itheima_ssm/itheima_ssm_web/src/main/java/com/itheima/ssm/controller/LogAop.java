package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *  aop日志记录
 *    分析:(注解方式)
 *      1. 导入aop坐标
 *      2. 在该类对应的框架配置文件中开启包扫描和注解支持
 *      3. 配置切面类,切入点, 通知
 *          3.1 类上使用注解
*               @Component : 将该类加入到spring容器中
*               @Aspect : 定义切面类
 *          3.2 定义切入点
 *              @Pointcut("execution(* com.itheima.aop.anno.*.*(..))")
 *          3.3 定义通知
 *              前置和最终通知
 *              此处修改为环绕通知
 *
 */

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    //日志收集的内容
    //环绕前
    private Date visitTime;  //开始访问时间
    private Class clazz;    //访问的类
    private Method method;  //访问的方法

    //环绕后
    private String ip;              //访问IP
    private String url;             //访问资源url
    private String username;        //操作者用户名
    private Long executionTime;     //执行时长


    //定义切入点
    @Around("execution(* com.itheima.ssm.controller.*.*(..)) && args(pjp)")
    public Object arround(JoinPoint jp, ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            //前置
            visitTime = new Date(); //获取当前时间
            clazz = jp.getTarget().getClass(); //获取目标方法(访问的方法)对象
            String methodName = jp.getSignature().getName(); //获取访问的方法的名称

            Object[] args = jp.getArgs();  //获取访问的方法的参数
            if (args == null || args.length == 0){
                method = clazz.getMethod(methodName);  // 无参方法
            }else {
                Class[] classArgs = new Class[args.length]; // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
                for (int i = 0; i < args.length; i++) {
                    classArgs[i] = args[i].getClass();
                }
                method = clazz.getMethod(methodName,classArgs);  //有参方法
            }
            //最终需要的方法为 : 类的字符串 + 方法的字符串

            //================================
            //目标方法执行
            proceed = pjp.proceed();
            //========================================

            //后置
            if(clazz != SysLogController.class){   //排除访问日志类的记录
                RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class); //获取到类上的注解对象
                if (clazzAnnotation != null){
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class); //获取到方法上的注解对象
                    if (methodAnnotation != null){
                        //获取访问的url
                        url = clazzAnnotation.value()[0] + methodAnnotation.value()[0];

                        //获取操作者用户名
                        SecurityContext securityContext = SecurityContextHolder.getContext(); //也可通过request对象获取context
                        User user = (User) securityContext.getAuthentication().getPrincipal();  //该user为userDetail类型
                        username= user.getUsername();

                        //获取访问ip
                        //web.xml中监听器内添加request类  . 注入request  . 调用获取响应内容
                        ip = request.getRemoteAddr();

                        //访问时长
                        executionTime = new Date().getTime() - visitTime.getTime();

                        SysLog sysLog = new SysLog();
                        //添加属性
                        sysLog.setVisitTime(visitTime);
                        sysLog.setMethod("类名: " + clazz.getName() + "方法名: " + method.getName());
                        sysLog.setIp(ip);
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setExecutionTime(executionTime);

                        //调用service向数据库添加日志记录
                        sysLogService.save(sysLog);

                    }
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

}
