package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.List;

@DatabaseTable(tableName = "Contracts")
public class Accumulator extends BasicContract {
    @DatabaseField(columnName = "lowstrike")
    double lowStrike;
    @DatabaseField(columnName = "pivot")
    double knockout;

    public Accumulator() {

    }

    public Accumulator(String bank, Date dateStart, Date dateFinish, String buyAsset, String sellAsset, double assetValue, int leverage, double spotRef,
                       double lowStrike, double knockout) {
        this.setBank(bank);
        this.setContractType("ACC");
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.setBuyAsset(buyAsset);
        this.setSellAsset(sellAsset);
        this.setAssetValue(assetValue);
        this.setLeverage(leverage);
        this.setSpotRef(spotRef);
        this.setIsClose(false);
        this.setLowStrike(lowStrike);
        this.setKnockout(knockout);
        this.setContractId();
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
    public double calculationResult(List<Deal> deals) {
        double result = 0d;
        for (Deal d : deals){
            if (isKnockOutCrossedUp(d.getSpotPrice())) break;
            result += (d.getSpotPrice() - lowStrike) * transactionsVolume(d.getSpotPrice());
        }
        return result;
    }

    public double getKnockout() {
        return knockout;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }

    @Override
    public void setContractId() {
        this.contractId = generatedId();
    }

    @Override
    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    @Override
    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    @Override
    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public void setBuyAsset(String buyAsset) {
        this.buyAsset = buyAsset;
    }

    @Override
    public void setContractType(String contractsType) {
        this.contractType = contractsType;
    }

    @Override
    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Override
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public void setIsClose(boolean isClose) {
        this.isClose = isClose;
    }

    @Override
    public void setSellAsset(String sellAsset) {
        this.sellAsset = sellAsset;
    }

    @Override
    public void setSpotRef(double spotRef) {
        this.spotRef = spotRef;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
