package com.yao.dynamicgeneratedb.aop;

import com.yao.dynamicgeneratedb.annotation.DataSource;
import com.yao.dynamicgeneratedb.dynamicdatasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/8 10:49
 */
@Component
@Aspect
@Order(-1)
@Slf4j
public class DynamicDataSourceAOP {

    @Pointcut("@within(com.yao.dynamicgeneratedb.annotation.DataSource) || @annotation(com.yao.dynamicgeneratedb.annotation.DataSource)")
    public void pointCut(){
    }

    //@Pointcut("within(com.yao.dynamicgeneratedb.*.service.impl))")
    @Pointcut("within(com.yao.dynamicgeneratedb.*.impl.*))")
    //@Pointcut("execution(* com.yao.dynamicgeneratedb..*.*(..)))")
    public void pointCut1(){}

    //@Before("pointCut1() && (@annotation(ds))")
    @Before("pointCut1()")
    public void changeDataSource(JoinPoint point) throws Throwable {
        try {
            Class clazz = point.getSignature().getDeclaringType();
            boolean isDataSource = AnnotationUtils.isAnnotationDeclaredLocally(DataSource.class, clazz);
            if(isDataSource){
                return;
            }
            //判断方法上有没有@DataSource注解，如果方法上也有，则放弃切换数据源
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName,parameterTypes);
            if(method.getAnnotation(DataSource.class)!=null){
                return;
            }

            /**
             * 1.从redis中取出userkey
             * Boolean useKey = redisTemplate.hasKey(customerNo);
             * if(!userKey){
             *     //抛出异常，redis没有该租户信息
             *     throw new XXXException("redis中未找到租户的唯一key");
             * }
             * String value = DynamicDataSource.getDataSource(userKey);
             * if(StringUtils.isEmpty(value)){
             *     //没有初始化数据源
             *     //去初始化数据源
             *     //切换数据源
             *     return;
             * }
             * //直接切换数据源
             * DynamicDataSource.setDataSource("userKey");
             *
             *
             */

            DynamicDataSource.setDataSource("userKey");
        } catch (Exception e) {
            log.error("么有[{}]数据源，鹅用默认数据源哈 > {}","123", point.getSignature());
        }
    }
}
