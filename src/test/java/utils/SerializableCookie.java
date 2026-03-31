package utils;

import org.openqa.selenium.Cookie;

import java.util.Date;

public class SerializableCookie {
    public String name;
    public String value;
    public String domain;
    public String path;
    public Date expiry;
    public boolean isSecure;
    public boolean isHttpOnly;

    public SerializableCookie(){}

    public SerializableCookie(Cookie cookie) {
        this.value = cookie.getValue();
        this.domain = cookie.getDomain();
        this.isHttpOnly = cookie.isHttpOnly();
        this.isSecure = cookie.isSecure();
        this.expiry = cookie.getExpiry();
        this.path = cookie.getPath();
        this.name = cookie.getName();
    }

    public Cookie toCookie(){
        return new Cookie(
                name,
                value,
                domain,
                path,
                expiry,
                isSecure,
                isHttpOnly
        );
    }
}
