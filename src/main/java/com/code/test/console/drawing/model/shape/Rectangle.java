package com.code.test.console.drawing.model.shape;

import java.util.Objects;

public class Rectangle {

    private Coordinate upperLeft;
    private Coordinate lowerRight;

    public Rectangle(int x1, int y1, int x2, int y2) {
        Coordinate ul = new Coordinate(x1, y1);
        Coordinate lr = new Coordinate(x2, y2);
        if (ul.equals(lr)) {
            throw new IllegalArgumentException("Invalid rectangle - two corners are the same");
        }

        if (ul.getX() == lr.getX() && ul.getY() > lr.getY()
                || ul.getY() == lr.getY() && ul.getX() > lr.getX()
                || ul.getX() > lr.getX() && ul.getY() > lr.getY()) {
            this.upperLeft = lr;
            this.lowerRight = ul;
        } else {
            this.upperLeft = ul;
            this.lowerRight = lr;
        }
    }

    public Line getEdgeLine(RectangleEdge edge) {
        switch (edge) {
            case TOP:
                return new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.lowerRight.getX(), this.upperLeft.getY());
            case BOTTOM:
                return new Line(this.upperLeft.getX(), this.lowerRight.getY(), this.lowerRight.getX(), this.lowerRight.getY());
            case LEFT:
                return new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(), this.lowerRight.getY());
            case RIGHT:
                return new Line(this.lowerRight.getX(), this.upperLeft.getY(), this.lowerRight.getX(), this.lowerRight.getY());
            default:
                return null;
        }
    }


    public Coordinate getUpperLeft() {
        return upperLeft;
    }

    public Coordinate getLowerRight() {
        return lowerRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(upperLeft, rectangle.upperLeft) &&
                Objects.equals(lowerRight, rectangle.lowerRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperLeft, lowerRight);
    }


}
