package com.zwh.social.api.datasource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;


/**
 * AOP的方式选择数据源
 *
 */
public class DynamicDataSourceProcessor implements BeanPostProcessor {
	
	/**
	 * 使用从库的方法集合
	 */
	private List<String> slaveMethodList=new ArrayList<String>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

        if(!(bean instanceof NameMatchTransactionAttributeSource)) {
            return bean;
        }
        
        try {
            NameMatchTransactionAttributeSource transactionAttributeSource=(NameMatchTransactionAttributeSource)bean;
            Field nameMapField=ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
            nameMapField.setAccessible(true);
            @SuppressWarnings("unchecked")
			Map<String, TransactionAttribute> nameMap =  (Map<String, TransactionAttribute>)nameMapField.get(transactionAttributeSource);
            for(Entry<String, TransactionAttribute> entry: nameMap.entrySet()) {
                RuleBasedTransactionAttribute attr=(RuleBasedTransactionAttribute)entry.getValue();

                // 仅对read-only的处理
                if(!attr.isReadOnly()) {
                    continue;
                }
                attr.setPropagationBehavior(Propagation.SUPPORTS.value());
                slaveMethodList.add(entry.getKey());
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
		return bean;
	}
	
	 /**
     * 切入处理的防范，选择数据源
     * @param pjp 环绕使用的是ProceedingJoinPoint 连接点对象，其他则可以用JoinPoint
     * @return
     * @throws Throwable
     */
    public Object setDataSourace(ProceedingJoinPoint point) throws Throwable {
    	String name = point.getSignature().getName();
    	if(isSlave(name)){
    		DynamicDataSource.setDataSource(DynamicDataSource.DataSourceType.slave);
    	}else{
    		DynamicDataSource.setDataSource(DynamicDataSource.DataSourceType.master);
    	}    	

        try {
            return point.proceed();
        } catch(Exception e) {
            throw e;
        }

    }
    /**
     * 判断是否是从库
     * @param methodName
     * @return
     */
    private Boolean isSlave(String methodName){
    	Boolean result = false;
    	for(String str : slaveMethodList){
    		if(PatternMatchUtils.simpleMatch(str, methodName)){
    			result = true;
    		}
    	}
    	return result;
    }

}
