package team.ascension.scripting.js;

import org.openjdk.nashorn.api.scripting.AbstractJSObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class FieldObject extends AbstractJSObject {

    private final Object classInstance;
    private final Field field;
    public FieldObject(final Object classInstance, final Field field) {
        this.classInstance = classInstance;
        this.field = field;
    }

    @Override
    public boolean isFunction() {
        return false;
    }

    @Override
    public Object call(final Object obj, final Object... args) {
        try {
            return this.field.get(this.classInstance);
        } catch (final IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }
}