package shapesort.gui;

import shapesort.model.Shape;

import java.util.List;

/**
 * A view factory template for this application.
 *
 * @author Amir Mohamad
 */
public interface View {

    static View createView(ViewType type) {
        return type.create();
    }

    void reloadShapes(List<Shape> shapes);

    void updateShapePositions(List<Shape> shapes);

    /**
     * A type of {@code View} for this application.
     */
    enum ViewType {
        /**
         * The main application view.
         */
        MAIN_VIEW {
            @Override
            View create() {
                return new MainView();
            }
        };

        /**
         * @return a new {@code View} of the enum instance's specified type.
         */
        abstract View create();
    }
}