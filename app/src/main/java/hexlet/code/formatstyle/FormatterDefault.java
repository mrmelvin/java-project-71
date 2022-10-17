package hexlet.code.formatstyle;

class FormatterDefault {

    public static final int INDEX_FIRST_FILE_AVAILABLE_KEY = 0;
    public static final int INDEX_SECOND_FILE_AVAILABLE_KEY = 1;
    public static final int INDEX_FIRST_FILE_DATA = 2;
    public static final int INDEX_SECOND_FILE_DATA = 3;

    public static String presentNullToString(Object inputData) {
        return inputData == null ? "null" : inputData.toString();
    }

}

