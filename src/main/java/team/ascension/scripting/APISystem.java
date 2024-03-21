package team.ascension.scripting;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.bindings.*;

public enum APISystem {

    INSTANCE;

    private final Controller templateController = getController();
    APISystem() {
        this.templateController.register(IScriptAPI.class);
        this.templateController.register(IScriptMath.class);
        this.templateController.register(IScriptCloud.class);
        this.templateController.register(IScriptClient.class);
        this.templateController.register(IScriptTypes.class);
        this.templateController.register(IScriptRendering.class);
    }

    public Controller getController() {
        return new Controller(this);
    }

    public Controller getTemplateController() {
        return this.templateController;
    }


}
