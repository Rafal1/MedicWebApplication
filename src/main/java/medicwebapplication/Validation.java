package medicwebapplication;

/**
 * @author Rafal Zawadzki
 */
public final class Validation {
    private static Character[] specialChars = new Character[]{'!', '#', '?', '<', '>', '=', '(', ')', '{', '}', '[', ']'};
    private static Integer lengthMinLimit = 2;
    private static Integer lengthMaxLimit = 80;

    public static boolean isSpecialChar(String phrase) {
        for (Character c : specialChars) {
            if (phrase.contains(c.toString())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isminimalLenght(String phrase) {
        if (phrase.length() >= lengthMinLimit) {
            return true;
        }
        return false;
    }

    public static boolean ismaximalLenght(String phrase) {
        if (phrase.length() <= lengthMaxLimit) {
            return true;
        }
        return false;
    }

    public static Boolean validate(String p) {
        if (
                ismaximalLenght(p) &&
                        isminimalLenght(p) &&
                        isSpecialChar(p)
                ) {
            return true;
        }
        return false;
    }

    //todo improvement: bad words - isNotAllowedWord(String phrase)
}