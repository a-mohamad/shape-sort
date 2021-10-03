package shapesort.gui;

import shapesort.model.Shape;
import shapesort.util.SortingTechnique;

import java.util.List;
import java.util.Objects;

public record Presenter(View view) {
    public Presenter {
        Objects.requireNonNull(view);
    }

    public void reloadShapes(List<Shape> shapes) {
        this.view.reloadShapes(shapes);
    }

    public void sortShapes(List<Shape> shapes) {
        if (shapes.isEmpty())
            return;
        SortingTechnique.sort(shapes);
        this.view.updateShapePositions(shapes);
    }
}
