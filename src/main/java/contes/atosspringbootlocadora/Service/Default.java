package contes.atosspringbootlocadora.Service;

import java.util.Scanner;

import contes.atosspringbootlocadora.Strategy;

public class Default implements Strategy{
    
    Scanner scanner;

    public Default(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("");
    }
}
