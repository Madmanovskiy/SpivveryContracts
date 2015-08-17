package Model.Contracts;


import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Pivot extends BasicContract {

    public Pivot(){

    }

    public Pivot(Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                 double pivot, double lowStrike, double highStrike) {
        super(bank, ContractsType.PIV, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef, pivot, lowStrike, highStrike, 0);
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return lowStrike > currentPrice;
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return highStrike  < currentPrice;
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
            if (pr >= pivot) {
                result += transactionsVolume(pr) * (highStrike - pr);
            } else {
                result += transactionsVolume(pr) * (pr - lowStrike);
            }
        }
        return result;
    }

    public double getHighStrike() {
        return highStrike;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }

    public double getPivot() {
        return pivot;
    }

    public void setPivot(double pivot) {
        this.pivot = pivot;
    }
}
