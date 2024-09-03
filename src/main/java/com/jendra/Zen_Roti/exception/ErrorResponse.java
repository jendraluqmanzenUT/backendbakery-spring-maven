package com.jendra.Zen_Roti.exception;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String details;


}
