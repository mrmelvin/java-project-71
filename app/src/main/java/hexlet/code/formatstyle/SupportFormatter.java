package hexlet.code.formatstyle;

class SupportFormatter {

    public static final String ADDED = "added";
    public static final String DELETED = "deleted";
    public static final String CHANGED = "changed";
    public static final String NOCHANGED = "noChanged";

    public static String presentNullToString(Object inputData) {
        return inputData == null ? "null" : inputData.toString();
    }

}

