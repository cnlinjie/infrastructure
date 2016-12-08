package com.github.cnlinjie.infrastructure.util.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-11-24
 */
@Target ({METHOD, FIELD})
@Retention (RUNTIME)
public @interface NotExportField {
}
