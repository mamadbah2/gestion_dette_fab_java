package com.baf.views;

import java.util.List;
import java.util.Scanner;

import com.baf.data.entities.Debt;
import com.baf.services.impl.DebtServImpl;

public class DebtView {
    private Scanner scanner;
    private DebtServImpl debtServ;

    public DebtView(Scanner scanner, DebtServImpl debtServ) {
        this.scanner = scanner;
        this.debtServ = debtServ;
    }

    public void displayAllPaidDebts() {
        List<Debt> debts = debtServ.getAllPaidDebt();
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void displayAllUnpaidDebts() {
        List<Debt> debts = debtServ.getAllUnpaidDebt();
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void displayAllDebts() {
        List<Debt> debts = debtServ.selectAll();
        for (Debt debt : debts) {
            System.out.println(debt.toString());
        }
    }

    public void archivePaidDebts() {
        System.out.println("Voici la liste des dettes payees...");
        List<Debt> dettes = debtServ.getAllPaidDebt();
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'a ete payee");
        }
        for (Debt dette : dettes) {
            System.out.println(dette.toString());
        }

        System.out.println("voulez-vous les archiver toutes?");
        String reponse = scanner.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            debtServ.archivePaidDebt(dettes);
            System.out.println("Les dettes payees ont ete archives");
        } else if (reponse.equalsIgnoreCase("non")) {
            System.out.println("Entrez l'ID de la dette a archiver");
            int idDebt = scanner.nextInt();
            Debt debt = debtServ.getDebtById(idDebt);
            if (debt!= null) {
                debtServ.archivePaidDebt(List.of(debt));
                System.out.println("La dette avec l'ID " + idDebt + " a ete archivée");
            } else {
                System.out.println("Aucune dette avec l'ID " + idDebt + " trouvee");
            }
        }

    }

    public void createDebt() {
       /*  System.out.println("Entrez les informations de la dette:");
        System.out.println("Le montant de la dette");
        double montant = scanner.nextDouble();
        System.out.println("La date de la dettte");
        String date = scanner.next();
        System.out.println("Les articles de la dette:");
        List<Article> articles = new ArrayList<>();
        while (scanner.hasNextLine()) {} */
    }

}
