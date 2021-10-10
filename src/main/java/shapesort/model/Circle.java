package shapesort.model;

import java.awt.*;

/**
 * An abstraction of a circle.
 *
 * @author Amir Mohamad
 */
public class Circle extends Shape {
    private static final double PI = Math.PI;
    private static final int DEFAULT_RADIUS = 1;
    private int radius;

    public Circle() {
        this.radius = DEFAULT_RADIUS;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public Circle(Point point) {
        this.radius = DEFAULT_RADIUS;
        this.setPos(point.getX(), point.getY());
    }

    public Circle(int radius, int x, int y) {
        this.radius = radius;
        this.setPos(x, y);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());
        g2d.fillOval(this.getX(), this.getY(), radius * 2, radius * 2);
    }

    @Override
    public double area() {
        return PI * radius * radius;
    }

    @Override
    public Dimension getBoundingDimension() {
        return new Dimension(radius * 2, radius * 2);
    }

    @Override
    public void setBoundingDimension(Dimension dimension) {
        assert dimension.width == dimension.height;
        setRadius(dimension.width / 2);
    }
}
