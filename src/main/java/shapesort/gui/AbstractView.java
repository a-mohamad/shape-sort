package shapesort.gui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public abstract class AbstractView implements View {

    protected final JFrame frame;
    protected final Presenter presenter;

    public AbstractView() {
        AbstractView.enableLookAndFeel();
        this.frame = new JFrame("Display Shapes");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.presenter = new Presenter(this);
    }

    /**
     * Set a custom look and feel.
     */
    public static void enableLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (final Exception ex) {
            System.err.println("Failed to initialize LaF");
            ex.printStackTrace();
        }
    }
}
