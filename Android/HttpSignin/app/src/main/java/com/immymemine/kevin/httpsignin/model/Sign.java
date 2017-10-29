package com.immymemine.kevin.httpsignin.model;

/**
 * Created by quf93 on 2017-10-25.
 */

public class Sign {
    String id;
    String pw;
    public Sign(String mEmail, String mPassword) {
        this.id = mEmail; this.pw = mPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
