package bank.presentation;

public abstract class ClientBlueprint {

    private final Teller teller;

    public ClientBlueprint(Teller teller) {
        if (teller == null) {
            throw new IllegalArgumentException("null parameter");
        }
        this.teller = teller;
    }

    protected Teller getTeller() {
        return this.teller;
    }
    public abstract void reportCreateCustomerAccount(boolean status);
    public abstract void reportRemoveCustomerAccount(boolean status);
    public abstract void reportCreateCheckingAccount(boolean status);
    public abstract void reportCreateSavingsAccount(boolean status);
    public abstract void reportRemoveSavingsAccount(boolean status);
    public abstract void reportRemoveCheckingAccount(boolean status);
    public abstract void reportDeposit(boolean status);
    public abstract void reportWithdraw(boolean status);
    public abstract void reportTransfer(boolean status);





}
