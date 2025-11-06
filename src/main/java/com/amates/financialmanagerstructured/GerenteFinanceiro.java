package com.amates.financialmanagerstructured;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenteFinanceiro {

    static ArrayList<String> types = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<String> descriptions = new ArrayList<>();
    static ArrayList<Double> values = new ArrayList<>();
    static ArrayList<String> history = new ArrayList<>();
    static int maxTsc = 0;

    static void main() {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            continueMenu = mainMenu(scanner);
        }
        scanner.close();
    }

    public static boolean mainMenu(Scanner scanner) {

        System.out.printf("%n%n===== GERENTE FINANCEIRO =====%n");
        System.out.printf("1 - Adicionar Receita%n");
        System.out.printf("2 - Adicionar Despesa%n");
        System.out.printf("3 - Consultar Saldo Atual%n");
        System.out.printf("4 - Consultar Histórico de Transações%n");
        System.out.printf("5 - Consultar Total por Categoria%n");
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
                getBalance(scanner);
                break;
            case 4:
                getHistory(scanner);
                break;
            case 5:
                getCategorySummary(scanner);
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

        if (type.equals("DESPESA")) {
            values.add(-value);
            types.add(type);
        } else {
            values.add(value);
            types.add(type);
        }

        System.out.printf("%nDigite a categoria: ");
        categories.add(scanner.nextLine());

        System.out.printf("%nDigite uma descrição: ");
        descriptions.add(scanner.nextLine());

        int added = values.size() - 1;
        addToHistory(LocalDate.now(), types.get(added), categories.get(added), values.get(added), descriptions.get(added));

        System.out.printf("%nTransação adicionada com sucesso!");
    }




    public static void getBalance(Scanner scanner) {



    }

    public static void addToHistory(LocalDate date, String type, String category, double value, String description) {
        history.add("[" + date + "] " + type + " | " + category + " | " + value + " | " + description + "%n");
    }


    public static void getHistory(Scanner scanner) {


    }


    public static void getCategorySummary(Scanner scanner) {


    }

    public static void generateReport() {


    }


}
