package Model.Contracts;


import java.time.LocalDate;

public class Decumulator extends BasicContract {
    private final double higherStrikeLevel;
    private final double knockOutLevel;

    public Decumulator(Bank bank, ContractsType contractsType, LocalDate dateStart, LocalDate dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double higherStrikeLevel, double knockOutLevel) {
        super(bank, contractsType, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
        this.higherStrikeLevel = higherStrikeLevel;
        this.knockOutLevel = knockOutLevel;
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return higherStrikeLevel < currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return knockOutLevel >= currentPrice;
    }

    @Override
    public double transactionsVolume(double currentPrice) {
        return isStrikeCrossedUp(currentPrice) ? getAssetValue() * getLeverage() : getAssetValue();
    }

    @Override
    public double calculationResult() {
        double result = 0d;
        for (Deal d : getDeals()){
            if (isKnockOutCrossedUp(d.getAssetsPrice())) break;
            result += (higherStrikeLevel - d.getAssetsPrice()) * transactionsVolume(d.getAssetsPrice());
        }
        return result;
    }
}
