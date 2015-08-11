package Model.Contracts;


import java.time.LocalDate;

public class Accumulator extends BasicContract {
    private final double lowerStrikeLevel;
    private final double knockOutLevel;

    public Accumulator(Bank bank, ContractsType contractsType, LocalDate dateStart, LocalDate dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double lowerStrikeLevel, double knockOutLevel) {
        super(bank, contractsType, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
        this.lowerStrikeLevel = lowerStrikeLevel;
        this.knockOutLevel = knockOutLevel;
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return lowerStrikeLevel > currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return knockOutLevel <= currentPrice;
    }

    @Override
    public double transactionsVolume(double currentPrice) {
        return isStrikeCrossedDown(currentPrice) ? getAssetValue() * getLeverage() : getAssetValue();
    }

    @Override
    public double calculationResult() {
        double result = 0d;
        for (Deal d : getDeals()){
            if (isKnockOutCrossedUp(d.getAssetsPrice())) break;
            result += (d.getAssetsPrice() - lowerStrikeLevel) * transactionsVolume(d.getAssetsPrice());
        }
        return result;
    }
}
