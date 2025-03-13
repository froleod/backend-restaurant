package by.froleod.backend_restaurant.domain.menu.service;

import by.froleod.backend_restaurant.domain.menu.entity.Order;
import by.froleod.backend_restaurant.domain.menu.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    @Value("${MAIL_USERNAME}")
    private String from;

    @Value("${MAIL_PASSWORD}")
    private String password;

    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    private final JavaMailSender mailSender;

    public void send(String to, String subject, String body) {
        logger.info("Email from: {}", from);
        logger.info("Email password: {}", password != null ? password : "null"); // Маскируем пароль

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(from);

        mailSender.send(mailMessage);
    }

    public static String buildOrderEmailText(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Спасибо за заказ!\n\n");
        sb.append("Детали заказа:\n");
        sb.append("Номер заказа: ").append(order.getId()).append("\n");
        sb.append("Дата заказа: ").append(order.getOrderDate()).append("\n");
        sb.append("Статус: ").append(order.getStatus()).append("\n\n");
        sb.append("Товары:\n");
        for (OrderItem item : order.getItems()) {
            sb.append(item.getProduct().getName())
                    .append(" - ")
                    .append(item.getQuantity())
                    .append(" x ")
                    .append(item.getPrice())
                    .append(" = ")
                    .append(item.getQuantity() * item.getPrice())
                    .append("\n");
        }
        sb.append("\nИтого: ").append(order.getTotalPrice()).append("\n");
        return sb.toString();
    }

}