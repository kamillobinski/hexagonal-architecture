package service;

import com.example.cash.machine.application.port.in.commands.WithdrawalCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.port.out.SaveCashMachinePort;
import com.example.cash.machine.application.service.WithdrawalService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WithdrawalServiceTest {

    @Mock
    private LoadCashMachinePort loadCashMachinePort;

    @Mock
    private SaveCashMachinePort saveCashMachinePort;

    @InjectMocks
    private WithdrawalService withdrawalService;

    @Test
    void withdrawal_shouldUpdateCashMachineWhenWithdrawalCommandIsValid() {
        BigDecimal amountToWithdraw = BigDecimal.valueOf(100);
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(amountToWithdraw);
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(200.00)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.of(cashMachine));
        BigDecimal expectedBalance = BigDecimal.valueOf(200.00).subtract(amountToWithdraw);

        WithdrawalDepositEvent event = withdrawalService.withdrawal(withdrawalCommand);

        assertEquals(1L, event.getCashMachine().getId());
        assertEquals(expectedBalance, event.getCashMachine().getBalance());
        assertEquals(EventType.WITHDRAWAL, event.getEventType());
        assertNotNull(event.getTimestamp());
    }

    @Test
    void withdrawal_shouldThrowCashMachineExceptionWhenNoCashMachineFound() {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(BigDecimal.valueOf(200.00));
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(CashMachineException.class, () -> {
            withdrawalService.withdrawal(withdrawalCommand);
        });
    }

    @Test
    void withdrawal_shouldThrowCashMachineExceptionWhenAmountIsGreaterThanBalance() {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(BigDecimal.valueOf(500.00));
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(100.00)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.of(cashMachine));

        assertThrows(CashMachineException.class, () -> {
            withdrawalService.withdrawal(withdrawalCommand);
        });
    }

    @Test
    void withdrawal_shouldThrowCashMachineExceptionWhenAmountIsZero() {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(BigDecimal.ZERO);
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(100.00)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.of(cashMachine));

        assertThrows(CashMachineException.class, () -> {
            withdrawalService.withdrawal(withdrawalCommand);
        });
    }

    @Test
    void withdrawal_shouldThrowCashMachineExceptionWhenAmountIsNegative() {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(BigDecimal.valueOf(-100.00));
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(100.00)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.of(cashMachine));

        assertThrows(CashMachineException.class, () -> {
            withdrawalService.withdrawal(withdrawalCommand);
        });
    }

    @Test
    void withdrawal_shouldThrowCashMachineExceptionWhenBalanceIsZero() {
        WithdrawalCommand withdrawalCommand = new WithdrawalCommand(BigDecimal.valueOf(100.00));
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.ZERO).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(java.util.Optional.of(cashMachine));

        assertThrows(CashMachineException.class, () -> {
            withdrawalService.withdrawal(withdrawalCommand);
        });
    }
}
