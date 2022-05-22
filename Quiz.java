/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quiz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author mear
 */
public class Quiz {

    /**
     * @param args the command line arguments
     */
    static Scanner reader = new Scanner(System.in);
    static int numberOfQuestions;
    static String[] randomStrings;
    static String[] typeMathNumbers = {"Primary", "Odd", "Even"};
    static int[] typeOfNumberQuestion;
    static int[] userAnswers;
    static int[] checkUserAnswers;
    static int[] correctِِِِِِِAnswers;
    static int numberOfCharacters;
    static int counterNumber = 0;
    
    public static void main(String[] args) {
        numberOfQuestions = showNumberQuestionMessage();

        randomStrings = new String[numberOfQuestions];
        typeOfNumberQuestion = new int[numberOfQuestions];
        userAnswers = new int[numberOfQuestions];
        correctِِِِِِِAnswers = new int[numberOfQuestions];
        checkUserAnswers = new int[numberOfQuestions];
        showMargins();
        numberOfCharacters = getNumberOfCharacters();
        showQuestionMessage();
    }

    public static int showNumberQuestionMessage() {
        System.out.println("Please enter the maximum number of questions");
        try {
            int number = reader.nextInt();
            if (number < 1) {
                throw new Exception();
            } else {
                return number;
            }
        } catch (InputMismatchException ex) {
            reader = new Scanner(System.in);
            return showNumberQuestionMessage();
        } catch (Exception ex) {
            return showNumberQuestionMessage();
        }
    }

    public static void showMargins() {
        System.out.println("==================================");
    }

    public static int getNumberOfCharacters() {
        System.out.println("Please enter an integer value between 3 and 100 (the number of characters from which to enumerate certain (Odd/Even?Primary) numbers -- Degree of difficulty)");
        try {
            int numberOfCharacters = reader.nextInt();
            if (numberOfCharacters >= 3 && numberOfCharacters <= 100) {
                return numberOfCharacters;
            } else {
                throw new Exception();
            }
        } catch (InputMismatchException ex) {
            reader = new Scanner(System.in);
            return getNumberOfCharacters();
        } catch (Exception ex) {
            return getNumberOfCharacters();
        }
    }

    public static String generateRandomString(int numberOfCurrentQuestion) {
        String allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int allCharLength = allCharacters.length();
        String RandomString = "";
        for (int i = 1; i <= numberOfCharacters; i++) {
            //get random index from allCharacters
            int index = (int) (Math.random() * (allCharLength - 0) + 0);
            char c = allCharacters.charAt(index);
            //get char from random index and set in string 
            RandomString += c;
            if (Character.isDigit(c)) {
                calculateNumbers(c, numberOfCurrentQuestion);
            }
        }
        correctِِِِِِِAnswers[numberOfCurrentQuestion] = counterNumber;
        counterNumber = 0;
        return RandomString;
    }

    public static void showQuestionMessage() {
        int i = 0;
        int index = 0;
        while (i < numberOfQuestions) {
            if (randomStrings[i] == null && typeOfNumberQuestion[i] == 0) {
                index = new Random().nextInt(3);
                randomStrings[i] = generateRandomString(i);
                typeOfNumberQuestion[i] = index;
            }
            System.out.printf("How many times %s numbers appear in the following characters:%n %s %n To ignore the question type ignore%n",
                    typeMathNumbers[index], randomStrings[i]);

            try {
                String userAnswer = reader.next();
                if (userAnswer.equalsIgnoreCase("ignore")) {
                    userAnswers[i] = 0;
                    System.out.println("Ignored");
                } else {
                    userAnswers[i] = Integer.parseInt(userAnswer);
                }
                checkAnswers(i);
            } catch (InputMismatchException ex) {
                //to avoid calls forever
                reader = new Scanner(System.in);
                continue;
            } catch (Exception ex) {
                continue;
            }
            i++;

        }
    }

    public static void calculateNumbers(char c, int numberOfCurrentQuestion) {
        int typeNumber = typeOfNumberQuestion[numberOfCurrentQuestion];
        // - '0' because we will get ascii value when we do this.. we will get the real number
        int number = (c - '0');
        //even type
        switch (typeNumber) {
            case 2 -> {
                if (number % 2 == 0) {
                    counterNumber++;
                }
            }
            case 1 -> {
                if (number % 2 != 0) {
                    counterNumber++;
                }
            }
            default -> {
                if (isPrimary(number)) {
                    counterNumber++;
                }
            }
        }
    }

    public static boolean isPrimary(int number) {
        if (number == 2 || number == 3) {
            return true;
        } //if number is even or 1 it is not primary
        else if (number % 2 == 0 || number == 1) {
            return false;
        } else {
            //if number % anyOddNumber == 0 is not primary.. 
            //if i == number or i > number that mean the number is 3 or 2 so do'nt loop
            for (int i = 3; i < 9; i += 2) {
                if (number % i == 0 && number != i) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkAnswers(int numberOfCurrentQuestion) {
        if (userAnswers[numberOfCurrentQuestion] == correctِِِِِِِAnswers[numberOfCurrentQuestion]) {
            checkUserAnswers[numberOfCurrentQuestion] = 1;
        } else {
            checkUserAnswers[numberOfCurrentQuestion] = 0;
        }

    }
}
