package team.ascension.scripting.js;

import org.openjdk.nashorn.api.scripting.AbstractJSObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class FunctionObject extends AbstractJSObject {

    private final Object classInstance;
    private final Method method;
    public FunctionObject(final Object classInstance, final Method method) {
        this.classInstance = classInstance;
        this.method = method;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(final Object obj, final Object... args) {
        try {
            return this.method.invoke(this.classInstance, args);
        } catch (final IllegalAccessException | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }
}