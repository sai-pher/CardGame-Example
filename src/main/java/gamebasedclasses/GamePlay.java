package gamebasedclasses;

import PlayerBasedClasses.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GamePlay {
    //TODO: Use try-catch while loops to manage input errors
    //TODO: Put an absolute limit on total number of players allowed (find said limit)
    //TODO: Implement a user based player profile to allow users to play against bots or each other
    //TODO: Implement a score tracking system to show how overall player stats.
    //TODO: Optimise data structures used.
    //TODO: Build appropriate test class for the GamePlay class.

    private static final int LIMIT = 21;

    public static void main(String[] args) {

        GameMechanics              newGame;
        Map<Integer, List<Player>> roundScores = new HashMap<>();
        int                        gameCount   = 0;

        //TODO: Game entry text.

        Scanner sin = new Scanner(System.in);
        System.out.println("Do you wish to begin the game?[y/n]: ");
        String control      = sin.next();
        String roundControl = "y";


        //Enter game play loop
        while (control.equals("y")) {

            newGame = new GameMechanics(LIMIT);

            System.out.println("how many players");
            int playerCount = sin.nextInt();

            //Create new players.
            for (int i = 0; i < playerCount; i++) {
                Player p = new Player(i, LIMIT);
                newGame.addPlayer(p);
            }
            System.out.println(String.format("%s players added", playerCount));

            //Round control loop
            while (!roundControl.equals("n")) {

                System.out.println(String.format("Round %s!", newGame.getRound()));
                newGame.gameStart();

                List<Player> results = newGame.play();

                System.out.println("Players     ||  Player Score    ||  Cards in hand");
                for (Player p : newGame.getPlayers()) {
                    System.out.printf("Player %s    ||  %s              ||  %s\n",
                                      p.getPlayerID(),
                                      p.showHandScore(),
                                      p.showHand());
                }
                if (results.size() == 1)
                    System.out.printf("\nPlayer %s wins! \nScore: %s\nHand%s",
                                      results.get(0).getPlayerID(),
                                      results.get(0).showHandScore(),
                                      results.get(0).showHand());

                else if (results.size() > 1) {
                    StringBuilder message = new StringBuilder(
                            String.format("Players %s", results.get(0).getPlayerID()));

                    for (Player p : results) {
                        message.append(" and ").append(p.getPlayerID());
                    }

                    System.out.printf(message + "draw!" + "\nScore: %s", results.get(0).showHandScore());
                }


                roundScores.put(newGame.getRound(), results);

                //Update round mechanic.
                System.out.println("\nDo you want to play another round?[y/n]:");
                roundControl = sin.next();
                if (roundControl.equals("y")) {
                    newGame.advanceRound();
                    newGame.gameReset();
                }
            }

            System.out.printf("End of game %s.\nResults: \n", gameCount);
            roundScores.forEach((round, result) -> {
                System.out.printf("\nRound %s winners\n", round);
                result.forEach(player -> System.out.printf("%s\n", player.toString()));
            });

            System.out.println("Do you wish to continue playing?[y/n]");
            control = sin.next();
        }
        System.out.println("Thanks for playing auto pontoon");


    }
}
