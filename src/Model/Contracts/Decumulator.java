package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.List;


@DatabaseTable(tableName = "Contracts")
public class Decumulator extends BasicContract {

    @DatabaseField
    double highStrike;
    @DatabaseField
    double knockout;

    public Decumulator() {

    }

    public Decumulator(String bank, Date dateStart, Date dateFinish, String buyAsset, String sellAsset, double assetValue, int leverage, double spotRef,
                       double highStrike, double knockout) {
        this.setBank(bank);
        this.setContractType("DEC");
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.setBuyAsset(buyAsset);
        this.setSellAsset(sellAsset);
        this.setAssetValue(assetValue);
        this.setLeverage(leverage);
        this.setSpotRef(spotRef);
        this.setIsClose(false);
        this.setKnockout(knockout);
        this.setHighStrike(highStrike);
        this.setContractId();

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
    public double calculationResult(List<Deal> deals) {
        double result = 0d;
        for (Deal d : deals){
            if (isKnockOutCrossedUp(d.getSpotPrice())) break;
            result += (highStrike - d.getSpotPrice()) * transactionsVolume(d.getSpotPrice());
        }
        return result;
    }

    public double getHighStrike() {
        return highStrike;
    }

    public double getKnockout() {
        return knockout;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
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
