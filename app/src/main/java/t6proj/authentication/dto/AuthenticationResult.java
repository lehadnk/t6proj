package t6proj.authentication.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationResult {
    public final Boolean isSuccess;
    public final String authenticationToken;
}
