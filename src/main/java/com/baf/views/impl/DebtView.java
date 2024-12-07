package com.baf.views.impl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.baf.data.entities.Article;
import com.baf.data.entities.Client;
import com.baf.data.entities.Debt;
import com.baf.data.entities.Payment;
import com.baf.services.ArticleService;
import com.baf.services.ClientService;
import com.baf.services.DebtServ;
import com.baf.services.PaymentServ;

public class DebtView extends ViewImpl<Debt> {
    private Scanner scanner;
    private DebtServ debtServ;
    private ArticleService articleService;
    private ArticleView articleView;
    private PaymentServ paymentServ;
    private ClientService clientService;
    private ClientView clientView;
    public DebtView(Scanner scanner, DebtServ debtServ, ArticleService articleService, ArticleView articleView, PaymentServ paymentServ, ClientService clientService, ClientView clientView) {
        this.scanner = scanner;
        this.debtServ = debtServ;
        this.articleService = articleService;
        this.articleView = articleView;
        this.paymentServ = paymentServ;
        this.clientService = clientService;
        this.clientView = clientView;
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
            if (debt != null) {
                debtServ.archivePaidDebt(List.of(debt));
                System.out.println("La dette avec l'ID " + idDebt + " a ete archivée");
            } else {
                System.out.println("Aucune dette avec l'ID " + idDebt + " trouvee");
            }
        }

    }

    public void createDebt() {
        /*
         * System.out.println("Entrez les informations de la dette:");
         * System.out.println("Le montant de la dette");
         * double montant = scanner.nextDouble();
         * System.out.println("La date de la dettte");
         * String date = scanner.next();
         * System.out.println("Les articles de la dette:");
         * List<Article> articles = new ArrayList<>();
         * while (scanner.hasNextLine()) {}
         */
    }

    @Override
    public Debt saisie() {
        Debt debt = new Debt();
        debt.setDate(new java.util.Date());
        Client client = new Client();
        List<Article> articles = articleService.selectAll();
        List<Article> filteredArticles = articleService.selectAll().stream()
                .filter(art -> art.getQteStock() > 0)
                .collect(Collectors.toList());
        articleView.liste(filteredArticles);
        boolean ok = false;
        do {
            System.out.println("Veuillez saisir le numero de telephone du client");
            clientView.liste(clientService.selectAll());
            String tel = scanner.nextLine();
            client = clientService.selectByTel(tel);
            debt.setClient(client);
            if (debt.getClient() == null) {
                System.out.println("Ce client n'existe pas");
            }
            
        } while (debt.getClient() == null);

        do {
            System.out.println("Veuillez saisir l'ID de l'article");
            String libelle = scanner.nextLine();
            Article article = articleService.selectByLibelle(libelle);
            if (article != null) {
                articles.add(article);
                debt.addArticle(article);
                debt.setMount(debt.getMount() + article.getPrix());

            } else {
                System.out.println("Cet article n'existe pas");
            }

            System.out.println("Voulez-vous ajouter un autre article? (oui/non)");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                ok = true;
            } else if (reponse.equalsIgnoreCase("non")) {
                ok = false;
            } else {
                System.out.println("Reponse invalide");
            }

        } while (articles.isEmpty() || ok == true);

        ok = false;
        
        do {
           
            System.out.println("Voulliez-vous ajouter un paiement? (oui/non)");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                Payment payment = new Payment();
                System.out.println("Saisir le montant du paiement : "); 
                payment.setAmount(scanner.nextDouble());
                payment.setDate(new java.util.Date());
                debt.setPaidMount(debt.getPaidMount() + payment.getAmount());
                debt.setRemainingMount(debt.getMount() - payment.getAmount());
                payment.setDebt(debt);
                paymentServ.insert(payment);
                debt.addPayment(payment);
            } else if (reponse.equalsIgnoreCase("non")) {
                ok = false;
            } else {
                System.out.println("Reponse invalide");
            }
        } while (ok == true);
        client.addDebt(debt);
        return debt;
    }

    public void showDetteByClient() {
        System.out.println("Veuillez saisir le numero de telephone du client");
        clientView.liste(clientService.selectAll());
        String tel = scanner.nextLine();
        Client client = clientService.selectByTel(tel);
        if (client != null) {
            List<Debt> dettes = debtServ.getDebtsFromClient(client);
            for (Debt dette : dettes) {
                System.out.println(dette.toString());
                System.out.println("Voulez-vous afficher les articles? (oui/non)");
                String reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    dette.getArticles().forEach(a -> System.out.println(a.toString()));
                }
                System.out.println("Voulez-vous afficher les paiements? (oui/non)");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    dette.getPayments().forEach(p -> System.out.println(p.toString()));
                }
            }
        } else {
            System.out.println("Ce client n'existe pas");
        }
    }
    // public 

}
