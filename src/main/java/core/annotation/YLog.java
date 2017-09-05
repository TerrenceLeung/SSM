package core.annotation;

import java.lang.annotation.*;

/**
 * Created by YannLeung on 2017/8/9.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface YLog {


    /**
     * 操作的模块名称
     */
    String moduleName() default "undefined";

    /**
     * 操作的模块名称
     */
    String desc() default "";
}
