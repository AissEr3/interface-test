package common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  配置接口测试内容，方便测试
 */
// 暂时不考虑实现
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceTestConfigure {
    // 设置默认路径
    String basePath() default "";

    String[] headers() default {};

    String[] cookies() default {};

    // 是否需要加上该默认Headers，默认Header需要自己配置
    boolean needDefaultHeaders() default true;

    // 是否需要加上该默认Cookies，默认Cookies需要自己配置
    boolean needDefaultCookies() default true;
}
