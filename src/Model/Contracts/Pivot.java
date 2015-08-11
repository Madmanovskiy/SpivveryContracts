package Model.Contracts;


import java.time.LocalDate;

public class Pivot extends BasicContract {

    private final double higherStrikeLevel;
    private final double lowerStrikeLevel;
    private final double pivotPrice;

    public Pivot(Bank bank, ContractsType contractsType, LocalDate dateStart, LocalDate dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                 double higherStrikeLevel, double lowerStrikeLevel, double pivotPrice) {
        super(bank, contractsType, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
        this.higherStrikeLevel = higherStrikeLevel;
        this.lowerStrikeLevel = lowerStrikeLevel;
        this.pivotPrice = pivotPrice;
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return lowerStrikeLevel > currentPrice;
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return higherStrikeLevel < currentPrice;
    }

    @Override
    public double transactionsVolume(double currentPrice) {
        return isStrikeCrossedUp(currentPrice) || isStrikeCrossedDown(currentPrice) ? getAssetValue() * getLeverage() : getAssetValue();
    }


    @Override
    public double calculationResult() {
        double result = 0d;
        for (Deal d : getDeals()){
            double pr = d.getAssetsPrice();
            if (pr >= pivotPrice) {
                result += transactionsVolume(pr) * (higherStrikeLevel - pr);
            } else {
                result += transactionsVolume(pr) * (pr - lowerStrikeLevel);
            }
        }
        return result;
    }
}
