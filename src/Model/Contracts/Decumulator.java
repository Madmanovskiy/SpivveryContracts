package Model.Contracts;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;


@DatabaseTable(tableName = "Contracts")
public class Decumulator extends BasicContract {

    @DatabaseField
    double highStrike;
    double knockout;

    public Decumulator() {

    }

    public Decumulator(String bank, Date dateStart, Date dateFinish, String buyAsset, String sellAsset, double assetValue, int leverage, double spotRef,
                       double highStrike, double knockout) {
        this.setBank(bank);
        this.setContractsType("DEC");
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.setBuyAsset(buyAsset);
        this.setSellAsset(sellAsset);
        this.setAssetValue(assetValue);
        this.setLeverage(leverage);
        this.setSpotRef(spotRef);
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
    public void setContractsType(String contractsType) {
        this.contractsType = contractsType;
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
    public void setDeals(ForeignCollection<Deal> deals) {
        this.deals = deals;
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
}
