package shapesort.model;

import java.awt.*;

/**
 * A static utility class with methods to sort a collection of shapes by deafult
 * using their surface area. A custom comparator can be used to sort as an
 * alternative.
 *
 * @author Amir Mohamad
 */
public abstract class Shape implements Comparable<Shape> {
    private final Point position = new Point();
    private Color color;

    public abstract void draw(Graphics g);

    public abstract double area();

    public abstract Dimension getBoundingDimension();

    public abstract void setBoundingDimension(Dimension dimension);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPos(Point point) {
        position.setLocation(point);
    }

    public void setPos(int x, int y) {
        position.setLocation(x, y);
    }

    public void setPos(double x, double y) {
        position.setLocation(x, y);
    }

    public int getX() {
        return (int) position.getX();
    }

    public void setX(int x) {
        position.setLocation(x, this.getY());
    }

    public int getY() {
        return (int) position.getY();
    }

    public void setY(int y) {
        position.setLocation(this.getY(), y);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.area(), other.area());
    }
}
