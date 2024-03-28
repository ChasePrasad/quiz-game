/*
    Name: Chase Prasad
    Title: Quiz Game
    
    This program simulates a game show in which the user answers 10 questions relating to Java and earns points for answering correctly.

    Pseudocode:

    Declare and initialize variables: String userName, menuChoice
                                      String[] question, answer(A-D), correctAnswer, highNames
                                      int[] pointValue, highScores

    Create methods: displayMainMenu, displayRules, processQuestion, displayScore, readInHighScores, compareScores, updateHighScores

    do
    {
        Display intro
        userName = Prompt for name
        menuChoice = Call to display main menu and return menu choice

        if(menuChoice.equals("1"))
        {
            Call to display rules
        }
        else if(menuChoice.equals("2"))
        {
            Declare and initialize certain variables to allow for game replay function: Scanner inputQuestionsFile = new Scanner(new File("questions.txt"))
                                              int index = 0, points = 0

            Display game intro

            while(inputQuestionsFile.hasNext())
            {
                question[index] = Read question
                answer(A-D)[index] = Read answers A-D
                correctAnswer[index] = Read correct answer
                pointValue[index] = Read point value
                if(inputQuestionsFile.hasNext())
                {
                    Fix read position by having a dummy read function
                }

                points += Call to process and display questions, prompt for and validate answer, and return points if earned
		        Call to display current score
                Increment index
            }
            Close file after use

            Display end of game message with total points

            Call to read in high scores
            Call to compare user total score to high scores
            Update high scores
        }
    } while(!(menuChoice.equals("3")))

    Display goodbye
*/

// All system console outputs and scanner inputs have been changed to dialog box outputs and inputs
// Library needed to use dialog box
import javax.swing.JOptionPane;
// Library needed to import and write to files
import java.io.*;
// Library needed to read from files
import java.util.Scanner;

public class javaGamev6
{
    public static void main(String[] args) throws IOException
    {
        // Declare and initialize variables
        String userName,
               menuChoice;
        // UPDATE: Variables changed to arrays
        String[] question = new String[10],
                 answerA = new String[10],
                 answerB = new String[10],
                 answerC = new String[10],
                 answerD = new String[10],
                 correctAnswer = new String[10],
                 highNames = new String[3];
        // Point accumulation system added
        int[] pointValue = new int[10],
              highScores = new int[3];

        // do-while loop used to allow user to play again multiple times
        do
        {
            // Display intro
            JOptionPane.showMessageDialog(null, "Welcome, one and all, to Who Wants to Be a Java Programmer!");

            // Prompt for name
            userName = JOptionPane.showInputDialog("To begin, please enter your name:");

            // Call to display main menu and return menu choice
            menuChoice = displayMainMenu(userName);

            // Menu decision structure added
            if(menuChoice.equals("1"))
            {
                // Call to display rules
                displayRules();
            }
            else if(menuChoice.equals("2"))
            {
                // UPDATE: Declare and initialize certain variables to allow for game replay function
                Scanner inputQuestionsFile = new Scanner(new File("questions.txt"));
                int index = 0;
                int points = 0;
                
                // Display game intro
                JOptionPane.showMessageDialog(null, "Let's play!");

                // Read until end of file
                while(inputQuestionsFile.hasNext())
                {
                    // Read values from file
                    // UPDATE: Arrays used to store question information
                    question[index] = inputQuestionsFile.nextLine();
                    answerA[index] = inputQuestionsFile.nextLine();
                    answerB[index] = inputQuestionsFile.nextLine();
                    answerC[index] = inputQuestionsFile.nextLine();
                    answerD[index] = inputQuestionsFile.nextLine();
                    correctAnswer[index] = inputQuestionsFile.nextLine();
                    pointValue[index] = inputQuestionsFile.nextInt();
                    // Fix read position by having a dummy read function
                    if(inputQuestionsFile.hasNext())
                    {
                        inputQuestionsFile.nextLine();
                    }

                    // Call to process and display questions, prompt for and validate answer, and return points if earned
                    points += processQuestion(question, answerA, answerB, answerC, answerD, correctAnswer, pointValue, index);
                    // Call to display current score
                    displayScore(points);
                    
                    // UPDATE: Increment index
                    index++;
                }
                // Close file after use
                inputQuestionsFile.close();

                // Display end of game message with total points
                JOptionPane.showMessageDialog(null, "You have reached the end of Who Wants to Be a Java Programmer!\n\nYou ended with a total of " + points + " point(s)!");

                // Call to read in high scores
                readInHighScores(highNames, highScores);
                // Call to compare user total score to high scores
                compareScores(userName, points, highNames, highScores);
                // UPDATE: Update high scores
                updateHighScores(highNames, highScores);

                // Play again prompt removed
            }
        } while(!(menuChoice.equals("3")));

        // Display goodbye
        JOptionPane.showMessageDialog(null, "Thank you for playing Who Wants to Be a Java Programmer! Have a nice day!");
    }

    /*
        Creating Method: displayMainMenu

        Description: Displays the main menu of the game

        UPDATE: @param name The user's name

        @return The user's menu choice
    */
    public static String displayMainMenu(String name)
    {
        // Declare and initialize variables
        String choice;

        // do-while loop used to validate user choice
        // Display menu, prompt for choice, and validate choice
        do
        {
            choice = JOptionPane.showInputDialog("Hello " + name + "! Please choose an option from the following menu:\n\n\t1. See Rules\n\t2. Play Game\n\t3. Exit\n\nPlease enter your choice:");
        } while(!(choice.equals("1") || choice.equals("2") || choice.equals("3")));

        // Return user menu choice
        return choice;
    }

