package team.ascension.scripting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import team.ascension.scripting.annotation.ScriptField;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {

    private static final AnnotationProcessor PROCESSOR = new AnnotationProcessor();
    private final Map<Class<?>, AnnotationProcessor.BindingRegistry> bindings = new ConcurrentHashMap<>();

    public final APISystem api;
    public Controller(final APISystem api) {
        this.api = api;
    }

    public void register(final Class<?> clazz) {
        this.bindings.put(clazz, PROCESSOR.process(clazz, clazz));
    }

    public void register(final Class<?> superClass, final Class<?> subClass) {
        this.bindings.put(superClass, PROCESSOR.process(superClass, subClass));
    }

    public void unregister(final Class<?> clazz) {
        this.bindings.remove(clazz);
    }

    public Bindings generateBindings(final ScriptEngine engine) {
        final Bindings bindings = engine.createBindings();

        for (final AnnotationProcessor.BindingRegistry registry : this.bindings.values()) {
            final String typeName = registry.clazz.value.name();

            for (final AnnotationProcessor.Pair<Field, ScriptField> field : registry.fields) {
                bindings.put(typeName + "." + field.value.name(), field.key);
            }

            for (final AnnotationProcessor.Pair<AnnotationProcessor.Pair<Method, ScriptFunction>, List<AnnotationProcessor.Pair<Parameter, ScriptParameter>>> function : registry.functions) {
                bindings.put(typeName + "." + function.key.value.name(), function.key);
            }
        }

        return bindings;
    }

    public JsonObject generateDocumentation() {
        final JsonObject object = new JsonObject();
        final JsonArray classes = new JsonArray();

        for (final AnnotationProcessor.BindingRegistry registry : this.bindings.values()) {
            final JsonObject classObject = new JsonObject();
            classObject.addProperty("name", registry.clazz.value.name());
            final String classDocumentation = registry.clazz.value.documentation();
            if (classDocumentation != null && classDocumentation.length() > 0) {
                classObject.addProperty("documentation", classDocumentation);
            }

            final JsonArray fields = new JsonArray();
            for (final AnnotationProcessor.Pair<Field, ScriptField> field : registry.fields) {
                final JsonObject fieldObject = new JsonObject();

                fieldObject.addProperty("name", field.value.name());
                final String fieldDocumentation = field.value.documentation();
                if (fieldDocumentation != null && fieldDocumentation.length() > 0) {
                    fieldObject.addProperty("documentation", fieldDocumentation);
                }
                fieldObject.addProperty("type", field.key.getType().getSimpleName());

                fields.add(fieldObject);
            }
            classObject.add("fields", fields);

            final JsonArray functions = new JsonArray();
            for (final AnnotationProcessor.Pair<AnnotationProcessor.Pair<Method, ScriptFunction>, List<AnnotationProcessor.Pair<Parameter, ScriptParameter>>> function : registry.functions) {
                final JsonObject functionObject = new JsonObject();
                functionObject.addProperty("name", function.key.value.name());

                final String functionDocumentation = function.key.value.documentation();
                if (functionDocumentation != null && functionDocumentation.length() > 0) {
                    functionObject.addProperty("documentation", functionDocumentation);
                }

                final String returnType = function.key.value.returnType();
                functionObject.addProperty("return_type", (returnType != null && returnType.length() > 0 ? returnType : function.key.key.getReturnType().getSimpleName()));

                final JsonArray parameters = new JsonArray();
                for (final AnnotationProcessor.Pair<Parameter, ScriptParameter> parameter : function.value) {
                    final JsonObject parameterObject = new JsonObject();
                    parameterObject.addProperty("name", parameter.value.name());
                    final String parameterDocumentation = parameter.value.documentation();
                    if (parameterDocumentation != null && parameterDocumentation.length() > 0) {
                        parameterObject.addProperty("documentation", parameterDocumentation);
                    }

                    final String type = parameter.value.type();
                    parameterObject.addProperty("type", (type != null && type.length() > 0 ? type : parameter.key.getType().getSimpleName()));
                    parameters.add(parameterObject);
                }
                functionObject.add("parameters", parameters);

                functions.add(functionObject);
            }
            classObject.add("functions", functions);

            classes.add(classObject);
        }

        object.add("classes", classes);

        return object;
    }

}
