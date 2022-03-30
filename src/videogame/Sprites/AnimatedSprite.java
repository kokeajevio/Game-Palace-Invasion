package videogame.Sprites;

public class AnimatedSprite extends Sprite
{
    public static final int TOTAL_MOVEMENTS = 2;
    public static final int  RIGHT = 0;
    public static final int  LEFT = 1;
    public static final byte SPRITE_CHANGE = 5;

    protected int currentDirection;
    protected byte currentSprite;
    protected  byte currentSpriteChange;


    protected int [][] SpriteXCoordinates = new int[TOTAL_MOVEMENTS][];
    protected int [][] SpriteYCoordinates = new int[TOTAL_MOVEMENTS][];

    public AnimatedSprite(int width, int height)
    {
        super(width, height);
        currentDirection = RIGHT;
        currentSprite = 0;
        currentSpriteChange = 5;

    }
    public void animate(int movement) {
        if (movement != currentDirection)
        {
            currentDirection = movement;
            currentSprite = 0;
            currentSpriteChange = 0;

        }  else {
            currentSpriteChange++;
            if (currentSpriteChange >= SPRITE_CHANGE) {


                currentSpriteChange = 0;
                currentSprite = (byte) ((currentSprite + 1) %
                        SpriteXCoordinates[currentDirection].length);
            }
        }
        updateSpriteCoordinates();
    }
    protected void updateSpriteCoordinates()
    {
        SpriteX = SpriteXCoordinates[currentDirection][currentSprite];
        SpriteY = SpriteYCoordinates[currentDirection][currentSprite];
    }
}