    /*
        Creating Method: displayRules

        Description: Displays the rules of the game
    */
    public static void displayRules()
    {
        // Display rules
        JOptionPane.showMessageDialog(null, "Here are the rules:\n\nYou will be given 10 questions about Java that increase in difficulty with each question.\nEvery time you answer a question correctly you will gain points that also increase with each question.\nThe goal is to earn as many points as possible. Try your best and have fun!");
    }

    /*
        UPDATE: Method updated to implement arrays
        
        Creating Method: processQuestion

        Description: Processes and displays question, prompts user for answer and validates answer, and returns points if user got the question right

        @param q The question array
        @param ans(A-D) The question answers A-D array
        @param correctAns The correct answers array for the question array
        @param pointVal The point values array of the questions array

        @return The point value of the question if the user got it right, else 0
    */
    public static int processQuestion(String[] q, String[] ansA, String[] ansB, String[] ansC, String[] ansD, String[] correctAns, int[] pointVal, int i)
    {
        // Declare and initialize variable
        String userAnswer;
        
        // Do-while loop used to validate answer
        do
        {
            // Display question, prompt for answer, and validate answer
            userAnswer = JOptionPane.showInputDialog(q[i] + "\n\t" + ansA[i] + "\n\t" + ansB[i] + "\n\t" + ansC[i] + "\n\t" + ansD[i] + "\n\nPlease enter your answer:");
        } while(!(userAnswer.equalsIgnoreCase("A") || userAnswer.equalsIgnoreCase("B") || userAnswer.equalsIgnoreCase("C") || userAnswer.equalsIgnoreCase("D")));
        
        // Correct/incorrect answer choice and response added
        if(userAnswer.equalsIgnoreCase(correctAns[i]))
        {
            // Display correct message and points earned
            JOptionPane.showMessageDialog(null, "Correct! You have earned " + pointVal[i] + " point(s)!");
            // Return granted points
            return pointVal[i];
        }
        else
        {
            // Display incorrect message
            JOptionPane.showMessageDialog(null, "Incorrect. Try again with the next question!");
            // Return 0 value for points
            return 0;
        }
    }

    /*
        Creating Method: displayScore

        Description: Displays the user's current score

        @param score The user's current score
    */
    public static void displayScore(int score)
    {
        // Display user's current score
        JOptionPane.showMessageDialog(null, "You have a total of " + score + " point(s).");
    }

    /*
        UPDATE: Method updated to implement arrays
        
        Creating Method: readInHighScore

        Description: Returns the current high score for the game

        UPDATE: @param names The array for high score players' names to be stored in
        UPDATE: @param scores The corresponding array for high scores to be stores in
    */
    public static void readInHighScores(String[] names, int[] scores) throws IOException
    {
        // Declare and initialize variables
        Scanner inputHighScoreFile = new Scanner(new File("highScores.txt"));

        for(int i = 0; i < 3; i++)
        {
            // UPDATE: Add name to high score names array
            names[i] = inputHighScoreFile.next();
            // UPDATE: Add corresponding score to high score scores array
            scores[i] = inputHighScoreFile.nextInt();
        }
    }

    /*
        Creating Method: compareScore

        Description: Compares the user's score to the current high scores

        UPDATE: @param name The user's name
        UPDATE: @param score The user's score
        UPDATE: @param names The array of high score players' names
        UPDATE: @param scores The corresponding array of high scores
    */
    public static void compareScores(String name, int score, String[] names, int[] scores) throws IOException
    {
        for(int i = 0; i < 3; i++)
        {
            // Compares user total score to high score and only executes if it is higher
            if(score > scores[i])
            {
                // Display congratulations for beating previous high score
                JOptionPane.showMessageDialog(null, "Congratulations! With your score of " + score + " point(s), you have managed to beat " + names[i] + "'s high score of " + scores[i] + " point(s)!");

                // UPDATE: Update high score names and scores array depending on new placement of user score
                switch(i)
                {
                    case 0:
                        names[2] = names[1];
                        scores[2] = scores[1];

                        names[1] = names[0];
                        scores[1] = scores[0];

                        names[0] = name;
                        scores[0] = score;

                        break;
                    case 1:
                        names[2] = names[1];
                        scores[2] = scores[1];

                        names[1] = name;
                        scores[1] = score;

                        break;
                    case 2:
                        names[2] = name;
                        scores[2] = score;
                }
                // UPDATE: Exit loop after placing user score to prevent errors
                break;
            }
        }
    }

    /*
        UPDATE: Creating Method: updateHighScores

        Description: Updates the high scores file

        @param names The array of high score players' names
        @param scores The corresponding array of high scores
    */
    public static void updateHighScores(String[] names, int[] scores) throws IOException
    {
        // Declare and initialize PrintWriter
        PrintWriter outputHighScoreFile = new PrintWriter("highScores.txt");
        for(int i = 0; i < 3; i++)
        {
            // Print points value to new highscore file
            outputHighScoreFile.print(names[i] + " " + scores[i] + "\n");
        }
        // Close file after use
        outputHighScoreFile.close();
    }
}