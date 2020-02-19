package com.wangshao.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liutao
 * @create 2020-02-19-15:40
 */


public class MyImportSelector implements ImportSelector {

    /**
     * 返回值,就是导入到容器中的组件全类名
     * @param importingClassMetadata:当前标注@import注解类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        return new String[]{"com.wangshao.bean.Blue","com.wangshao.bean.Yellow"};
    }
}
