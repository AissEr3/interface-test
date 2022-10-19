package test.use.common.annotation;

import org.junit.jupiter.api.Test;
import test.use.utils.RequestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Test
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceTest {
    String path() default "";

    RequestType type() default RequestType.GET;
}
