package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Rendering")
public interface IScriptRendering {

    @ScriptFunction(name = "fact", documentation = "Calculates the factorial from a set number of operations.")
    void fact(@ScriptParameter(name = "n") final int n);

}
