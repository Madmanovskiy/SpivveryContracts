package Model.Contracts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicContract {
    private final Bank bank;
    private final ContractsType contractsType;
    private final LocalDate dateStart;
    private final LocalDate dateFinish;
    private final Assets buyAsset;
    private final Assets sellAsset;
    private final double assetValue;
    private final int leverage;
    private final double spotRef;
    private final String id;

    private List<Deal> deals = new ArrayList<>();
    private boolean isClose;

    public BasicContract(Bank bank, ContractsType contractsType, LocalDate dateStart, LocalDate dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef) {
        this.bank = bank;
        this.contractsType = contractsType;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.buyAsset = buyAsset;
        this.sellAsset = sellAsset;
        this.assetValue = assetValue;
        this.leverage = leverage;
        this.spotRef = spotRef;
        this.id = bank.toString() +
                contractsType.toString() +
                buyAsset.toString() +
                sellAsset.toString() +
                dateStart.getDayOfMonth() +
                dateStart.getMonth() +
                dateStart.getYear();
//        +OPTIONAL 1 - 2 PARAMETERS;
        isClose = false;
    }

    public Bank getBank() {
        return bank;
    }

    public Assets getBuyAsset() {
        return buyAsset;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public double getAssetValue() {
        return assetValue;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public String getId() {
        return id;
    }

    public int getLeverage() {
        return leverage;
    }

    public boolean isClose() {
        return isClose;
    }

    public boolean IsContractExpired() {
        return getRemainingWeeks(LocalDate.now()) < 0;
    }

    public int getRemainingWeeks(LocalDate fromDate) {
        return (dateFinish.getDayOfYear() - fromDate.getDayOfYear()) / 7;
    }

    public Assets getSellAsset() {
        return sellAsset;
    }

    public double getSpotRef() {
        return spotRef;
    }

    public ContractsType getContractsType() {
        return contractsType;
    }

    public void addDealWithContract(Deal deal) {
        deals.add(deal);
    }

    public abstract double calculationResult();

    public abstract double transactionsVolume(double currentPrice);

    public enum Bank {
        UBS,
        HSBC,
        CS,
        BNP,
        GSB
    }

    public enum ContractsType {
        DIS,
        ACC,
        PIV,
        SPT,
        FRW,
        PUT,
        CAL,
        STK
    }

    public enum Assets {
        XAU,
        XPD,
        XPT,
        USD,
        EUR,
        CHF,
        GBP,
        JPY
    }

}
