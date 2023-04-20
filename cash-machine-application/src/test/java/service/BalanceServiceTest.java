package service;

import com.example.cash.machine.application.port.in.commands.BalanceCommand;
import com.example.cash.machine.application.port.out.LoadCashMachinePort;
import com.example.cash.machine.application.service.BalanceService;
import com.example.cash.machine.domain.entities.CashMachine;
import com.example.cash.machine.domain.enums.EventType;
import com.example.cash.machine.domain.events.BalanceEvent;
import com.example.cash.machine.domain.exception.CashMachineException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @Mock
    private LoadCashMachinePort loadCashMachinePort;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    void balance_shouldLoadCashMachineAndReturnBalanceEvent() {
        BalanceCommand balanceCommand = new BalanceCommand();
        CashMachine cashMachine = CashMachine.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(500.25)).build();
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.of(cashMachine));

        BalanceEvent event = balanceService.balance(balanceCommand);

        verify(loadCashMachinePort).getById(1L);
        assertNotNull(event);
        assertEquals(cashMachine, event.getCashMachine());
        assertEquals(BigDecimal.valueOf(500.25), event.getCashMachine().getBalance());
        assertEquals(EventType.BALANCE, event.getEventType());
        assertNotNull(event.getTimestamp());
    }

    @Test
    void balance_shouldThrowCashMachineExceptionWhenNoCashMachineFound() {
        BalanceCommand balanceCommand = new BalanceCommand();
        when(loadCashMachinePort.getById(1L)).thenReturn(Optional.empty());

        assertThrows(CashMachineException.class, () -> {
            balanceService.balance(balanceCommand);
        });
    }
}
