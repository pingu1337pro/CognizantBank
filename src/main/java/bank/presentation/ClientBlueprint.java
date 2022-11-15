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
    public abstract void reportCreateCustomerAccount(boolean success);
    public abstract void reportGetCustomerAccount(boolean success);



    //TODO: add methods to display messages according to boolean return values of teller methods




}
