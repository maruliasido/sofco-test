package com.maruli.sofco.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private String name;
    private String email;
    private String username;
    private String password;
    private Long phoneNumber;
    private String city;
    private String street;
}
