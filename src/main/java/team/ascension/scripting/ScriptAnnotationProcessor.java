package team.ascension.scripting;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptField;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public final class ScriptAnnotationProcessor {

    public ScriptAnnotationProcessor() {
    }

    public ScriptSet process(final Class<?> clazz) {
        if (clazz.isAnnotationPresent(ScriptClass.class)) {
            final ScriptPair<Class<?>, ScriptClass> scriptClass = new ScriptPair<>(clazz, clazz.getAnnotation(ScriptClass.class));
            final List<ScriptPair<Field, ScriptField>> fields = new ArrayList<>();
            final List<ScriptPair<ScriptPair<Method, ScriptFunction>, List<ScriptPair<Parameter, ScriptParameter>>>> functions = new ArrayList<>();

            for (final Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(ScriptField.class)) {
                    fields.add(new ScriptPair<>(field, field.getAnnotation(ScriptField.class)));
                }
            }

            for (final Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(ScriptFunction.class)) {
                    final List<ScriptPair<Parameter, ScriptParameter>> parameters = new ArrayList<>();
                    for (final Parameter parameter : method.getParameters()) {
                        if (parameter.isAnnotationPresent(ScriptParameter.class)) {
                            parameters.add(new ScriptPair<>(parameter, parameter.getAnnotation(ScriptParameter.class)));
                        } else {
                            parameters.add(new ScriptPair<>(parameter, new ScriptParameter() {

                                @Override
                                public Class<? extends Annotation> annotationType() {
                                    return null;
                                }

                                @Override
                                public String name() {
                                    return parameter.getName();
                                }

                                @Override
                                public String documentation() {
                                    return null;
                                }

                            }));
                        }
                    }

                    functions.add(new ScriptPair<>(new ScriptPair<>(method, method.getAnnotation(ScriptFunction.class)), parameters));
                }
            }

            return new ScriptSet(scriptClass, fields, functions);
        }

        return null;
    }

    public static class ScriptSet {

        public final ScriptPair<Class<?>, ScriptClass> clazz;
        public final List<ScriptPair<Field, ScriptField>> fields;
        public final List<ScriptPair<ScriptPair<Method, ScriptFunction>, List<ScriptPair<Parameter, ScriptParameter>>>> functions;
        public ScriptSet(final ScriptPair<Class<?>, ScriptClass> clazz, final List<ScriptPair<Field, ScriptField>> fields, final List<ScriptPair<ScriptPair<Method, ScriptFunction>, List<ScriptPair<Parameter, ScriptParameter>>>> functions) {
            this.clazz = clazz;
            this.fields = fields;
            this.functions = functions;
        }

    }

    public static class ScriptPair<T, E> {

        public T key;
        public E value;
        public ScriptPair(final T key, final E value) {
            this.key = key;
            this.value = value;
        }

    }

}
