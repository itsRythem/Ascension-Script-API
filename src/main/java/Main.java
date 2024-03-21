import com.google.gson.GsonBuilder;
import org.openjdk.nashorn.api.scripting.AbstractJSObject;
import org.simpleyaml.configuration.file.YamlFile;
import team.Test;
import team.ascension.scripting.APISystem;
import team.ascension.scripting.Controller;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Main {

    private static YamlFile config;

    public static void main(final String[] args) {
        final Controller controller = APISystem.INSTANCE.getTemplateController();

        if (args.length < 1) {
            throw new RuntimeException("Config file not found.");
        } else {
            config = new YamlFile(new File(args[0]));

            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(config.getString("output-file", "./output.json")))){
                writer.write(new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create().toJson(controller.generateDocumentation()));
                writer.flush();
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
