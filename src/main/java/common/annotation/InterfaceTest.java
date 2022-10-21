package test.use.common.annotation;

import utils.stat.RequestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// 暂时不考虑实现
@Deprecated
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceTest {
    String path() default "";

    RequestType type() default RequestType.GET;
}
