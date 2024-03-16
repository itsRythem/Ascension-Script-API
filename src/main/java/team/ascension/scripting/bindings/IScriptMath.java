package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Math")
public interface IScriptMath {

    @ScriptFunction(name = "fact", documentation = "Calculates the factorial from a set number of operations.")
    long fact(@ScriptParameter(name = "n", documentation = "The number of iterations to perform.") final int n);

    @ScriptFunction(name = "sum", documentation = "Calculates the sum of a data set.")
    float sum(final float[] data);

    @ScriptFunction(name = "max", documentation = "Finds the maximum value within a data set.")
    float max(final float[] data);

    @ScriptFunction(name = "min", documentation = "Finds the minimum value within a data set.")
    float min(final float[] data);

    @ScriptFunction(name = "avg", documentation = "Calculates the average of a data set.")
    float avg(final float[] data);

}
