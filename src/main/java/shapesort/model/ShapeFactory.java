package shapesort.model;

import java.awt.*;

public final class ShapeFactory {
    private static ShapeFactory shapeFactory;

    private ShapeFactory() {
    }

    public static synchronized ShapeFactory getInstance() {
        if (shapeFactory == null)
            shapeFactory = new ShapeFactory();
        return shapeFactory;
    }

    public Shape create(ShapeType shapeType) {
        return switch (shapeType) {
            case RECTANGLE -> new Rectangle();
            case SQUARE -> new Square();
            case CIRCLE -> new Circle();
            default -> throw new IllegalArgumentException("Not a valid shape.");
        };
    }

    public Shape createAt(ShapeType shapeType, Point point) {
        return switch (shapeType) {
            case RECTANGLE -> new Rectangle(point);
            case SQUARE -> new Square(point);
            case CIRCLE -> new Circle(point);
            default -> throw new IllegalArgumentException("Not a valid shape.");
        };
    }

    public Shape createAt(ShapeType shapeType, int x, int y) {
        return switch (shapeType) {
            case RECTANGLE -> new Rectangle(new Point(x, y));
            case SQUARE -> new Square();
            case CIRCLE -> new Circle(new Point(x, y));
            default -> throw new IllegalArgumentException("Not a valid shape.");
        };
    }
}
