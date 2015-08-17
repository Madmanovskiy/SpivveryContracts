package Model.Contracts;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public abstract class BasicContract {
    @DatabaseField(id = true, generatedId = false, columnName = "contract_id")
    protected String contractId;
    @DatabaseField
    protected String bank;
    @DatabaseField(columnName = "type")
    protected String contractsType;
    @DatabaseField
    protected Date dateStart;
    @DatabaseField
    protected Date dateFinish;
    @DatabaseField
    protected String buyAsset;
    @DatabaseField
    protected String sellAsset;
    @DatabaseField
    protected double assetValue;
    @DatabaseField
    protected int leverage;
    @DatabaseField
    protected double spotRef;
    @DatabaseField
    protected boolean isClose;
    @DatabaseField
    protected double pivot;
    @DatabaseField
    protected double lowStrike;
    @DatabaseField
    protected double highStrike;
    @DatabaseField
    protected double knockout;

    @ForeignCollectionField(eager = true)
    protected ForeignCollection<Deal> deals;

    protected String generatedId() {
        int tempDay = dateStart.toLocalDate().getDayOfMonth();
        int tempMonth = dateStart.toLocalDate().getMonthValue();

        return bank +
                contractsType +
                buyAsset +
                sellAsset +
                (tempDay < 10 ? ("0" + tempDay) : tempDay) +
                (tempMonth < 10 ? ("0" + tempMonth) : tempMonth) +
                dateStart.toLocalDate().getYear();
    }

    public double getAssetValue() {
        return assetValue;
    }

    public String getBank() {
        return bank;
    }

    public String getBuyAsset() {
        return buyAsset;
    }

    public String getContractId() {
        return contractId;
    }

    public String getContractsType() {
        return contractsType;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public ForeignCollection<Deal> getDeals() {
        return deals;
    }

    public double getHighStrike() {
        return highStrike;
    }

    public boolean isClose() {
        return isClose;
    }

    public double getKnockout() {
        return knockout;
    }

    public int getLeverage() {
        return leverage;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public double getPivot() {
        return pivot;
    }

    public String getSellAsset() {
        return sellAsset;
    }

    public double getSpotRef() {
        return spotRef;
    }

    public abstract void setAssetValue(double assetValue);

    public abstract void setBank(String bank);

    public abstract void setBuyAsset(String buyAsset);

    public abstract void setContractId(String contractId);

    public abstract void setContractsType(String contractsType);

    public abstract void setDateFinish(Date dateFinish);

    public abstract void setDateStart(Date dateStart);

    public abstract void setDeals(ForeignCollection<Deal> deals);

    public abstract void setHighStrike(double highStrike);

    public abstract void setIsClose(boolean isClose);

    public abstract void setKnockout(double knockout);

    public abstract void setLeverage(int leverage);

    public abstract void setLowStrike(double lowStrike);

    public abstract void setPivot(double pivot);

    public abstract void setSellAsset(String sellAsset);

    public abstract void setSpotRef(double spotRef);

    public boolean IsContractExpired() {
        return getRemainingWeeks(new Date(new java.util.Date().getTime())) < 0;
    }

    public int getRemainingWeeks(Date fromDate) {
        return (dateFinish.toLocalDate().getDayOfYear() - fromDate.toLocalDate().getDayOfYear()) / 7;
    }

    public abstract double calculationResult();

    public abstract double transactionsVolume(double currentPrice);

}



