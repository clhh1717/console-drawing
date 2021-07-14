package com.code.test.console.drawing.model;

import com.code.test.console.drawing.model.exception.OutOfBoundaryException;
import com.code.test.console.drawing.model.shape.Coordinate;
import com.code.test.console.drawing.model.shape.Line;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    @Test
    public void createLine_vertical() {
        Line l = new Line(1,2, 1,4);
        Coordinate start = new Coordinate(1,2);
        Coordinate end = new Coordinate(1,4);
        Assert.assertEquals(start, l.getStart());
        Assert.assertEquals(end, l.getEnd());
    }

    @Test
    public void createLine_horizontal() {
        Line l = new Line(5,3, 9,3);
        Coordinate start = new Coordinate(5,3);
        Coordinate end = new Coordinate(9,3);
        Assert.assertEquals(start, l.getStart());
        Assert.assertEquals(end, l.getEnd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLine_error_invalid() {
        Line l = new Line(1,3, 2,4);
    }


    @Test(expected = IllegalArgumentException.class)
    public void createLine_error_noLength() {
        Line l = new Line(1,3, 1,3);
    }

    @Test(expected = OutOfBoundaryException.class)
    public void createLine_error_negativeCoordinate() {
        Line l = new Line(-1,-1,-1,-2);
    }

    @Test
    public void createLine_equal() {
        Line l1 = new Line(5,3, 9,3);
        Line l2 = new Line(5,3, 9,3);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void createLine_switchStartEnd() {
        Line l = new Line(1,4, 1,2);
        Coordinate start = new Coordinate(1,2);
        Coordinate end = new Coordinate(1,4);
        Assert.assertEquals(start, l.getStart());
        Assert.assertEquals(end, l.getEnd());
    }

}
