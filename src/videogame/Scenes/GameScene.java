package videogame.Scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.CastleInvasionChallenge;
import videogame.Sprites.Diamont;
import videogame.Sprites.MainCharacter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameScene extends GeneralScene
{
    private static final String CATEDRAL_IMAGE = "assets/catedral.png";
    private static final String CATEDRAL_SONG = "assets/papa-dont-take-no-mess.mp3";
    private static final String SOUND_EFFECT = "assets/quick-jump.wav";


    private Image catedral;
    private MainCharacter personaje;
    private Diamont diamont = null;

    private MediaPlayer mediaPlayerEffects;
    private Media effect;
    public static int points = 0;
    private int lives = 1;

    public GameScene()
    {
        super();
        try {
            catedral = new Image(Files.newInputStream(Paths.get(CATEDRAL_IMAGE)));
            personaje = new MainCharacter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw()
    {

        reset();

        sound = new Media(new File(CATEDRAL_SONG).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        activeKeys.clear();
        personaje.MoveTo(380, 375);
        new AnimationTimer()
        {
            @Override
            public void handle(long CurrentNanoTime)
            //Black background
            {

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                gc.drawImage(catedral, 0, 0);
                personaje.draw(gc);
                if (diamont != null)
                    diamont.draw(gc);
                updateHUD();


                if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    CastleInvasionChallenge.setScene(
                            CastleInvasionChallenge.WELCOME_SCENE);

                } else if (activeKeys.contains(KeyCode.ENTER)) {
                    this.stop();
                    CastleInvasionChallenge.setScene(
                            CastleInvasionChallenge.CREDITS_SCENE);

                }else if (activeKeys.contains(KeyCode.LEFT)) {
                    personaje.move(MainCharacter.LEFT);
                }else if (activeKeys.contains(KeyCode.RIGHT)) {
                    personaje.move(MainCharacter.RIGHT);
                }
                if(diamont == null)
                {


                    diamont= new Diamont();
                    diamont.MoveTo((int)(Math.random() * (GeneralScene.GAME_WIDTH - Diamont.DIAMONT_HEIGHT)), 0 );

                } else {


                    diamont.move();
                    if(diamont.collidesWith(personaje))
                    {
                        points += 10;
                        diamont.increaseDifficulty();
                        playEffect(SOUND_EFFECT);
                        diamont = null;
                    } else if (diamont.getY() > GeneralScene.GAME_HEIGHT)
                    {
                        lives--;
                        personaje.resetPosition();
                        diamont = null;

                    }
                }
                if(lives == 0)
                {
                    this.stop();
                    mediaPlayer.stop();
                    CastleInvasionChallenge.setScene(
                            CastleInvasionChallenge.CREDITS_SCENE
                    );
                }
            }
        }.start();
    }

    private void playEffect(String path)
    {
        effect = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(effect);
        mediaPlayerEffects.play();
    }

    private void reset()
    {
        personaje.resetPosition();
        lives = 1;
        points = 0;
        Diamont.STEP_INCREMENT = 0f;
    }
    private void updateHUD()
    {
        Font myFont = Font.font(  "Arial", FontWeight.NORMAL,  18);
        gc.setFont(myFont);
        gc.setFill(Color.BLUE);
        gc.fillText("Score: " + points,  20,  GeneralScene.GAME_HEIGHT -15);

        gc.setFill(Color.YELLOW);
        gc.fillText( "Lives" + lives,  GeneralScene.GAME_WIDTH - 100,  GeneralScene.GAME_HEIGHT -15);

    }
}
