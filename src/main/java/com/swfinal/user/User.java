package com.swfinal.user;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class User {
    private int    userSeq;
    private String userId;
    private String userNm;
    private String    userEmail;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
