package com.app.viewdraghelpdemo;

/**
 * ElementType Created on 2016/7/19-16:11
 * Description:
 * Created by DongHao
 */
public enum ElementType {
    TYPE,
    FIELD,
    METHOD,
    PARAMETER,
    /**
     * Constructor declaration.
     */
    CONSTRUCTOR,
    /**
     * Local variable declaration.
     */
    LOCAL_VARIABLE,
    /**
     * Annotation type declaration.
     */
    ANNOTATION_TYPE,
    /**
     * Package declaration.
     */
    PACKAGE
}
