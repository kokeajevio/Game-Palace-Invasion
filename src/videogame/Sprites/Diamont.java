package videogame.Sprites;

import javafx.scene.image.Image;


import java.nio.file.Files;
import java.nio.file.Paths;

public class Diamont extends Sprite {

    private  static final String IMAGE_PATH = "assets/fruits.png";
    public static  int MAX_DIAMONTS = 2;
    public static int  DIAMONT_WIDTH = 30;
    public static int DIAMONT_HEIGHT = 30;
    public static float STEP_INCREMENT = 0f;

    public Diamont()
    {
        this((int)(Math.random() * MAX_DIAMONTS));

    }
    public Diamont(int DiamontType) {
        super(DIAMONT_WIDTH, DIAMONT_HEIGHT);
        try {
            SpriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();

        }
        switch (DiamontType) {
            case 0:
                //diamont1
                this.SpriteX = 4;
                this.SpriteY = 40;
                break;

            case 1:
                //diamont2
                this.SpriteX = 42;
                this.SpriteY = 40;
                break;
        }
    }
    public void move()
    {
        this.y += (int) (1 + STEP_INCREMENT);
    }

    public void increaseDifficulty()
    {
        STEP_INCREMENT += 0.1f;
    }
}
