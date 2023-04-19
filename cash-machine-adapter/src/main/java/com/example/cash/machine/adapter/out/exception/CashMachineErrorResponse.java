package com.example.cash.machine.adapter.out.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CashMachineErrorResponse {
    private int statusCode;
    private String message;
}
