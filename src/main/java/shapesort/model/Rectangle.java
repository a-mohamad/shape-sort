package shapesort.model;

import java.awt.*;

/**
 * An abstraction of a rectangle.
 *
 * @author Amir Mohamad
 */
public class Rectangle extends Shape {
    protected static final int DEFAULT_WIDTH = 1;
    protected static final int DEFAULT_HEIGHT = 1;
    private int width;
    private int height;

    public Rectangle() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point point) {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.setPos(point.getX(), point.getY());
    }

    public Rectangle(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.setPos(x, y);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public final void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());
        g2d.fillRect(this.getX(), this.getY(), width, height);
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public Dimension getBoundingDimension() {
        return new Dimension(width, height);
    }

    @Override
    public void setBoundingDimension(Dimension dimension) {
        setWidth(dimension.width);
        setHeight(dimension.height);
    }
}
