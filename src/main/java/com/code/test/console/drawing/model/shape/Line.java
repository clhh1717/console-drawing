package com.code.test.console.drawing.model.shape;

import java.util.Objects;

public class Line {

    private Coordinate start;
    private Coordinate end;

    public Line(int x1, int y1, int x2, int y2) {
        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("Line must be horizontal or vertical");
        }

        Coordinate s = new Coordinate(x1, y1);
        Coordinate e = new Coordinate(x2, y2);
        if (s.equals(e)) {
            throw new IllegalArgumentException("Invalid line - Start and end point are the same");
        }

        if ((s.getX() == e.getX() && s.getY() > e.getY() || s.getY() == e.getY() && s.getX() > e.getX())) {
            this.start = e;
            this.end = s;
        } else {
            this.start = s;
            this.end = e;
        }
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) &&
                Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
