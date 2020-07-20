package com.example.evcollect;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class config {
static String cookies;















    private static CookieManager lastCookies = new CookieManager();
    private static final String COOKIES_HEADER = "Set-Cookie";

    public static CookieManager getLastCookies() {
        return lastCookies;
    }

    public static String urlEncode(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
                url.getQuery(), url.getRef()).toASCIIString();
    }
    private static void storeCookies(URLConnection urlConnection) {
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

        if (cookiesHeader == null)
            return;

        lastCookies = new CookieManager();
        for (String cookie : cookiesHeader)
            lastCookies.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
    }



    private static void applyCookies(URLConnection urlConnection) {
        if (lastCookies.getCookieStore().getCookies().size() < 1)
            return;

        StringBuilder cookieHeader = new StringBuilder();
        for (HttpCookie cookie : lastCookies.getCookieStore().getCookies())
            cookieHeader.append(cookie).append(";");
        cookieHeader.deleteCharAt(cookieHeader.length() - 1);

        urlConnection.setRequestProperty("Cookie", cookieHeader.toString());
    }



    public static String get(String requestUrl, CookieManager cookies) throws Exception {
        URL url = new URL(urlEncode(requestUrl));
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        if (cookies != null)
            applyCookies(connection);

        connection.setConnectTimeout(8 * 1000);
        //connection.setRequestProperty("User-Agent", FrameworkConstants.FRAMEWORK_NAME);
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String text;
        StringBuilder result = new StringBuilder();
        while ((text = in.readLine()) != null)
            result.append(text);

        in.close();

        storeCookies(connection);
        return result.toString();

    }


}
