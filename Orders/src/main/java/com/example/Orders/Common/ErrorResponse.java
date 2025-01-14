package com.example.Orders.Common;

import lombok.Getter;

@Getter
public class ErrorResponse implements OrderResponse{
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
