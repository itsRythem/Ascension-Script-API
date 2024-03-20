package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Types")
public interface IScriptTypes<PositionVector, RotationVector, AlignmentVector, Vector2f, Vector3f, Vector4f, Matrix4f, Color, Category> {

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

}
