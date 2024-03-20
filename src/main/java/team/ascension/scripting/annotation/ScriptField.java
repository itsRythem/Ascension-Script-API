package team.ascension.scripting.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ScriptField {

    String name();

    String documentation() default "";

}
