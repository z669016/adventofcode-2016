package com.putoet.utils.assembunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OutTest {
    private ExecutionContext context = null;

    @Test
    void toggle() {
        final var a = mock(Register.class);
        final var context = mock(ExecutionContext.class);

        final var out = new Out(new InOperant(a), () -> context);
        final var toggle = out.toggle();

        assertTrue(toggle instanceof Inc);
    }

    @Test
    void execute() {
        final var a = mock(Register.class);

        when(a.get()).thenReturn(2);
        final var out = new Out(new InOperant(a), () -> context);

        context = mock(ExecutionContext.class);
        when(context.consumer()).thenReturn(i -> assertEquals(2, i));

        out.execute();

        verify(context, times(1)).consumer();
    }
}