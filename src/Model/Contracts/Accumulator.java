package Model.Contracts;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public class Accumulator extends BasicContract {
    @DatabaseField
    double lowStrike;
    @DatabaseField
    double knockout;

    public Accumulator() {

    }

    public Accumulator(String bank, Date dateStart, Date dateFinish, String buyAsset, String sellAsset, double assetValue, int leverage, double spotRef,
                       double lowStrike, double knockout) {
        this.setBank(bank);
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.setBuyAsset(buyAsset);
        this.setSellAsset(sellAsset);
        this.setAssetValue(assetValue);
        this.setLeverage(leverage);
        this.setSpotRef(spotRef);
        this.setLowStrike(lowStrike);
        this.setKnockout(knockout);
        this.setHighStrike(0);
        this.setPivot(0);

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

    @Override
    public double getKnockout() {
        return knockout;
    }

    @Override
    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }

    @Override
    public double getLowStrike() {
        return lowStrike;
    }

    @Override
    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
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
    public void setContractId(String contractId) {
        this.contractId = contractId;
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
    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    @Override
    public void setIsClose(boolean isClose) {
        this.isClose = isClose;
    }

    @Override
    public void setPivot(double pivot) {
        this.pivot = pivot;
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
