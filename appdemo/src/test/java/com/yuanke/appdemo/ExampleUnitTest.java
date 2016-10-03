package com.yuanke.appdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    public static String str = "HelloWord";

    @Test
    public void addition_isCorrect() throws Exception {
        show(0, new String());

    }

    private void show(int current, String temp) {
        if (current < str.length()) {
            for (int i = 0; i < str.length(); i++) {
                if (!(temp.contains(str.substring(i, i + 1)))) {
                    System.out.println(temp + str.substring(i, i + 1));
                    show(current + 1, new String(temp + str.substring(i, i + 1)));
                }
            }
        }
    }
}