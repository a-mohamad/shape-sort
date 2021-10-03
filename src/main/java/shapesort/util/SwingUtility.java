package shapesort.util;

import javax.swing.*;
import java.awt.*;

public class SwingUtility {
    public static void setTransparent(JComponent component) {
        component.setBackground(new Color(0, 0, 0, 0));
        component.setOpaque(false);
    }
}
