package com.code.test.console.drawing.model;

import com.code.test.console.drawing.config.DrawingConfig;
import com.code.test.console.drawing.model.exception.OutOfBoundaryException;
import com.code.test.console.drawing.model.shape.*;
import com.code.test.console.drawing.model.shape.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Canvas {
    private int width;
    private int height;
    private char[][] drawingResult;
    private final List<DrawingResult> historyList = new ArrayList<DrawingResult>();

    public Canvas(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("width or height of canvas must be >= 1");
        }
        this.width = width;
        this.height = height;
        this.drawingResult = new char[height][width];
        for (char[] row : this.drawingResult) {
            Arrays.fill(row, DrawingConfig.EMPTY_CHAR);
        }
    }

    public String getDrawingResultWithBoundary(char[][] dr) {
        StringBuffer sb = new StringBuffer();

        sb.append(getHorizontalBoundary());
        sb.append(DrawingConfig.LINE_SEPARATOR);

        for (int y = 0; y < this.height; y++) {
            sb.append(DrawingConfig.CANVAS_BOUNDARY_VERTICAL);
            for (int x = 0; x < this.width; x++) {
                sb.append(dr[y][x]);
            }
            sb.append(DrawingConfig.CANVAS_BOUNDARY_VERTICAL);
            sb.append(DrawingConfig.LINE_SEPARATOR);
        }

        sb.append(getHorizontalBoundary());
        return sb.toString();
    }

    public String getDrawingResultWithBoundary() {


        char[][] empty = new char[height][width];
        for (char[] row : empty) {
            Arrays.fill(row, DrawingConfig.EMPTY_CHAR);
        }
        return getDrawingResultWithBoundary(this.historyList != null && this.historyList.size() > 0 ? this.historyList.get(this.historyList.size() - 1).getDrawingResult() : empty);
    }

    public List<DrawingResult> getHistoryList() {
        return this.historyList;
    }

    private void addHistory(char[][] result) {
        if (this.historyList != null && result != null) {
            this.historyList.add(new DrawingResult(result));
        }
    }

    public void removeHistory() {
        if (this.historyList != null && this.historyList.size() >= 1) {
            this.historyList.remove(this.historyList.size() - 1);
        }
    }

    public void undo() {
        this.removeHistory();
        if (this.historyList != null && this.historyList.size() >= 1) {
            drawingResult = this.historyList.get(this.historyList.size() - 1).getDrawingResult();
        }
    }

    public void drawLine(Line line) throws OutOfBoundaryException {
        this.drawLine(line, DrawingConfig.LINE_CHAR);
    }

    private void drawLine(Line line, char lineChar) throws OutOfBoundaryException {
        if (line == null || line.getStart().getX() > this.width || line.getStart().getY() > this.height) {
            throw new OutOfBoundaryException("Unable to draw - line is empty or out of boundary");
        }
        for (int y = line.getStart().getY() - 1; y <= line.getEnd().getY() - 1 && y < this.height; y++) {
            for (int x = line.getStart().getX() - 1; x <= line.getEnd().getX() - 1 && x < this.width; x++) {
                drawingResult[y][x] = lineChar;
            }
        }
        this.addHistory(drawingResult);
    }

    public void drawRectangle(Rectangle rectangle) throws OutOfBoundaryException {
        if (rectangle == null || rectangle.getUpperLeft().getX() > this.width || rectangle.getUpperLeft().getY() > this.height) {
            throw new OutOfBoundaryException("Unable to draw - rectangle is empty or out of boundary");
        }
        drawLine(rectangle.getEdgeLine(RectangleEdge.TOP), DrawingConfig.RECTANGLE_CHAR);
        drawLine(rectangle.getEdgeLine(RectangleEdge.LEFT), DrawingConfig.RECTANGLE_CHAR);
        try {
            drawLine(rectangle.getEdgeLine(RectangleEdge.RIGHT), DrawingConfig.RECTANGLE_CHAR);
            drawLine(rectangle.getEdgeLine(RectangleEdge.BOTTOM), DrawingConfig.RECTANGLE_CHAR);
        } catch (OutOfBoundaryException e) {
            // ignore
        }
        this.addHistory(drawingResult);
    }

    public void bucketFill(Coordinate c, char fillChar) throws OutOfBoundaryException {
        this.bucketFill(c, fillChar, null);
        this.addHistory(drawingResult);
    }


    private void bucketFill(Coordinate c, char fillChar, Character orgChar) throws OutOfBoundaryException {
        if (c.getX() > this.width || c.getY() > this.height) {
            throw new OutOfBoundaryException("coordinate is out of boundary");
        } else {
            Character selectedChar = Character.valueOf(this.drawingResult[c.getY() - 1][c.getX() - 1]);
            if ((orgChar == null || selectedChar.equals(orgChar)) && !selectedChar.equals(fillChar)) {
                this.drawingResult[c.getY() - 1][c.getX() - 1] = fillChar;
                try {
                    bucketFill(new Coordinate(c.getX() + 1, c.getY()), fillChar, selectedChar);
                    bucketFill(new Coordinate(c.getX() - 1, c.getY()), fillChar, selectedChar);
                    bucketFill(new Coordinate(c.getX(), c.getY() - 1), fillChar, selectedChar);
                    bucketFill(new Coordinate(c.getX(), c.getY() + 1), fillChar, selectedChar);
                } catch (OutOfBoundaryException e) {
                }
            }
        }
    }


    private String getHorizontalBoundary() {
        char[] chars = new char[width + 2];
        Arrays.fill(chars, DrawingConfig.CANVAS_BOUNDARY_HORIZATAL);
        return String.valueOf(chars);
    }


}
