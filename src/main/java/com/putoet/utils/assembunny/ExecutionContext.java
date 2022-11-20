package com.putoet.utils.assembunny;

import java.util.function.Consumer;

public record ExecutionContext(Register ip, RegisterSet regs, Instruction[] program, Consumer<Integer> consumer) {
}
