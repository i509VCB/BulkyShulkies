package me.i509.fabric.cursedshulkerboxes.config;

public class RecipeParseException extends Exception {

    private final String configNodePath;
    private final String reason;

    public RecipeParseException(String configNodePath, String reason) {
        this.configNodePath = configNodePath;
        this.reason = reason;
    }

    public RecipeParseException(String s) {
        this.reason = s;
        this.configNodePath = null;
    }

    public String getPath() {
        return configNodePath;
    }

    public String getReason() {
        return reason;
    }
}
