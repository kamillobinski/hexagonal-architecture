package service;

import com.example.cash.machine.application.port.in.commands.DepositCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.application.service.DepositService;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.enums.EventType;
import com.example.cash.machine.domain.events.WithdrawalDepositEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    @Mock
    private LoadCashMachinePort loadCashMachinePort;

    @Mock
    private SaveCashMachinePort saveCashMachinePort;

    @InjectMocks
    private DepositService depositService;


    @Test
    void deposit_shouldUpdateCashMachineWhenDepositCommandIsValid() {
        BigDecimal amountToDeposit = BigDecimal.valueOf(200.00);
        DepositCommand depositCommand = new DepositCommand(amountToDeposit);
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(255.25)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.of(cashMachine));
        BigDecimal expectedBalance = BigDecimal.valueOf(255.25).add(amountToDeposit);

        WithdrawalDepositEvent event = depositService.deposit(depositCommand);

        assertEquals(1L, event.getCashMachine().getId());
        assertEquals(expectedBalance, event.getCashMachine().getBalance());
        assertEquals(EventType.DEPOSIT, event.getEventType());
        assertNotNull(event.getTimestamp());
    }

    @Test
    void deposit_shouldThrowCashMachineExceptionWhenNoCashMachineFound() {
        DepositCommand depositCommand = new DepositCommand(BigDecimal.valueOf(200.00));
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.empty());

        assertThrows(CashMachineException.class, () -> {
            depositService.deposit(depositCommand);
        });
    }

    @Test
    void deposit_shouldThrowCashMachineExceptionWhenAmountIsNegative() {
        DepositCommand depositCommand = new DepositCommand(BigDecimal.valueOf(-120.00));
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.empty());

        assertThrows(CashMachineException.class, () -> {
            depositService.deposit(depositCommand);
        });
    }

    @Test
    void deposit_shouldThrowCashMachineExceptionWhenAmountIsZero() {
        DepositCommand depositCommand = new DepositCommand(BigDecimal.ZERO);
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.empty());

        assertThrows(CashMachineException.class, () -> {
            depositService.deposit(depositCommand);
        });
    }
}
