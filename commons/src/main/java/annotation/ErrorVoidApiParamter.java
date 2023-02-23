package annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * spring cloud中返回值为void的服务中的服务处理
 *位置:带有uuid的复杂对象参数或者json参数
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorVoidApiParamter {
}
