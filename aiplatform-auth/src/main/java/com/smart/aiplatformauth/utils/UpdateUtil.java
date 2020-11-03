package com.smart.aiplatformauth.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @desc: 数据更新工具类   解决问题：将空值的属性从目标实体类中复制到源实体类中
 * @author: chengjz
 */
public class UpdateUtil {
  /**
   * 将空值的属性从目标实体类中复制到源实体类中
   * @param src : 从数据库根据id查询出来的目标对象
   * @param target :要将属性中的空值覆盖的对象(源实体类)
   */
  public static void copyNonNullProperties(Object src,Object target){
    BeanUtils.copyProperties(src,target,getNullProperties(target));
  }

  /**
   * 将为空的properties给找出来,然后返回出来
   * @param src
   * @return
   */
  private static String[] getNullProperties(Object src){
    BeanWrapper srcBean=new BeanWrapperImpl(src);
    PropertyDescriptor[] pds=srcBean.getPropertyDescriptors();
    Set<String> emptyName=new HashSet<>();
    for(PropertyDescriptor p:pds){
      Object srcValue=srcBean.getPropertyValue(p.getName());
      if(srcValue!=null) emptyName.add(p.getName());
    }
    String[] result=new String[emptyName.size()];
    return emptyName.toArray(result);
  }
}
