package com.example.cash.machine.application.port.in.commands;

import java.math.BigDecimal;

public record WithdrawalCommand(BigDecimal amount) {

}
