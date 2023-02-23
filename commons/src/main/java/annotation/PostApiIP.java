package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用：用于write-api的post请求的参数的幂等性验证，防止post请求参数过多报错,如(user,file,uuid)
 * 位置：带有uuid的参数
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostApiIP {
}
