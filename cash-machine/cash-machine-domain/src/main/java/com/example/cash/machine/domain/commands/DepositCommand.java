package com.example.cash.machine.domain.commands;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class DepositCommand {

  private final BigDecimal amount;
}
