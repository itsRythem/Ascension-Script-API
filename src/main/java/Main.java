import team.ascension.scripting.ScriptAPI;
import team.ascension.scripting.ScriptController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Main {

    private static Map<String, String> CACHE;

    public static void main(final String[] args) {
        CACHE = parse(args);

        final ScriptController controller = ScriptAPI.INSTANCE.getTemplateController();
        if (getOrDefault("genDocs", false)) {
            System.out.println(controller.generateDocumentation());
        }
    }

    private static String getOrDefault(final String key, final String defaultValue) {
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }

        return defaultValue;
    }

    private static boolean getOrDefault(final String key, final boolean defaultValue) {
        if (CACHE.containsKey(key)) {
            if (CACHE.get(key) == null) {
                return true;
            }

            try {
                return Boolean.parseBoolean(CACHE.get(key));
            } catch (final Exception ignored) {}
        }

        return defaultValue;
    }

    private static Map<String, String> parse(final String[] args) {
        final Map<String, String> parameters = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            final String arg = args[i];

            if (arg.startsWith("--")) {
                final String key = arg.substring(2);

                String value = null;
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    value = args[i + 1];
                    i++;
                }

                parameters.put(key, value);
            }
        }

        return parameters;
    }

}
