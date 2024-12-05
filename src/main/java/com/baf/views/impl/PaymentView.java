package com.baf.views.impl;

import java.util.Date;
import java.util.Scanner;

import com.baf.data.entities.Payment;

public class PaymentView extends ViewImpl<Payment> {
    private Scanner scanner;
     

    public PaymentView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Payment saisie() {
       // Étape 1 : Lister les dettes non soldées

        // Étape 2 : Sélectionner une dette
        System.out.println("Sélectionner une dette : ");
        int idDebt = scanner.nextInt();
        if (idDebt < 0 || idDebt > 10) {
            System.out.println("Dette inexistante");
            return null;
        }
        // On recupère la dette dans une variable debt

        // Étape 3 : Saisir le montant du paiement
        System.out.println("Saisir le montant du paiement : ");
        int amount = scanner.nextInt();
        /* if (amount > selectedDebt.getMontantRestant()) {
            System.out.println("Le montant du paiement dépasse le montant restant !");
            return null;
        } */

        // Etape 4 : Créer un objet Payment
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(new Date());
        
        return payment;
    }
}
