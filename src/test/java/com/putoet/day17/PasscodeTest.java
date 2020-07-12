package com.putoet.day17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasscodeTest {
    private static final String SEED = "hijkl";
    private Passcode passcode;

    @BeforeEach
    void setup() {
        passcode = new Passcode(SEED);
    }

    @Test
    void forRoute() {
        assertEquals("ced9", passcode.forRoute(""));
        assertEquals("f2bc", passcode.forRoute("D"));
        assertEquals("5745", passcode.forRoute("DR"));
        assertEquals("528e", passcode.forRoute("DU"));
    }
}