package com.code.test.console.drawing.test;

import com.code.test.console.drawing.Main;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void drawCanvas() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        Main main = new Main();
        String c = "C 20 4";
        String output = main.process(c);
        Assert.assertEquals(expected, output);

    }

    @Test
    public void drawLine() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        Main main = new Main();
        String c = "C 20 4";
        main.process(c);
        String l = "L 1 2 6 2";
        String output = main.process(l);
        Assert.assertEquals(expected, output);
    }

    @Test
    public void drawLine_undo() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        Main main = new Main();
        String c = "C 20 4";
        main.process(c);
        String l = "L 1 2 6 2";
        main.process(l);
        String u = "U";
        String output = main.process(u);
        Assert.assertEquals(expected, output);
    }

    @Test
    public void drawLine_twice_undoOne() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "----------------------";
        Main main = new Main();
        String c = "C 20 4";
        main.process(c);
        String l = "L 1 3 6 3";
        main.process(l);
        String l2 = "L 1 2 6 2";
        main.process(l2);
        String u = "U";
        String output = main.process(u);
        Assert.assertEquals(expected, output);
    }
}
