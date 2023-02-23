package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *作用:使用接口幂等性的解决
 *
 *对象:controller类的方法
 * 要求：
 * 1.必须要有@RequestParam(name = "uuid") String uuid的参数
 * 2.必须要throws Exception
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotence {
}
