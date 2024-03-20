package team.ascension.scripting;

import team.ascension.scripting.bindings.IScriptClient;
import team.ascension.scripting.bindings.IScriptCloud;
import team.ascension.scripting.bindings.IScriptMath;

public enum ScriptAPI {

    INSTANCE(1);

    private final Controller templateController = getController();
    private final int version;
    ScriptAPI(final int version) {
        this.version = version;

        this.templateController.register(IScriptMath.class);
        this.templateController.register(IScriptCloud.class);
        this.templateController.register(IScriptClient.class);
    }

    public int getVersion() {
        return this.version;
    }

    public Controller getController() {
        return new Controller(this);
    }

    public Controller getTemplateController() {
        return this.templateController;
    }


}
