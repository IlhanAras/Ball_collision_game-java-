import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static int xSize;
    public  static boolean isgamerun=true;
    public static int ySize;
    // There are several classes
    // GameItems is super class
    // Ball and Colors classes are subclasses

    public static void main(String[] args) throws IOException {


        // Pull data from move.txt to movelist
        File fileMovement = new File(args[1]);
        FileReader fileMovementReader = new FileReader(fileMovement);
        BufferedReader bufferedReaderMove = new BufferedReader(fileMovementReader);
        String lineMove;
        List<String> moveList = new ArrayList<>();

        while ((lineMove = bufferedReaderMove.readLine()) != null) {
            moveList.clear();
            Collections.addAll(moveList, lineMove.split(" "));

        }

        fileMovementReader.close();
        bufferedReaderMove.close();

        List<String[]> rawarrinpt=new ArrayList<>();

        // Pull data from board.txt to rawarrinpt list
        File fileBoard=new File(args[0]);
        FileReader fread=new FileReader(fileBoard);
        BufferedReader bufferedReader=new BufferedReader(fread);
        String line;

        while ((line=bufferedReader.readLine())!=null) {
            rawarrinpt.add(line.split(" "));
        }
        fread.close();
        bufferedReader.close();

        xSize=rawarrinpt.size();
        ySize=(rawarrinpt.get(0)).length;

        GameItems[][] gameboard=new GameItems[xSize][ySize];

        // create game items
        for ( int raw = 0 ; raw < xSize ; raw++ ) {
            for ( int col = 0 ; col < ySize ; col++ ) {

                String element= rawarrinpt.get(raw)[col];
                switch (element){
                    case "G":
                        gameboard[raw][col]=new Colors("G",0);
                        break;

                    case "B":
                        gameboard[raw][col]=new Colors("B",-5);
                        break;
                    case "Y":
                        gameboard[raw][col]=new Colors("Y",5);
                        break;

                    case "R":
                        gameboard[raw][col]=new Colors("R",10);
                        break;
                    case "P":
                        gameboard[raw][col]=new Colors("P",0);
                        break;

                    case "O":
                        gameboard[raw][col]=new Colors("O",0);
                        break;
                    case "D":
                        gameboard[raw][col]=new Colors("D",0);
                        break;

                    case "L":
                        gameboard[raw][col]=new Colors("L",0);
                        break;
                    case "F":
                        gameboard[raw][col]=new Colors("F",0);
                        break;
                    case "N":
                        gameboard[raw][col]=new Colors("N",0);
                        break;

                    case "W":
                        gameboard[raw][col]=new Colors("W",0);
                        break;
                    case "H":
                        gameboard[raw][col]=new Colors("H",0);
                        break;

                    case "*":
                        gameboard[raw][col]=new Ball("*");
                        break;

                    default:
                        System.out.println("unvalid color");
                        break;
                }

            }
        }

        // Open writer
        File fileWrite=new File("output.txt");
        FileWriter fileWriter=new FileWriter(fileWrite);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);


        bufferedWriter.write("Game board:\n");
        boardWriter( bufferedWriter,gameboard);


        bufferedWriter.write("\nYour movement is:\n");

        for (String s:moveList) {
            bufferedWriter.write(s+" ");
        }

        bufferedWriter.write("\n");


        // Main game loop
        for (int movelistIndex = 0 ; movelistIndex < moveList.size() ; movelistIndex++ ) {
            if( isgamerun ) {
                Ball.ballMove( moveList.get(movelistIndex) , gameboard );
            } else {
                break;
            }
        }

        // Write some String
        bufferedWriter.write("\nYour output is:\n");

        boardWriter( bufferedWriter,gameboard);

        if( !isgamerun ) {
            bufferedWriter.write("\nGame Over!");
        }

        bufferedWriter.write("\nScore: "+Ball.point);


        bufferedWriter.close();
        fileWriter.close();
    }
    public static void boardWriter(BufferedWriter bufferedWriter,GameItems[][] gameboard) throws IOException {
        for ( int raw = 0 ; raw < xSize ; raw++ ) {       // This method writes gameboard
            for(int col = 0 ; col < ySize ; col++ ) {

                bufferedWriter.write( gameboard[raw][col].getColorName() + " " );
            }

            bufferedWriter.write("\n");
        }
    }
}