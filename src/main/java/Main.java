import com.google.gson.GsonBuilder;
import org.simpleyaml.configuration.file.YamlFile;
import team.ascension.scripting.ScriptAPI;
import team.ascension.scripting.ScriptController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Main {

    private static YamlFile config;

    public static void main(final String[] args) {
        final ScriptController controller = ScriptAPI.INSTANCE.getTemplateController();

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
