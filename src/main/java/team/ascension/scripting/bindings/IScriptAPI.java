package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "API")
public interface IScriptAPI<ScriptHandle> {

    @ScriptFunction(name = "register", returnType = "ScriptHandle", documentation = "Registers the script and returns its handle.")
    ScriptHandle register(final String name, final String description, final String version, final String[] authors);

    @ScriptFunction(name = "getVersion", documentation = "Returns the current version of the scripting api.")
    int getVersion();

}
