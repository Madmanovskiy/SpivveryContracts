package Model.Contracts;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.time.LocalDate;


@DatabaseTable(tableName = "Contracts")
public class BasicContract {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "contractID")
    private String contractID;
    @DatabaseField
    private String bank;
    @DatabaseField (columnName = "contractsType")
    private String contractsType;
    @DatabaseField(dataType = DataType.SQL_DATE)
    private Date dateStart;
    @DatabaseField(dataType = DataType.SQL_DATE)
    private Date dateFinish;
    @DatabaseField(columnName = "buy")
    private String buyAsset;
    @DatabaseField(columnName = "sell")
    private String sellAsset;
    @DatabaseField(columnName = "value")
    private double assetValue;
    @DatabaseField
    private int leverage;
    @DatabaseField
    private double spotRef;
    @DatabaseField
    private boolean isClose;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Deal> deals;

    public BasicContract() {

    }

    public BasicContract(int id, Bank bank, ContractsType contractsType, Date dateStart, Date dateFinish, Assets buyAsset, Assets sellAsset, double assetValue, int leverage, double spotRef) {
        this.id = id;
        this.bank = bank.toString();
        this.contractsType = contractsType.toString();
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
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

    public int getId() {
        return id;
    }

    public String getContractID() {
        return contractID;
    }

    public boolean isClose() {
        return isClose;
    }

    public int getLeverage() {
        return leverage;
    }

    public double getSpotRef() {
        return spotRef;
    }

    public boolean IsContractExpired() {
        return getRemainingWeeks(LocalDate.now()) < 0;
    }

    public int getRemainingWeeks(LocalDate fromDate) {
        return (dateFinish.toLocalDate().getDayOfYear() - fromDate.getDayOfYear()) / 7;
    }

    public String getSellAsset() {
        return sellAsset;
    }

    public double calculationResult(){
        throw new UnsupportedOperationException();
    }

    public double transactionsVolume(double currentPrice){
        throw new UnsupportedOperationException();
    }

    public enum Bank {
        UBS,
        HSBC,
        CS,
        BNP,
        GSB
    }

    public enum ContractsType {
        DIS,
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
