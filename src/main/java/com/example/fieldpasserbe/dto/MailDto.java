package com.example.fieldpasserbe.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MailDto {
    private String address;
    private String title;
    private String message;
}
