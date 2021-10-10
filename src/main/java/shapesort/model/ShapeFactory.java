package shapesort.model;

import shapesort.util.ColorUtility;

import java.awt.*;
import java.util.Random;

/**
 * A singleton factory class which helps create shapes in various different ways.
 *
 * @author Amir Mohamad
 */
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
            default -> throw new IllegalArgumentException("Shape not implemented");
        };
    }

    public Shape create(ShapeType shapeType, Point point) {
        return switch (shapeType) {
            case RECTANGLE -> new Rectangle(point);
            case SQUARE -> new Square(point);
            case CIRCLE -> new Circle(point);
            default -> throw new IllegalArgumentException("Shape not implemented");
        };
    }

    public Shape create(ShapeType shapeType, int x, int y) {
        return switch (shapeType) {
            case RECTANGLE -> new Rectangle(new Point(x, y));
            case SQUARE -> new Square();
            case CIRCLE -> new Circle(new Point(x, y));
            default -> throw new IllegalArgumentException("Shape not implemented");
        };
    }

    public Shape create() {
        Random rand = new Random();
        int DEFAULT_MAX = 1000;
        int width = rand.nextInt(DEFAULT_MAX);
        int height = rand.nextInt(DEFAULT_MAX);

        return create(width, height);
    }

    public Shape create(int width, int height) {
        Random rand = new Random();
        int shapeIndex = rand.nextInt(ShapeType.values().length);
        ShapeType shapeType = ShapeType.values()[shapeIndex];
        Shape shape = shapeFactory.create(shapeType);
        shape.setColor(ColorUtility.getRandomColor());
        shape.setBoundingDimension(switch (shapeType) {
            case RECTANGLE -> new Dimension(width, height);
            case SQUARE, CIRCLE -> new Dimension(width, width);
            default -> throw new IllegalArgumentException("Shape not implemented");
        });
        return shape;
    }
}
