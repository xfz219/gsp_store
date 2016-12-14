package com.puhui.app.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

/**
 * @author: dongchen
 * @ClassName: BeanMapper
 * @Description: 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现: 1. 持有Mapper的单例. 2.
 *               返回值类型转换. 3. 批量转换Collection中的所有对象. 4.
 *               区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * @date 2015年12月28日 下午1:45:46
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapper() {
    }

    /**
     * @auth:dongchen
     * @Title: map
     * @Description:基于Dozer转换对象的类型
     * @param @param source
     * @param @param destinationClass
     * @param @return 设定文件
     * @return T 返回类型
     * @throws
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * @auth:dongchen
     * @Title: mapList
     * @Description: 基于Dozer转换Collection中对象的类型.
     * @param @param sourceList
     * @param @param destinationClass
     * @param @return 设定文件
     * @return List<T> 返回类型
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * @auth:dongchen
     * @Title: copy
     * @Description:基于Dozer将对象A的值拷贝到对象B中
     * @param @param source
     * @param @param destinationObject 设定文件
     * @return void 返回类型
     * @throws
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}