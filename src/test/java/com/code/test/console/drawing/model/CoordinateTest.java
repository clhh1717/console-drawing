package com.code.test.console.drawing.model;

import com.code.test.console.drawing.model.exception.OutOfBoundaryException;
import com.code.test.console.drawing.model.shape.Coordinate;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void createCoordinate() {
        Coordinate c = new Coordinate(1,2);
        Assert.assertEquals(1, c.getX());
        Assert.assertEquals(2, c.getY());
    }

    @Test(expected = OutOfBoundaryException.class)
    public void createCoordinate_error() {
        new Coordinate(0,0);
    }

    @Test
    public void createCoordinate_equal() {
        Coordinate c1 = new Coordinate(1,2);
        Coordinate c2 = new Coordinate(1,2);
        Assert.assertEquals(c1, c2);
    }

    @Test
    public void createCoordinate_notEqual() {
        Coordinate c1 = new Coordinate(1,2);
        Coordinate c2 = new Coordinate(2,2);
        Assert.assertNotEquals(c1, c2);
    }


}
