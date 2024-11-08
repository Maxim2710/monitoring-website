package monitoringWebsite.monitoringWebsite.util;

import java.util.regex.Pattern;

public class UrlValidator {
    private static final Pattern URL_PATTERN = Pattern.compile("^(https?://).*");

    public static boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }
}
