package com.scaler.springsecurityandjwttokens.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String userName;
    private String password;
}
