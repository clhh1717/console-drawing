package com.code.test.console.drawing.model.shape;

public class DrawingResult {
    private char[][] drawingResult;

    public DrawingResult(char[][] result) {
        drawingResult = java.util.Arrays.stream(result).map(el -> el.clone()).toArray($ -> result.clone());
    }

    public char[][] getDrawingResult() {
        return drawingResult;
    }
}
