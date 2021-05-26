package ua.epam.internetprovider.service;

import ua.epam.internetprovider.db.Transaction;

public abstract class AbstractService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
