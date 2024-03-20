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

public final class AnnotationProcessor {

    public AnnotationProcessor() {
    }

    public BindingRegistry process(final Class<?> clazz) {
        if (clazz.isAnnotationPresent(ScriptClass.class)) {
            final Pair<Class<?>, ScriptClass> scriptClass = new Pair<>(clazz, clazz.getAnnotation(ScriptClass.class));
            final List<Pair<Field, ScriptField>> fields = new ArrayList<>();
            final List<Pair<Pair<Method, ScriptFunction>, List<Pair<Parameter, ScriptParameter>>>> functions = new ArrayList<>();

            for (final Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(ScriptField.class)) {
                    fields.add(new Pair<>(field, field.getAnnotation(ScriptField.class)));
                }
            }

            for (final Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(ScriptFunction.class)) {
                    final List<Pair<Parameter, ScriptParameter>> parameters = new ArrayList<>();
                    for (final Parameter parameter : method.getParameters()) {
                        if (parameter.isAnnotationPresent(ScriptParameter.class)) {
                            parameters.add(new Pair<>(parameter, parameter.getAnnotation(ScriptParameter.class)));
                        } else {
                            parameters.add(new Pair<>(parameter, new ScriptParameter() {
                                @Override public Class<? extends Annotation> annotationType() {return null;}
                                @Override public String name() {return parameter.getName();}
                                @Override public String type() {return null;}
                                @Override public String documentation() {
                                    return null;
                                }
                            }));
                        }
                    }

                    functions.add(new Pair<>(new Pair<>(method, method.getAnnotation(ScriptFunction.class)), parameters));
                }
            }

            return new BindingRegistry(scriptClass, fields, functions);
        }

        return null;
    }

    public static class BindingRegistry {

        public final Pair<Class<?>, ScriptClass> clazz;
        public final List<Pair<Field, ScriptField>> fields;
        public final List<Pair<Pair<Method, ScriptFunction>, List<Pair<Parameter, ScriptParameter>>>> functions;
        public BindingRegistry(final Pair<Class<?>, ScriptClass> clazz, final List<Pair<Field, ScriptField>> fields, final List<Pair<Pair<Method, ScriptFunction>, List<Pair<Parameter, ScriptParameter>>>> functions) {
            this.clazz = clazz;
            this.fields = fields;
            this.functions = functions;
        }

    }

    public static class Pair<T, E> {

        public T key;
        public E value;
        public Pair(final T key, final E value) {
            this.key = key;
            this.value = value;
        }

    }

}
