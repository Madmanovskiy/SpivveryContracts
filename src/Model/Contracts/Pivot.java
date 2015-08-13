package Model.Contracts;


import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Pivot extends BasicContract {

    public Pivot(){

    }

    public Pivot(Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef) {
        super(bank, ContractsType.PIV, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef);
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return strike.getLowStrike() > currentPrice;
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return strike.getHighStrike()  < currentPrice;
    }

    @Override
    public double transactionsVolume(double currentPrice) {
        return isStrikeCrossedUp(currentPrice) || isStrikeCrossedDown(currentPrice) ? getAssetValue() * getLeverage() : getAssetValue();
    }


    @Override
    public double calculationResult() {
        double result = 0d;
        for (Deal d : getDeals()){
            double pr = d.getSpotPrice();
            if (pr >= strike.getPivot()) {
                result += transactionsVolume(pr) * (strike.getHighStrike() - pr);
            } else {
                result += transactionsVolume(pr) * (pr - strike.getLowStrike());
            }
        }
        return result;
    }
}
