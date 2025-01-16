package com.app.book.notification;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
public class Notification {
    private  NotificationStatus status;
    private  String message;
    private  String bookTitle;

}
