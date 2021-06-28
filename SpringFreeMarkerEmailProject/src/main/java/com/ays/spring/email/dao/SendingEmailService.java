package com.ays.spring.email.dao;

import java.io.IOException;

import javax.mail.MessagingException;

import com.ays.spring.email.entities.MailModel;

import freemarker.template.TemplateException;

public interface SendingEmailService {

    void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException;
}
