package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Types")
public interface IScriptTypes<PositionVector, RotationVector, Vector2f, Vector3f, Vector4f, Matrix4f, Color, Category, Alignment> {

    @ScriptFunction(name = "PositionVector", returnType = "PositionVector", documentation = "Constructs a new position vector.")
    PositionVector PositionVector(final float x, final float y, final float z);

    @ScriptFunction(name = "PositionVector", returnType = "PositionVector", documentation = "Constructs a new position vector.")
    PositionVector PositionVector(final float x);

    @ScriptFunction(name = "PositionVector", returnType = "PositionVector", documentation = "Constructs a new position vector.")
    PositionVector PositionVector();

    @ScriptFunction(name = "RotationVector", returnType = "RotationVector", documentation = "Constructs a new rotation vector.")
    RotationVector RotationVector(final float yaw, final float pitch);

    @ScriptFunction(name = "RotationVector", returnType = "RotationVector", documentation = "Constructs a new rotation vector.")
    RotationVector RotationVector(final float x);

    @ScriptFunction(name = "RotationVector", returnType = "RotationVector", documentation = "Constructs a new rotation vector.")
    RotationVector RotationVector();

    @ScriptFunction(name = "Vector2f", returnType = "Vector2f", documentation = "Constructs a new Vector2f.")
    Vector2f Vector2f(final float x, final float y);

    @ScriptFunction(name = "Vector2f", returnType = "Vector2f", documentation = "Constructs a new Vector2f.")
    Vector2f Vector2f(final float x);

    @ScriptFunction(name = "Vector2f", returnType = "Vector2f", documentation = "Constructs a new Vector2f.")
    Vector2f Vector2f();

    @ScriptFunction(name = "Vector3f", returnType = "Vector3f", documentation = "Constructs a new Vector3f.")
    Vector3f Vector3f(final float x, final float y, final float z);

    @ScriptFunction(name = "Vector3f", returnType = "Vector3f", documentation = "Constructs a new Vector3f.")
    Vector3f Vector3f(final float x);

    @ScriptFunction(name = "Vector3f", returnType = "Vector3f", documentation = "Constructs a new Vector3f.")
    Vector3f Vector3f();

    @ScriptFunction(name = "Vector4f", returnType = "Vector4f", documentation = "Constructs a new Vector4f.")
    Vector4f Vector4f(final float x, final float y, final float z, final float w);

    @ScriptFunction(name = "Vector4f", returnType = "Vector4f", documentation = "Constructs a new Vector4f.")
    Vector4f Vector4f(final float x);

    @ScriptFunction(name = "Vector4f", returnType = "Vector4f", documentation = "Constructs a new Vector4f.")
    Vector4f Vector4f();

}
