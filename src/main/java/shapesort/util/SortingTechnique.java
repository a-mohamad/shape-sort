package shapesort.util;

import shapesort.model.Shape;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * A static utility class with methods to sort a collection of shapes by deafult
 * using their surface area. A custom comparator can be used to sort as an
 * alternative.
 *
 * @author Amir Mohamad
 */
public final class SortingTechnique {

    public static void sort(List<Shape> shapes) {
        sort(shapes, null);
    }

    private static void sort(List<Shape> shapes, Comparator<? super Shape> c) {
        Object[] a = shapes.toArray();
        quickSort(a, 0, a.length - 1);
        ListIterator<Shape> i = shapes.listIterator();
        for (Object e : a) {
            i.next();
            i.set((Shape) e);
        }
    }

    private static void quickSort(Object[] shapes, int left, int right) {
        if (left >= right)
            return;

        Shape pivot = (Shape) shapes[(left + right) / 2];
        int index = partition(shapes, left, right, pivot);
        quickSort(shapes, left, index - 1);
        quickSort(shapes, index, right);

    }

    private static int partition(Object[] shapes, int left, int right, Shape pivot) {
        while (left <= right) {
            while (((Shape) shapes[left]).compareTo(pivot) < 0)
                left++;

            while (((Shape) shapes[right]).compareTo(pivot) > 0)
                right--;

            if (left <= right) {
                if (((Shape) shapes[right]).area() != ((Shape) shapes[left]).area())
                    swap(left, right, shapes);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int left, int right, Object[] shapes) {
        Shape leftShape = (Shape) shapes[left];
        Shape rightShape = (Shape) shapes[right];
        Point tempPos = new Point(leftShape.getX(), leftShape.getY());
        leftShape.setPos(new Point(rightShape.getX(), rightShape.getY()));
        rightShape.setPos(tempPos);

        shapes[left] = shapes[right];
        shapes[right] = leftShape;
    }

}
