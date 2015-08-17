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
    protected long dateStart;
    @DatabaseField
    protected long dateFinish;
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

    public BasicContract() {

    }

    public BasicContract(Bank bank, ContractsType contractsType, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef,
                         double pivot, double lowStrike, double highStrike, double knockout) {
        this.bank = bank.toString();
        this.contractsType = contractsType.toString();
        this.dateStart = dateStart.getTime();
        this.dateFinish = dateFinish.getTime();
        this.buyAsset = buyAsset.toString();
        this.sellAsset = sellAsset.toString();
        this.assetValue = assetValue;
        this.leverage = leverage;
        this.spotRef = spotRef;

        int tempDay = dateStart.toLocalDate().getDayOfMonth();
        int tempMonth = dateStart.toLocalDate().getMonthValue();

        this.contractId = bank.toString() +
                contractsType.toString() +
                buyAsset.toString() +
                sellAsset.toString() +
                (tempDay < 10 ? ("0" + tempDay) : tempDay) +
                (tempMonth < 10 ? ("0" + tempMonth) : tempMonth) +
                dateStart.toLocalDate().getYear();
//        +OPTIONAL 1 - 2 PARAMETERS;
        isClose = false;
        this.pivot = pivot;
        this.lowStrike = lowStrike;
        this.highStrike = highStrike;
        this.knockout = knockout;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public double getHighStrike() {
        return highStrike;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setIsClose(boolean isClose) {
        this.isClose = isClose;
    }

    public double getKnockout() {
        return knockout;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }

    public int getLeverage() {
        return leverage;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    public double getLowStrike() {
        return lowStrike;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }

    public double getPivot() {
        return pivot;
    }

    public void setPivot(double pivot) {
        this.pivot = pivot;
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



