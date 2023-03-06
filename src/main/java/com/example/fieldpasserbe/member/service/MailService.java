package com.example.fieldpasserbe.member.service;

import com.example.fieldpasserbe.member.vo.MailVo;

public interface MailService {

    void sendMail(MailVo mailVo);
    MailVo createMail(String tmpPassword, String memberEmail);
}
