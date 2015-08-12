package Model.Contracts;


import java.sql.Date;

public class Accumulator extends BasicContract {
    private final double lowerStrikeLevel;
    private final double knockOutLevel;

    public Accumulator(int id,Bank bank, ContractsType contractsType, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double lowerStrikeLevel, double knockOutLevel) {
        super(id, bank, contractsType, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
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
