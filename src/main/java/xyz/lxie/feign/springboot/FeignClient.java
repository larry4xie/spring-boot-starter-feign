package xyz.lxie.feign.springboot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FeignClient
 *
 * @author lianjin
 * @since 2016/11/3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {
    /**
     * An absolute URL or resolvable hostname.<br>
     * whether or not a url is provided. Can be specified as property key, eg: ${propertyKey}.
     */
    String value() default "";

    /**
     * Sets the <code>@Primary</code> value for the feign client.
     */
    boolean primary() default true;

    /**
     * Sets the <code>@Qualifier</code> value for the feign client.
     */
    String qualifier() default "";

    /**
     * Whether 404s should be decoded instead of throwing FeignExceptions
     */
    boolean decode404() default false;
}
