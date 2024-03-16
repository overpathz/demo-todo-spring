package com.example.demoreplay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FailTest {

    @Test
    public void failSmth() {
        int n1 = 5;
        int n2 = 3;
        int expRes = n1 + n2;
        int actualResult = add(n1, n2);
        assertEquals(expRes, actualResult);
    }

    public static int add(int n1, int n2) {
        return n1 - n2;
    }
}
