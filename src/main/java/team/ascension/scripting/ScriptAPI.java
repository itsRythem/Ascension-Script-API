package team.ascension.scripting;

import team.ascension.scripting.annotation.ScriptClass;
import team.ascension.scripting.bindings.*;

import java.util.Arrays;

public enum ScriptAPI {

    INSTANCE(1);

    private final Controller templateController = getController();
    private final int version;
    ScriptAPI(final int version) {
        this.version = version;

        this.templateController.register(IScriptMath.class);
        this.templateController.register(IScriptCloud.class);
        this.templateController.register(IScriptClient.class);
        this.templateController.register(IScriptTypes.class);
        this.templateController.register(IScriptRendering.class);
    }

    public int getVersion() {
        return this.version;
    }

    public Controller getController(final Class<?>... classes) {
        final Controller controller = this.getController();
        for (final Class<?> clazz : classes) {
            if (!clazz.isInterface()) {
                final Class<?>[] interfaces = clazz.getInterfaces();

                for (final Class<?> superClass : interfaces) {
                    if (superClass.isAnnotationPresent(ScriptClass.class)) {
                        controller.register(superClass, clazz);
                        break;
                    }
                }
            } else {
                controller.register(clazz);
            }
        }
        return controller;
    }

    public Controller getController() {
        return new Controller(this);
    }

    public Controller getTemplateController() {
        return this.templateController;
    }


}
