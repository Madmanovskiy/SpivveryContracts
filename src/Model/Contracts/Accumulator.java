package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Accumulator extends BasicContract {

    public Accumulator(){

    }

    public Accumulator(Bank bank, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                       double lowStrike, double knockout) {
        super(bank, ContractsType.ACC, dateStart, dateFinish, buyAsset, sellAsset, assetValue, leverage, spotRef,0,lowStrike,0,knockout);
    }

    public boolean isStrikeCrossedDown(double currentPrice) {
        return lowStrike > currentPrice;
    }

    public boolean isKnockOutCrossedUp(double currentPrice) {
        return knockout <= currentPrice;
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
            result += (d.getSpotPrice() - lowStrike) * transactionsVolume(d.getSpotPrice());
        }
        return result;
    }

    public double getKnockout() {
        return knockout;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }
}
