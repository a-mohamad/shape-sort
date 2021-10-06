package shapesort.util;

import java.awt.*;
import java.util.Random;

public class ColorUtility {
    private static final Random random = new Random();
    private static final int MIN = 0;
    private static final int MAX = 255;

    public static Color getRandomColor() {
        return new Color(random.nextInt(MAX - MIN) + MIN, random.nextInt(MAX - MIN) + MIN,
                random.nextInt(MAX - MIN) + MIN);
    }

    public static Color getRandomColor(int min, int max) {
        return new Color(random.nextInt(max - min) + min, random.nextInt(max - min) + min,
                random.nextInt(max - min) + min);
    }
}
