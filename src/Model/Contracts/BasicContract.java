package Model.Contracts;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Contracts")
public abstract class BasicContract {
    @DatabaseField(id = true,  columnName = "contract_id")
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

    public boolean isClose() {
        return isClose;
    }

    public int getLeverage() {
        return leverage;
    }

    public String getSellAsset() {
        return sellAsset;
    }

    public double getSpotRef() {
        return spotRef;
    }

    public abstract void setContractId();

    public abstract void setAssetValue(double assetValue);

    public abstract void setBank(String bank);

    public abstract void setBuyAsset(String buyAsset);

    public abstract void setContractsType(String contractsType);

    public abstract void setDateFinish(Date dateFinish);

    public abstract void setDateStart(Date dateStart);

    public abstract void setIsClose(boolean isClose);

    public abstract void setLeverage(int leverage);

    public abstract void setSellAsset(String sellAsset);

    public abstract void setSpotRef(double spotRef);

    public boolean IsContractExpired() {
        return getRemainingWeeks(new Date(new java.util.Date().getTime())) < 0;
    }

    public int getRemainingWeeks(Date fromDate) {
        return (dateFinish.toLocalDate().getDayOfYear() - fromDate.toLocalDate().getDayOfYear()) / 7;
    }

//    public abstract double calculationResult();

    public abstract double transactionsVolume(double currentPrice);

    @Override
    public String toString() {
        return "BasicContract{" +
                "contractId='" + contractId + '\'' +
                ", bank='" + bank + '\'' +
                ", contractsType='" + contractsType + '\'' +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                ", buyAsset='" + buyAsset + '\'' +
                ", sellAsset='" + sellAsset + '\'' +
                ", assetValue=" + assetValue +
                ", leverage=" + leverage +
                ", spotRef=" + spotRef +
                ", isClose=" + isClose +
                '}';
    }
}



