package Model.DAOs;

import Model.Contracts.BasicContract;
import Model.Contracts.Deal;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BasicContractsService {
    private final String url = "jdbc:sqlite:test.s3db";
    private ConnectionSource source;
    private Dao<BasicContract, String> dao;

    public BasicContractsService() throws SQLException {
        source = new JdbcConnectionSource(url);
        dao = DaoManager.createDao(source, BasicContract.class);
    }

    public List<BasicContract> getAll() throws SQLException {
        return dao.queryForAll();
    }

    public void addContractToDB(BasicContract bc) throws SQLException {
        dao.create(bc);
    }


    public static void main(String[] args) {
        BasicContract ac = new BasicContract(0, BasicContract.Bank.BNP, BasicContract.ContractsType.ACC, new Date(System.currentTimeMillis()), Date.valueOf("2015-10-21"),
                BasicContract.Assets.CHF, BasicContract.Assets.EUR,500000d, 2, 1.5034d);

//        Deal deal1 = new Deal(0,ac,Date.valueOf("2015-08-30"), Deal.DealsType.BUY, 1.5060d, 500000d);

        try{
            BasicContractsService bcs = new BasicContractsService();
            bcs.addContractToDB(ac);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
