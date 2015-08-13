package Model.Contracts;


import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Accumulator extends BasicContract {

    public Accumulator(){

    }

    public Accumulator(int id, Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double lowerStrikeLevel, double knockOutLevel, Strike strike) {
        super(bank, ContractsType.ACC, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef, strike);
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return strike.getLowStrike() > currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return strike.getKnockout() <= currentPrice;
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
            result += (d.getAssetsPrice() - strike.getLowStrike()) * transactionsVolume(d.getAssetsPrice());
        }
        return result;
    }
}
