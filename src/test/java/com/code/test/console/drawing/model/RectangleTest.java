package com.code.test.console.drawing.model;

import com.code.test.console.drawing.model.shape.Coordinate;
import com.code.test.console.drawing.model.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void createRectangle() {
        Rectangle r = new Rectangle(5, 5, 10, 10);
        Coordinate ul = new Coordinate(5, 5);
        Coordinate lr = new Coordinate(10, 10);
        Assert.assertEquals(ul, r.getUpperLeft());
        Assert.assertEquals(lr, r.getLowerRight());
    }

    @Test
    public void createRectangle_switchPoint() {
        Rectangle r = new Rectangle(10, 10, 5, 5);
        Coordinate ul = new Coordinate(5, 5);
        Coordinate lr = new Coordinate(10, 10);
        Assert.assertEquals(ul, r.getUpperLeft());
        Assert.assertEquals(lr, r.getLowerRight());
    }

    @Test
    public void createRectangle_equal() {
        Rectangle r = new Rectangle(5, 5, 10, 10);
        Rectangle r2 = new Rectangle(5, 5, 10, 10);
        Assert.assertEquals(r, r2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLine_error_noSize() {
        Rectangle r = new Rectangle(10, 10, 10, 10);
    }


}
