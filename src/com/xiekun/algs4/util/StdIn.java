package com.xiekun.algs4.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StdIn {

    private static Scanner scanner;

    public StdIn(String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            scanner = new Scanner(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from standard input, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from standard input, "
                    + "but no more tokens are available");
        }

    }

    public boolean isEmpty() {
        return !scanner.hasNext();
    }
}
