public class Ball extends GameItems{
    private String colorName;


    public static   int point=0;
    public static int ballRawcoordinate=0;
    public static int ballColcoordinate=0;
    public Ball(String colorName) {
        this.colorName=colorName;

    }
    public static void ballMove(String direction,GameItems[][] gameboard){
        ballCoordinateFinder(gameboard);
        int Rawmovestep=0;
        int Colmovestep=0;

        switch (direction){
            case "U":
                Rawmovestep--;
                break;
            case "D":
                Rawmovestep++;
                break;
            case "L":
                Colmovestep--;
                break;
            case "R":
                Colmovestep++;
                break;
        }

        moveBallCont(Rawmovestep,Colmovestep,direction,gameboard);


    }
    private static void moveBallCont(int Rawmovestep, int Colmovestep, String direction,GameItems[][]gameboard) {

        int newRAWForTarget = ballRawcoordinate + Rawmovestep;
        int newCOLForTarget = ballColcoordinate + Colmovestep;


        if (newRAWForTarget < 0) {
            newRAWForTarget = Main.xSize - 1;
        } else if (newRAWForTarget >= Main.xSize) {
            newRAWForTarget = 0;
        }
        if (newCOLForTarget < 0) {
            newCOLForTarget = Main.ySize - 1;
        } else if (newCOLForTarget >= Main.ySize) {
            newCOLForTarget = 0;
        }

        GameItems target = gameboard[newRAWForTarget][newCOLForTarget];
        GameItems ball = gameboard[ballRawcoordinate][ballColcoordinate];


        if (target.getColorName().equals("W")) {
            switch (direction) {
                case "U":
                    Rawmovestep += 2;
                    break;
                case "D":
                    Rawmovestep -= 2;
                    break;
                case "L":
                    Colmovestep += 2;
                    break;
                case "R":
                    Colmovestep -= 2;
                    break;
            }
            newRAWForTarget = ballRawcoordinate + Rawmovestep+Main.xSize;
            newCOLForTarget = ballColcoordinate + Colmovestep+Main.ySize;
        }
        newRAWForTarget=newRAWForTarget%(Main.xSize);
        newCOLForTarget=newCOLForTarget%(Main.ySize);
        target = gameboard[newRAWForTarget][newCOLForTarget];

        pointGiver(target,ball);

        if(!target.getColorName().equals("H")){

            gameboard[newRAWForTarget][newCOLForTarget] = ball;
            gameboard[ballRawcoordinate][ballColcoordinate] = target;
        }


    }


    public static void pointGiver(GameItems target,GameItems ball){
        String targetLetter= target.getColorName();
        switch (targetLetter){
            case "H":
                ball.setColorName(" ");
                Main.isgamerun=false;
                break;
            case "Y":
                target.setColorName("X");
                ball.setPoint(ball.getPoint()+5);
                break;
            case "R":
                target.setColorName("X");
                ball.setPoint(ball.getPoint()+10);
                break;
            case "B":
                target.setColorName("X");
                ball.setPoint(ball.getPoint()-5);
                break;
        }
    }


    public static void ballCoordinateFinder(GameItems[][] gameboard){
        for (int x=0;x<Main.xSize;x++){
            for(int y=0;y<Main.ySize;y++){
                if(gameboard[x][y].getColorName().equals("*")){
                    ballRawcoordinate=x;
                    ballColcoordinate=y;
                }
            }
        }
    }

    @Override
    public String getColorName() {
        return colorName;
    }
    @Override
    public int getPoint() {
        return point;
    }
    @Override
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    @Override
    public void setPoint(int point) {
        this.point = point;
    }

}
