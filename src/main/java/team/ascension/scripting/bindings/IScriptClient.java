package team.ascension.scripting.bindings;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.annotation.ScriptFunction;
import team.ascension.scripting.annotation.ScriptParameter;

@ScriptClass(name = "Client")
public interface IScriptClient {

    @ScriptFunction(name = "log", documentation = "Prints a client side chat message.")
    void log(@ScriptParameter(name = "message") final String message);

    @ScriptFunction(name = "getName", documentation = "Returns the client display name.")
    String getName();

    @ScriptFunction(name = "getVersion", documentation = "Returns the client build number.")
    String getVersion();

    @ScriptFunction(name = "getTime", documentation = "Returns the current time in nanoseconds.")
    long getTime();

    @ScriptFunction(name = "getTimeSinceLaunch", documentation = "Returns the time since the client launched in milliseconds.")
    long getTimeSinceLaunch();

}
