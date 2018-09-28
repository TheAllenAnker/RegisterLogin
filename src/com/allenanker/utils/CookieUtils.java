package com.allenanker.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie findCookie(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
