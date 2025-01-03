package view;


import model.keyboard.Key;
import model.keyboard.Keyboard;

import java.util.ArrayList;

/*
Color Name	Color code	Background Color	Background Color code
BLACK	    \u001B[30m	BLACK_BACKGROUND	\u001B[40m
RED	        \u001B[31m	RED_BACKGROUND	    \u001B[41m
GREEN	    \u001B[32m	GREEN_BACKGROUND	\u001B[42m
YELLOW	    \u001B[33m	YELLOW_BACKGROUND	\u001B[43m
BLUE	    \u001B[34m	BLUE_BACKGROUND	    \u001B[44m
PURPLE	    \u001B[35m	PURPLE_BACKGROUND	\u001B[45m
CYAN	    \u001B[36m	CYAN_BACKGROUND	    \u001B[46m
WHITE	    \u001B[37m	WHITE_BACKGROUND	\u001B[47m


Dans pdf:
Pinky:         blue
Ring Finger:   cyan
Middle Finger: green
Left Index:    orange
Right Index:   yellow
Thumb:         red

 */
public class DisplayKeyboard
{
    // Backgrounds
    private static final String BG_BLUE = "\u001B[34m";
    private static final String BG_CYAN = "\u001B[36m";
    private static final String BG_GREEN = "\u001B[32m";
    private static final String BG_ORANGE = "\u001B[35m";
    private static final String BG_YELLOW = "\u001B[33m";
    private static final String BG_RED = "\u001B[31m";

    private static final String BG_WHITE = "\u001B[37m";
    private static final String BG_BLACK = "\u001B[40m";
    private static final String BG_RESET = "\u001B[0m";

    // Foregrounds
    private static final String FG_BLACK = "\u001B[40m";
    private static final String FG_WHITE = "\u001B[40m";
    private static final String FG_PURPLE = "\u001B[35m";
    private static final String FG_RESET = "\u001B[0m";



    private static void setColours(String bg, String fg)
    {
        System.out.print(bg + fg);
    }

    /**
     * Resets colours.
     */
    private static void resetColours()
    {
        System.out.print(BG_RESET + FG_RESET);
    }


    /**
     * Prints text with given colours, then resets it.
     * @param bg background colour
     * @param fg text colour
     * @param text the text to print
     */
    private static void writeWStyle(String bg, String fg, String text)
    {
        resetColours();
        setColours(bg, fg);
        System.out.print(text);
        resetColours();
    }


    /**
     * Prints n character
     * @param n char num
     */
    private static void printChar(int n, char c)
    {
        for (int i = 0; i < n; i++)
        {
            System.out.print(c);
        }
    }


    /**
     * Prints |  text  | depending on the parameters.
     * @param bg the background colour of the space and text
     * @param fg the text colour(not the bars, only text)
     * @param text the text to print
     * @param length length of the "box"
     * @param first_bar true if the first bar will be printed
     * @param last_bar true if the last bar will be printed
     */
    private static void printBoxMiddle(String bg, String fg, String text, int length, boolean first_bar, boolean last_bar)
    {
        // Calculate the space between the start-text + text-end
        int margin_total = 0;
        if (text.length() > length)
        {
            text = text.substring(0, length);
        }
        else
        {
            margin_total = length - text.length();
        }

        // Print first bar
        if (first_bar)
        {
            writeWStyle(BG_BLACK, FG_PURPLE, "|");
        }

        // Set colour of the key(bg and fg)
        setColours(bg, fg);

        // Space between the start and the text
        printChar(margin_total/2, ' ');

        // Print text
        System.out.print(text);

        // Print the last spaces. If margin_total isnt divisible by 2, add 1 space
        if (margin_total % 2 == 0)
        {
            printChar(margin_total/2, ' ');
        }
        else
        {
            printChar((margin_total/2) + 1, ' ');
        }

        // Print last bar
        if (last_bar)
        {
            writeWStyle(BG_BLACK, FG_BLACK, "|");
        }

        // Reset the colours
        resetColours();
    }


    /**
     * Essentially prints |     | depending on the parameters.
     * @param bg the space colour
     * @param length length of the "box"
     * @param first_bar true if the first bar will be printed
     * @param last_bar true if the last bar will be printed
     */
    private static void printBoxTop(String bg, int length, boolean first_bar, boolean last_bar)
    {
        // Print first bar
        if (first_bar)
        {
            writeWStyle(BG_BLACK, FG_PURPLE, "|");
        }

        setColours(bg, "");
        printChar(length, ' ');

        // Print last bar
        if (last_bar)
        {
            writeWStyle(BG_BLACK, FG_BLACK, "|");
        }

        resetColours();
    }


    /**
     * Prints |_____| depending on the parameters.
     * @param bg the background colour of the space and text
     * @param fg the text colour(not the bars, only text)
     * @param length length of the "box"
     * @param first_bar true if the first bar will be printed
     * @param last_bar true if the last bar will be printed
     */
    private static void printBoxBottom(String bg, String fg, int length, boolean first_bar, boolean last_bar)
    {
        // Print first bar
        if (first_bar)
        {
            writeWStyle(BG_BLACK, FG_PURPLE, "|");
        }

        setColours(bg, fg);
        printChar(length, '_');

        // Print last bar
        if (last_bar)
        {
            writeWStyle(BG_BLACK, FG_BLACK, "|");
        }

        resetColours();
    }






    private static void printFirstRow(Keyboard keyboard, String mode)
    {
        for (int i = 0; i < 13; i++)
        {
            printBoxBottom(BG_BLACK, FG_WHITE, 3, false, false);
        }
        printBoxBottom(BG_BLACK, FG_WHITE, 6, false, false);

        for (int i = 0; i < 13; i++)
        {
            
        }
    }

}
