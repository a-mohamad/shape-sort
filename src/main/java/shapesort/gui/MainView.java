package shapesort.gui;

import shapesort.model.Shape;
import shapesort.model.ShapeFactory;
import shapesort.util.SwingUtility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainView extends AbstractView {

    /* frame properties */
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    /* general constants */
    private static final int NUM_SHAPES = 6;
    private static final double ACTION_PANEL_RATIO = 0.08;
    private static final int ACTION_PANEL_HEIGHT = (int) (HEIGHT * ACTION_PANEL_RATIO);
    private static final int CANVAS_HEIGHT = (int) (HEIGHT * (1.0 - ACTION_PANEL_RATIO));
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 40;
    /* shape properties constants */
    private final Random rand = new Random();
    private final int SHAPE_PADDING = 15;
    private final double AREA_VARIATION = 20;
    private final double DIV_FACTOR = 1.2;
    private final int BASE_SIZE = (int) (CANVAS_HEIGHT / (NUM_SHAPES * DIV_FACTOR) - SHAPE_PADDING - AREA_VARIATION);
    /**
     * List of shapes to display.
     */
    private final ArrayList<Shape> shapes = new ArrayList<>();
    /**
     * Singleton factory to instantiate shapes.
     */
    private final ShapeFactory shapeFactory = ShapeFactory.getInstance();
    /**
     * Canvas to paint the shapes onto.
     */
    private final JPanel canvas;
    /**
     * Keep track of the sorted state of the shapes.
     */
    private boolean isSorted = false;

    public MainView() {
        canvas = new Canvas();
        JPanel root = new JPanel(new BorderLayout());
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton loadShapesBtn = new JButton("Load shapes");
        JButton sortShapesBtn = new JButton("Sort shapes");

        actionPanel.setPreferredSize(new Dimension(WIDTH, ACTION_PANEL_HEIGHT));
        SwingUtility.setTransparent(actionPanel);

        loadShapesBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        sortShapesBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        loadShapesBtn.addActionListener(e -> {
            this.presenter.reloadShapes(shapes);
            this.isSorted = false;
        });
        sortShapesBtn.addActionListener(e -> {
            if (this.isSorted)
                return;
            this.presenter.sortShapes(shapes);
            this.isSorted = true;
            this.canvas.repaint();
        });

        actionPanel.add(loadShapesBtn);
        actionPanel.add(sortShapesBtn);

        canvas.setPreferredSize(new Dimension(WIDTH, CANVAS_HEIGHT));
        SwingUtility.setTransparent(canvas);

        root.add(actionPanel, BorderLayout.NORTH);
        root.add(canvas, BorderLayout.CENTER);
        root.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        SwingUtility.setTransparent(root);

        this.frame.add(root);
        this.frame.setTitle("Display Shapes");
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setMinimumSize(new Dimension(BUTTON_WIDTH * 3, 0));
        this.frame.setResizable(true);
        this.frame.pack();
        this.frame.toFront();
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        View.createView(ViewType.MAIN_VIEW);
    }

    @Override
    public void reloadShapes(List<Shape> shapes) {
        shapes.clear();
        final Point lastPoint = new Point();

        for (int i = 0; i < NUM_SHAPES; i++) {
            int width = BASE_SIZE + (int) ((rand.nextInt(3) - 1) * AREA_VARIATION);
            int height = BASE_SIZE + (int) ((rand.nextInt(3) - 1) * AREA_VARIATION);
            Shape shape = shapeFactory.create(width, height);

            lastPoint.translate(SHAPE_PADDING, SHAPE_PADDING);
            shape.setPos(lastPoint);
            lastPoint.setLocation(lastPoint.x + shape.getBoundingDimension().width,
                    lastPoint.y + shape.getBoundingDimension().height);
            shapes.add(shape);
        }
        this.canvas.repaint();
    }

    @Override
    public void updateShapePositions(List<Shape> shapes) {
        if (this.shapes == null || this.shapes.isEmpty() || this.shapes.size() == 1)
            return;

        for (int i = 1; i < NUM_SHAPES; i++) {
            Shape s = this.shapes.get(i);
            Shape last = this.shapes.get(i - 1);

            int lastW = (int) last.getBoundingDimension().getWidth();
            int lastH = (int) last.getBoundingDimension().getHeight();
            s.setPos(last.getX() + lastW + SHAPE_PADDING, last.getY() + lastH + SHAPE_PADDING);
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            for (Shape s : shapes) {
                s.draw(g);
            }
        }
    }
}
