package shapesort.model;

import java.awt.*;

public class Square extends Rectangle {

    public Square() {
        super(DEFAULT_WIDTH, DEFAULT_WIDTH);
    }

    public Square(int width) {
        super(width, width);
    }

    public Square(Point point) {
        super(point);
    }

    public Square(int width, int x, int y) {
        super(width, width, x, y);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }
}
