package com.amates.financialmanagerstructured;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GerenteFinanceiro {

    static ArrayList<String> types = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<String> descriptions = new ArrayList<>();
    static ArrayList<Double> values = new ArrayList<>();
    static ArrayList<String> history = new ArrayList<>();
    static ArrayList<LocalDate> trcDate = new ArrayList<>();
    static int maxTsc = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            continueMenu = mainMenu(scanner);
        }
        scanner.close();
    }

    public static boolean mainMenu(Scanner scanner) {

        System.out.printf("%n%n===== GERENTE FINANCEIRO =====%n");
        System.out.printf("1 - Adicionar receita%n");
        System.out.printf("2 - Adicionar despesa%n");
        System.out.printf("3 - Consultar saldo atual%n");
        System.out.printf("4 - Consultar histórico de transações%n");
        System.out.printf("5 - Consultar por tipo de transação%n");
        System.out.printf("6 - Gerar relatório em arquivo%n");
        System.out.printf("7 - Sair%n");
        System.out.printf("%nSua seleção: ");

        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                addIncome(scanner);
                break;
            case 2:
                addExpense(scanner);
                break;
            case 3:
                getBalance();
                break;
            case 4:
                getHistory(scanner);
                break;
            case 5:
                getTypeSummary(scanner);
                break;
            case 6:
                generateReport();
                break;
            case 7:
                return false;
            default:
                System.out.printf("%n%nOPÇÃO INVÁLIDA!");
                break;
        }
        return true;
    }

    public static void addIncome(Scanner scanner) {
        System.out.printf("%n%nINSERIR RECEITA");
        System.out.printf("%nDigite o valor da receita: ");

        setGeneralData(scanner, scanner.nextDouble(), "RECEITA");
    }

    public static void addExpense(Scanner scanner) {
        System.out.printf("%n%nINSERIR DESPESA");
        System.out.printf("%nDigite o valor da despesa: ");

        setGeneralData(scanner, scanner.nextDouble(), "DESPESA");
    }

    public static void setGeneralData(Scanner scanner, double value, String type) {

        if (value <= 0) {
            System.out.printf("%n%nO valor precisa ser maior que zero!");
            return;
        }

        if (type.equals("DESPESA")) values.add(-value);
        else values.add(value);

        types.add(type);
        LocalDate insertDate = LocalDate.now();
        trcDate.add(insertDate);

        System.out.printf("%nDigite a categoria: ");
        categories.add(scanner.next());

        System.out.printf("%nDigite uma descrição: ");
        descriptions.add(scanner.next());

        int added = values.size() - 1;
        addToHistory(insertDate, types.get(added), categories.get(added), values.get(added), descriptions.get(added));

        System.out.printf("%nTransação adicionada com sucesso!");
    }

    public static void getBalance() {
        double balance = 0;

        for (Double value : values) {
            balance += value;
        }

        System.out.printf("%n-> O saldo atual é R$ %.2f%n", balance);
    }

    public static void addToHistory(LocalDate date, String type, String category, double value, String description) {
        history.add("[" + date + "] " + type + " | " + category + " | R$" + value + " | " + description);
    }

    public static void getHistory(Scanner scanner) {
        System.out.printf("%n%nHISTÓRICO DE TRANSAÇÕES %n");

        for (int i = 0; i < values.size(); i++) {
            System.out.printf("%s %n", history.get(i));
        }

        System.out.printf("%n%n--------------------");
        getBalance();
    }

    public static void getTypeSummary(Scanner scanner) {

        double incomeTr = 0;
        double expenseTr = 0;

        for (Double value : values) {
            if (value < 0) expenseTr += value;
            else incomeTr += value;
        }
        System.out.printf("%n%nSALDO POR TIPO %nTotal em receitas: R$%.2f %nTotal em despesas: R$%.2f%n", incomeTr, expenseTr);
    }

    public static void generateReport() {
        String filePath = "history.txt";
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {
            content.append(history.get(i));
            content.append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content.toString());
            System.out.printf("%nHistórico adicionado com sucesso ao arquivo: %s", filePath);
        } catch (IOException e) {
            System.err.printf("%nErro ao escrever no arquivo. Erro: %s", e.getMessage());
        }
    }
}
