package Model.Contracts;


import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Accumulator extends BasicContract {

    public Accumulator(){

    }

    public Accumulator(Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef) {
        super(bank, ContractsType.ACC, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
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
            if (isKnockOutCrossedUp(d.getSpotPrice())) break;
            result += (d.getSpotPrice() - strike.getLowStrike()) * transactionsVolume(d.getSpotPrice());
        }
        return result;
    }
}
