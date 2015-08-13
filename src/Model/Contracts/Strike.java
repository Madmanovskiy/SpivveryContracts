package Model.Contracts;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Maksim on 13.08.2015.
 */
@DatabaseTable(tableName = "Strikes")
public class Strike {

    @DatabaseField(columnName = "strike_id", id = true, generatedId = true)
    protected int strikeId;
    @DatabaseField(columnName = "contractsID", foreign = true)
    protected BasicContract basicContract;
    @DatabaseField(columnName = "upstrike")
    protected double highStrike;
    @DatabaseField(columnName = "downstrike")
    protected double lowStrike;
    @DatabaseField(columnName = "knockout")
    protected double knockout;
    @DatabaseField(columnName = "pivotlevel")
    protected double pivot;

    public Strike() {

    }

    public Strike(BasicContract basicContract, double strike1, double strike2orKnockOut) {
        if (basicContract instanceof Accumulator) {
            lowStrike = strike1;
            knockout = strike2orKnockOut;
        } else if (basicContract instanceof Decumulator) {
            highStrike = strike1;
            knockout = strike2orKnockOut;
        } else if (basicContract instanceof Pivot) {
            lowStrike = strike1;
            highStrike = strike2orKnockOut;
        }
        this.basicContract = basicContract;
    }

    public Strike(BasicContract basicContract, double strike1, double strike2, double pivot) {
        lowStrike = strike1;
        highStrike = strike2;
        pivot = 0;
        this.basicContract = basicContract;
    }

    public BasicContract getBasicContract() {
        return basicContract;
    }

    public double getLowStrike() {
        return isStrikeZero(lowStrike);
    }

    public double getKnockout() {
        return isStrikeZero(knockout);
    }

    public int getStrikeId() {
        return strikeId;
    }

    public double getHighStrike() {
        return isStrikeZero(highStrike);
    }

    public double getPivot() {
        return isStrikeZero(pivot);
    }

    public void setBasicContract(BasicContract basicContract) {
        this.basicContract = basicContract;
    }

    public void setHighStrike(double highStrike) {
        this.highStrike = highStrike;
    }

    public void setKnockout(double knockout) {
        this.knockout = knockout;
    }

    public void setLowStrike(double lowStrike) {
        this.lowStrike = lowStrike;
    }

    public void setPivot(double pivot) {
        this.pivot = pivot;
    }

    public void setStrikeId(int strikeId) {
        this.strikeId = strikeId;
    }

    private double isStrikeZero(double value) {
        if (value == 0d) {
            throw new IllegalArgumentException("Strike doesn't correspond to this contract. Value is: " + value);
        }
        return value;
    }

}
