package common.extension;

import org.junit.jupiter.api.extension.*;
import common.annotation.InterfaceTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @ClassName CustomAnnotationParameterResolver
 * @Author AissEr
 * @Date 2022/10/20 16:26
 * @Version 1.0
 * @Description TODO
 **/
// 暂时不考虑实现
@Deprecated
public class CustomAnnotationParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // We invoke parameterContext.isAnnotated() instead of parameterContext.getParameter().isAnnotationPresent()
        // in order to verify support for the convenience method in the ParameterContext API.
        return parameterContext.isAnnotated(InterfaceTest.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Optional<Method> testMethod = extensionContext.getTestMethod();
        Method method = testMethod.get();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        return null;
    }

}