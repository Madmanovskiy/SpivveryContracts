package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.List;

@DatabaseTable(tableName = "Contracts")
public class Pivot extends BasicContract {

    @DatabaseField
    double pivot;
    @DatabaseField
    double lowStrike;
    @DatabaseField
    double highStrike;

    public Pivot(){

    }

    public Pivot(String bank, Date dateStart, Date dateFinish, String buyAsset, String sellAsset, double assetValue, int leverage, double spotRef,
                 double pivot, double lowStrike, double highStrike) {
        this.setBank(bank);
        this.setContractType("PIV");
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.setBuyAsset(buyAsset);
        this.setSellAsset(sellAsset);
        this.setAssetValue(assetValue);
        this.setLeverage(leverage);
        this.setSpotRef(spotRef);
        this.setIsClose(false);
        this.setLowStrike(lowStrike);
        this.setHighStrike(highStrike);
        this.setPivot(pivot);
        this.setContractId();
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
    public double calculationResult(List<Deal> deals) {
        double result = 0d;
        for (Deal d : deals){
            double pr = d.getSpotPrice();
            if (pr >= pivot) {
                result += transactionsVolume(pr) * (highStrike - pr);
            } else {
                result += transactionsVolume(pr) * (pr - lowStrike);
            }
        }
        return result;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public double getHighStrike() {
        return highStrike;
    }

    public double getPivot() {
        return pivot;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public void setPivot(double pivot) {
        this.pivot = pivot;
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
