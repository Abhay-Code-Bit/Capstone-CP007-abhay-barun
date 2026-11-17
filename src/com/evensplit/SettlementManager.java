
package com.evensplit;

import java.util.ArrayList;
import java.util.List;

public class SettlementManager {

    // Calculate optimized settlements for a group
    public List<Settlement> calculateSettlements(Group group) {

        if (group == null) {
            throw new IllegalArgumentException("Group cannot be null.");
        }

        List<Person> members = group.getMembers();
        List<Expense> expenses = group.getExpenses();

        List<Settlement> settlements = new ArrayList<>();

        if (members.isEmpty()) {
            return settlements;
        }

        // Store each person's net balance in paise
        long[] balances = new long[members.size()];

        // Process every expense
        for (Expense expense : expenses) {

            long amount = Math.round(expense.getAmount() * 100);

            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "Expense amount must be positive."
                );
            }

            Person payer = expense.getPaidBy();

            int payerIndex = members.indexOf(payer);

            if (payerIndex == -1) {
                throw new IllegalArgumentException(
                        "Expense payer is not a group member."
                );
            }

            // Credit the person who paid
            balances[payerIndex] += amount;

            // Split the expense equally in paise
            long share = amount / members.size();
            long remainder = amount % members.size();

            for (int i = 0; i < members.size(); i++) {

                long individualShare = share;

                // Distribute remaining paise
                if (i < remainder) {
                    individualShare++;
                }

                balances[i] -= individualShare;
            }
        }

        // Separate debtors and creditors
        List<Integer> debtors = new ArrayList<>();
        List<Integer> creditors = new ArrayList<>();

        for (int i = 0; i < balances.length; i++) {

            if (balances[i] < 0) {
                debtors.add(i);
            } else if (balances[i] > 0) {
                creditors.add(i);
            }
        }

        // Greedy settlement
        int debtorIndex = 0;
        int creditorIndex = 0;

        while (debtorIndex < debtors.size()
                && creditorIndex < creditors.size()) {

            int debtor = debtors.get(debtorIndex);
            int creditor = creditors.get(creditorIndex);

            long payment = Math.min(
                    -balances[debtor],
                    balances[creditor]
            );

            settlements.add(
                    new Settlement(
                            members.get(debtor),
                            members.get(creditor),
                            payment / 100.0
                    )
            );

            balances[debtor] += payment;
            balances[creditor] -= payment;

            if (balances[debtor] == 0) {
                debtorIndex++;
            }

            if (balances[creditor] == 0) {
                creditorIndex++;
            }
        }

        return settlements;
    }

    // Display settlement results
    public void displaySettlements(List<Settlement> settlements) {

        System.out.println("\n===== SETTLEMENT PLAN =====");

        if (settlements.isEmpty()) {
            System.out.println("No payments are required.");
            return;
        }

        for (Settlement settlement : settlements) {
            System.out.println(settlement);
        }

        System.out.println(
                "\nTotal transactions: " + settlements.size()
        );
    }
}
