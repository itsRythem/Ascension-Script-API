package team.ascension.scripting.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ScriptClass {

    String name();

    String documentation() default "";

}
