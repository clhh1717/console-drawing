package com.code.test.console.drawing;

import com.code.test.console.drawing.model.Canvas;
import com.code.test.console.drawing.model.shape.Coordinate;
import com.code.test.console.drawing.model.shape.Line;
import com.code.test.console.drawing.model.shape.Rectangle;

public class CommandProcessor {

    private static Canvas canvas;

    public enum Command {
        Q {
            @Override
            public String execute() {
                System.exit(0);
                return null;
            }
        }, C {
            @Override
            public String execute() {
                String[] cmd = validateCmd(getInput(), 3, false);
                try {
                    canvas = new Canvas(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("invalid command. please try [C width height]");
                }
                System.out.println(canvas.getDrawingResultWithBoundary());
                return canvas.getDrawingResultWithBoundary();
            }
        }, L {
            @Override
            public String execute() {
                String[] cmd = validateCmd(getInput(), 5, true);
                try {
                    canvas.drawLine(new Line(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("invalid command. please try [L x1 y1 x2 y2]");
                }
                System.out.println(canvas.getDrawingResultWithBoundary());
                return canvas.getDrawingResultWithBoundary();
            }
        }, R {
            @Override
            public String execute() {
                String[] cmd = validateCmd(getInput(), 5, true);
                try {
                    canvas.drawRectangle(new Rectangle(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("invalid command. please try [R x1 y1 x2 y2]");
                }
                System.out.println(canvas.getDrawingResultWithBoundary());
                return canvas.getDrawingResultWithBoundary();
            }
        }, B {
            @Override
            public String execute() {
                String[] cmd = validateCmd(getInput(), 4, true);
                try {
                    canvas.bucketFill(new Coordinate(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])), cmd[3].charAt(0));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("invalid command. please try [B x y c]");
                }
                System.out.println(canvas.getDrawingResultWithBoundary());
                return canvas.getDrawingResultWithBoundary();
            }
        }, U {
            @Override
            public String execute() {
                canvas.undo();
                System.out.println(canvas.getDrawingResultWithBoundary());
                return canvas.getDrawingResultWithBoundary();
            }
        };

        private static String[] validateCmd(String params, int NumOfParam, boolean checkCanvasExists) {

            String[] cmd = params.split(" ");
            if (checkCanvasExists && canvas == null) {
                throw new IllegalArgumentException("please create canvas first");
            }
            if (cmd.length != NumOfParam) {
                throw new IllegalArgumentException("invalid command. please try command list in README");
            }
            return cmd;
        }


        private String input;

        void setInput(String input) {
            this.input = input;
        }

        String getInput() {
            return this.input;
        }

        public abstract String execute();

        public static Command getInstance(String input) {
            if (input != null && input.length() > 0) {
                String action = String.valueOf(input.charAt(0));
                try {
                    Command cmd = Command.valueOf(action);
                    cmd.setInput(input);
                    return cmd;
                } catch (IllegalArgumentException e) {
                    //ignore
                }
            }
            return null;
        }

    }

}
