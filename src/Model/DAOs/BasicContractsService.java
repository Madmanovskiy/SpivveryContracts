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

    public boolean isIdconsists(int id) throws SQLException {
        return dao.idExists(String.valueOf(id));
    }


    public static void main(String[] args) {
        BasicContract ac = new BasicContract(0, BasicContract.Bank.HSBC, BasicContract.ContractsType.ACC, new Date(System.currentTimeMillis()), Date.valueOf("2015-10-21"),
                BasicContract.Assets.CHF, BasicContract.Assets.EUR, 500000d, 2, 1.5034d);

        BasicContract ac1 = new BasicContract(0, BasicContract.Bank.UBS, BasicContract.ContractsType.ACC, new Date(System.currentTimeMillis()), Date.valueOf("2015-10-21"),
                BasicContract.Assets.CHF, BasicContract.Assets.EUR, 500000d, 2, 1.5034d);

        Deal deal1 = new Deal(0, ac, Date.valueOf("2015-09-11"), Deal.DealsType.BUY, 1.6060d, 500000d);
        Deal deal2 = new Deal(0, ac, Date.valueOf("2015-09-12"), Deal.DealsType.BUY, 1.7060d, 500000d);
        Deal deal3 = new Deal(0, ac, Date.valueOf("2015-09-13"), Deal.DealsType.BUY, 1.8060d, 1000000d);
        Deal deal4 = new Deal(0, ac1, Date.valueOf("2015-09-14"), Deal.DealsType.BUY, 1.9060d, 1000000d);
        Deal deal5 = new Deal(0, ac1, Date.valueOf("2015-09-14"), Deal.DealsType.BUY, 2.9060d, 1000000d);

        try {
            BasicContractsService bcs = new BasicContractsService();
            DealService ds = new DealService();
            bcs.addContractToDB(ac);
            bcs.addContractToDB(ac1);
            ds.addDealToDB(deal1);
            ds.addDealToDB(deal2);
            ds.addDealToDB(deal3);
            ds.addDealToDB(deal4);
            ds.addDealToDB(deal5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
