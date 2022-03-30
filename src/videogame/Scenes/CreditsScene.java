package videogame.Scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.CastleInvasionChallenge;

public class CreditsScene extends  GeneralScene
{
    public CreditsScene()
    {
        super();
    }

    private void showCreditsMessage()
    {
        Font myFont = Font.font("Arial", FontWeight.NORMAL,  32);
        gc.setFont(myFont);
        gc.setFill(Color.GREEN);
        gc.fillText("Game Over", 325, 180);

        myFont = Font.font( "Arial", FontWeight.NORMAL, 20);
        gc.setFont(myFont);
        gc.setFill(Color.YELLOW);
        gc.fillText("Your score: " + GameScene.points, 340, 225);
        gc.setFill(Color.WHITE);
        gc.fillText("Press spacebar to go back to welcome scene", 200, 275);
    }

    @Override
    public void draw()
    {


        activeKeys.clear();
        new AnimationTimer()
        {
            @Override
            public void handle(long CurrentNanoTime)
            //Black background
            {

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                showCreditsMessage();

                if (activeKeys.contains(KeyCode.SPACE)) {
                    this.stop();
                    CastleInvasionChallenge.setScene(
                            CastleInvasionChallenge.WELCOME_SCENE);
                }
            }
        }.start();
    }

}
