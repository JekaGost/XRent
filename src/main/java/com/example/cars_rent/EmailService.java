package com.example.cars_rent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static final String SMTP_HOST = "smtp.gmail.com"; // SMTP сервер Gmail
    private static final String SMTP_PORT = "587";            // Порт SMTP
    private static final String SENDER_EMAIL = "lcarlrentl@gmail.com"; // Ваш email
    private static final String SENDER_PASSWORD = "jylj cshr yyle soyf"; // Ваш пароль

    public static void sendVerificationCode(String recipientEmail, String verificationCode) throws MessagingException {
        // Настройка свойств почтового сервера
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        // Создание сессии с аутентификацией
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Rezervasyon için onay kodu");
        message.setText("Onay kodunuz: " + verificationCode);

        // Отправка сообщения
        Transport.send(message);
    }
}
