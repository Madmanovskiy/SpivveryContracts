package Model.Contracts;


import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;


@DatabaseTable(tableName = "Contracts")
public class Decumulator extends BasicContract {

    public Decumulator(){

    }

    public Decumulator( Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double higherStrikeLevel, double knockOutLevel, Strike strike) {
        super(bank, ContractsType.DEC, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef, strike);
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return strike.getHighStrike() < currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return strike.getKnockout() >= currentPrice;
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
            result += (strike.getHighStrike() - d.getAssetsPrice()) * transactionsVolume(d.getAssetsPrice());
        }
        return result;
    }
}
