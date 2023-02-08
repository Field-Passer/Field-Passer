package com.example.fieldpasserbe.member.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MailDTO {
    private String address;
    private String title;
    private String message;
}
