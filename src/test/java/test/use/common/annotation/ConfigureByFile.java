package test.use.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  通过文件指定配置文件的内容，配置测试信息（yaml文件指定）
 *  配置文件书写信息示例如下，如果不指定则使用默认指定，如果默认也没有，则不进行设置
 *  --------------------------------------------------------------------
 * test-config:
 *   base:
 *     baseURI: http://localhost
 *     port: 8000
 *     default-contentType: application/json
 *   interface:
 *     basePath: /test
 *     requestType: get
 *   headers:
 *     header1: header1
 *     header2: header2
 *     .........
 *   cookies:
 *     cookie1: cookie1
 *     cookie2: cookie2
 *     .........
 * ----------------------------------------------------------------------
 */
@Deprecated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigureByFile {
    // 指定配置文件路径，格式为yaml格式
    String path();

    // yaml文件中哪一个
    String name();
}
