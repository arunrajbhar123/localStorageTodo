package untils;

import android.graphics.Color;

import java.util.Random;

public class CommonFunctions {

    public static int generateRandomColor() {
        Random random = new Random();

        int red = random.nextInt(128) + 128;
        int green = random.nextInt(128) + 128;
        int blue = random.nextInt(128) + 128;

        return Color.rgb(red, green, blue);
    }
}
