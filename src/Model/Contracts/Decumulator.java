package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;


@DatabaseTable(tableName = "Contracts")
public class Decumulator extends BasicContract {
    public Decumulator() {

    }

    public Decumulator(Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double highStrike, double knockout) {
        super(bank, ContractsType.DEC, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef, 0,0,highStrike,knockout);
    }

    public boolean isStrikeCrossedUp(double currentPrice) {
        return highStrike < currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return knockout >= currentPrice;
    }

    @Override
    public double transactionsVolume(double currentPrice) {
        return isStrikeCrossedUp(currentPrice) ? getAssetValue() * getLeverage() : getAssetValue();
    }

    @Override
    public double calculationResult() {
        double result = 0d;
        for (Deal d : getDeals()){
            if (isKnockOutCrossedUp(d.getSpotPrice())) break;
            result += (highStrike - d.getSpotPrice()) * transactionsVolume(d.getSpotPrice());
        }
        return result;
    }

    public double getHighStrike() {
        return highStrike;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public double getKnockout() {
        return knockout;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }
}
