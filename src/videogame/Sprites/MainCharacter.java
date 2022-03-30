package videogame.Sprites;

import javafx.scene.image.Image;
import videogame.Scenes.GeneralScene;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainCharacter extends AnimatedSprite
{
    public static final int MAIN_CHARACTER_WIDTH = 140;
    public static final int MAIN_CHARACTER_HEIGHT = 140;
    private static final String IMAGE_PATH = "assets/personaje.png";
    private static final int STEP = 4;
    public static final int LEFT= 0;
    public static final int RIGHT= 1;

    public MainCharacter()
    {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
        try
        {
            SpriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }  catch (Exception e) {
            e.printStackTrace();
        }

        SpriteXCoordinates[RIGHT] = new int[] {230, 240, 230, 240};
        SpriteYCoordinates[RIGHT] = new int[] {0 , 5, 0, 5};
        SpriteXCoordinates[LEFT] = new int[] {850, 860, 850, 860};
        SpriteYCoordinates[LEFT] = new int[] {0 , 5, 0, 5};

        updateSpriteCoordinates();

    }

    public void move(int movement)
    {
        int newX = x;
        if (movement == LEFT)
            newX -= Math.min(STEP, x);
        else if (movement == RIGHT)
            newX += Math.min(STEP, GeneralScene.GAME_WIDTH - MAIN_CHARACTER_WIDTH/ 2 -x);
        MoveTo(newX, y);
        animate(movement);
    }

    public void resetPosition()
    {
        MoveTo( GeneralScene.GAME_WIDTH / 2 - MAIN_CHARACTER_WIDTH / 2,
                GeneralScene.GAME_HEIGHT / - 30 - MAIN_CHARACTER_HEIGHT);
    }
}
