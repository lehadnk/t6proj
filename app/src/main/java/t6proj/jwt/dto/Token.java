package t6proj.jwt.dto;

import lombok.RequiredArgsConstructor;

import java.util.Date;

//@RequiredArgsConstructor
public class Token {
    public Integer userId;
    public Date expirationDate;
}
