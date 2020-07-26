package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TglTest {
    private ExecutionContext context = null;

    @Test
    void toggle() {
        final Register a = mock(Register.class);
        final ExecutionContext context = mock(ExecutionContext.class);

        final Instruction tgl = new Tgl(new InOperant(a), () -> context);
        final Instruction toggle = tgl.toggle();

        assertTrue(toggle instanceof Inc);
    }

    @Test
    void execute() {
        final Register a = mock(Register.class);
        final Register ip = mock(Register.class);
        final Instruction nop = mock(Nop.class);
        final Instruction[] program = new Instruction[] {null, null, null, null, null, null};

        when(a.get()).thenReturn(2);
        when(ip.get()).thenReturn(1);
        when(nop.toggle()).thenReturn(nop);
        program[3] = nop;

        final Instruction tgl = new Tgl(new InOperant(a), () -> context);

        context = mock(ExecutionContext.class);
        when(context.ip()).thenReturn(ip);
        when(context.program()).thenReturn(program);

        tgl.execute();

        verify(nop, times(1)).toggle();
    }
}