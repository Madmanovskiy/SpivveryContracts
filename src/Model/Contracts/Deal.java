package Model.Contracts;


import java.time.LocalDate;

public class Deal {
    private final BasicContract contract;
    private final LocalDate dealsDate;
    private final DealsType dealsType;
    private final double assetsPrice;
    private final double volume;

    public Deal(BasicContract contract, LocalDate dealsDate, DealsType dealsType, double assetsPrice, double volume) {
        this.contract = contract;
        this.dealsDate = dealsDate;
        this.dealsType = dealsType;
        this.assetsPrice = assetsPrice;
        this.volume = volume;
    }

    public BasicContract getContract() {
        return contract;
    }

    public LocalDate getDealsDate() {
        return dealsDate;
    }

    public DealsType getDealsType() {
        return dealsType;
    }

    public double getAssetsPrice() {
        return assetsPrice;
    }

    public double getVolume() {
        return volume;
    }

    public enum DealsType {
        BUY,
        SELL
    }
}
