package com.code.test.console.drawing.model;

import com.code.test.console.drawing.model.exception.OutOfBoundaryException;
import com.code.test.console.drawing.model.shape.Coordinate;
import com.code.test.console.drawing.model.shape.Line;
import com.code.test.console.drawing.model.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CanvasTest {

    private Canvas c;

    @Before
    public void init() {
        c = new Canvas(20, 4);
    }

    @Test
    public void drawCanvas_Empty() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }


    @Test
    public void drawCanvas_line_horizontal() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        c.drawLine(new Line(1, 2, 6, 2));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_line_history() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        c.drawLine(new Line(1, 2, 6, 2));
        String output = c.getDrawingResultWithBoundary(c.getHistoryList().get(0).getDrawingResult());
        Assert.assertEquals(expected, output);
    }

    @Test
    public void drawCanvas_line_history_remove() {
        c.drawLine(new Line(1, 2, 6, 2));
        c.removeHistory();
        Assert.assertEquals(0, c.getHistoryList().size());
    }

    @Test
    public void drawCanvas_line_horizontal_undo() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        c.drawLine(new Line(1, 2, 6, 2));
        c.undo();
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_line_horizontal_switchedCoordinate() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------";
        c.drawLine(new Line(6, 2, 1, 2));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_line_vertical() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------";
        c.drawLine(new Line(6, 3, 6, 4));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_line_vertical_switchedCoordinate() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------";
        c.drawLine(new Line(6, 4, 6, 3));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_line_multiple() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------";
        c.drawLine(new Line(1, 2, 6, 2));
        c.drawLine(new Line(6, 3, 6, 4));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }


    @Test(expected = OutOfBoundaryException.class)
    public void drawCanvas_line_error_outOfBoundX() {
        c.drawLine(new Line(21, 1, 21, 2));
    }

    @Test(expected = OutOfBoundaryException.class)
    public void drawCanvas_line_error_outOfBoundY() {
        c.drawLine(new Line(1, 5, 6, 5));
    }

    @Test
    public void drawCanvas_rectangle() {
        String expected =
                "----------------------\n" +
                        "|             xxxxx  |\n" +
                        "|             x   x  |\n" +
                        "|             xxxxx  |\n" +
                        "|                    |\n" +
                        "----------------------";
        c.drawRectangle(new Rectangle(14, 1, 18, 3));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_rectangle_partially() {
        String expected =
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                 xxx|\n" +
                        "|                 x  |\n" +
                        "----------------------";

        c.drawRectangle(new Rectangle(18, 3, 21, 6));
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test(expected = OutOfBoundaryException.class)
    public void drawCanvas_rectangle_error_outOfBoundX() {
        c.drawRectangle(new Rectangle(21, 1, 21, 5));
    }

    @Test
    public void drawCanvas_bucketFill_full() {
        String expected = "----------------------\n" +
                "|oooooooooooooooooooo|\n" +
                "|oooooooooooooooooooo|\n" +
                "|oooooooooooooooooooo|\n" +
                "|oooooooooooooooooooo|\n" +
                "----------------------";
        c.bucketFill(new Coordinate(10,2), 'o');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillOInEmptyArea() {
        String expected = "----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|xxxxxxooooooox   xoo|\n" +
                "|     xoooooooxxxxxoo|\n" +
                "|     xoooooooooooooo|\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(10,3), 'o');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillOInsideRectangle() {
        String expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       xooox  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(15,2), 'o');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillOInsideRectangle2() {
        String expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|ooooox       xxxxx  |\n" +
                "|ooooox              |\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(1,4), 'o');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillOtoRectangleEdge() {
        String expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|oooooo       x   x  |\n" +
                "|     o       xxxxx  |\n" +
                "|     o              |\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(1,2), 'o');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillBlankToRectangleEdge() {
        String expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|             x   x  |\n" +
                "|             xxxxx  |\n" +
                "|                    |\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(1,2), ' ');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test
    public void drawCanvas_bucketFill_fillBlankInEmptyArea() {
        String expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------";
        c.drawLine(new Line(1,2,6,2));
        c.drawLine(new Line(6,3,6,4));
        c.drawRectangle(new Rectangle(14,1,18,3));
        c.bucketFill(new Coordinate(10,3), ' ');
        Assert.assertEquals(expected, c.getDrawingResultWithBoundary());
    }

    @Test(expected = OutOfBoundaryException.class)
    public void drawCanvas_bucketFill_error() {
        c.bucketFill(new Coordinate(21,2), 'o');
    }

    @Test(expected = OutOfBoundaryException.class)
    public void drawCanvas_bucketFill_error2() {
        c.bucketFill(new Coordinate(0,0), 'o');
    }


    @Test(expected = IllegalArgumentException.class)
    public void drawCanvas_error_size() {
        Canvas c = new Canvas(0,0);
    }
}
