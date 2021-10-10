package shapesort.gui;

import shapesort.model.Shape;
import shapesort.util.SortingTechnique;

import java.util.List;
import java.util.Objects;

/**
 * The class which links the model and the view. It contains a reference to a view which it can receive and present
 * information.
 *
 * @author Amir Mohamad
 */
public record Presenter(View view) {
    public Presenter {
        Objects.requireNonNull(view);
    }

    public void reloadShapes(List<Shape> shapes) {
        this.view.reloadShapes(shapes);
    }

    public void sortShapes(List<Shape> shapes) {
        if (shapes == null || shapes.isEmpty())
            return;
        SortingTechnique.sort(shapes);
        this.view.updateShapePositions(shapes);
    }
}
