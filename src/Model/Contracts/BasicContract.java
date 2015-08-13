package Model.Contracts;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.sql.Date;


public abstract class BasicContract {
    @DatabaseField(id = true, generatedId = false, columnName = "contract_id")
    protected String contractID;
    @DatabaseField
    protected String bank;
    @DatabaseField(columnName = "type")
    protected String contractsType;
    @DatabaseField
    protected long dateStart;
    @DatabaseField
    protected long dateFinish;
    @DatabaseField(columnName = "buy")
    protected String buyAsset;
    @DatabaseField(columnName = "sell")
    protected String sellAsset;
    @DatabaseField(columnName = "value")
    protected double assetValue;
    @DatabaseField
    protected int leverage;
    @DatabaseField
    protected double spotRef;
    @DatabaseField
    protected boolean isClose;

    protected Strike strike;

    @ForeignCollectionField(eager = true)
    protected ForeignCollection<Deal> deals;

    public BasicContract() {

    }

    public BasicContract(Bank bank, ContractsType contractsType, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef, Strike strike) {
        this.bank = bank.toString();
        this.contractsType = contractsType.toString();
        this.dateStart = dateStart.getTime();
        this.dateFinish = dateFinish.getTime();
        this.buyAsset = buyAsset.toString();
        this.sellAsset = sellAsset.toString();
        this.assetValue = assetValue;
        this.leverage = leverage;
        this.spotRef = spotRef;
        this.contractID = bank.toString() +
                contractsType.toString() +
                buyAsset.toString() +
                sellAsset.toString() +
                dateStart.toLocalDate().getDayOfMonth() +
                dateStart.toLocalDate().getMonthValue() +
                dateStart.toLocalDate().getYear();
//        +OPTIONAL 1 - 2 PARAMETERS;
        isClose = false;
        this.strike = strike;
    }


    public double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBuyAsset() {
        return buyAsset;
    }

    public void setBuyAsset(String buyAsset) {
        this.buyAsset = buyAsset;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getContractsType() {
        return contractsType;
    }

    public void setContractsType(String contractsType) {
        this.contractsType = contractsType;
    }

    public long getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(long dateFinish) {
        this.dateFinish = dateFinish;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public ForeignCollection<Deal> getDeals() {
        return deals;
    }

    public void setDeals(ForeignCollection<Deal> deals) {
        this.deals = deals;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setIsClose(boolean isClose) {
        this.isClose = isClose;
    }

    public int getLeverage() {
        return leverage;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    public String getSellAsset() {
        return sellAsset;
    }

    public void setSellAsset(String sellAsset) {
        this.sellAsset = sellAsset;
    }

    public double getSpotRef() {
        return spotRef;
    }

    public void setSpotRef(double spotRef) {
        this.spotRef = spotRef;
    }

    public boolean IsContractExpired() {
        return getRemainingWeeks(new Date(new java.util.Date().getTime())) < 0;
    }

    public int getRemainingWeeks(Date fromDate) {
        return (new Date(dateFinish).toLocalDate().getDayOfYear() - fromDate.toLocalDate().getDayOfYear()) / 7;
    }

    public abstract double calculationResult();

    public abstract double transactionsVolume(double currentPrice);


    public enum Bank {
        UBS_,
        HSBC,
        CS__,
        BNP_,
        GSB_
    }

    public enum ContractsType {
        DEC,
        ACC,
        PIV,
        SPT,
        FRW,
        PUT,
        CAL,
        STK
    }

    public enum Assets {
        XAU,
        XPD,
        XPT,
        USD,
        EUR,
        CHF,
        GBP,
        JPY
    }

}



