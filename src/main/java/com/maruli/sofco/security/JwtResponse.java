package com.maruli.sofco.security;


import java.io.Serializable;


public class JwtResponse implements Serializable {

    private String jwttoken;

    public JwtResponse(String jwttoken) {
            this.jwttoken = jwttoken;
    }

    public String getToken() {
            return this.jwttoken;
    }
}