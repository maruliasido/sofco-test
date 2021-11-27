package com.maruli.sofco.service;

import com.maruli.sofco.object.RegisterUser;

public interface UserService {
    public void register(RegisterUser registerUser);
    public void registerAdmin(RegisterUser registerUser);
    public Boolean isAdmin(String username);
}
