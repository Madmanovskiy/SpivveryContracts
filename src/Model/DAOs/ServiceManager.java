package Model.DAOs;

import Model.Contracts.*;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

//nahuy nujen etot Manager?
//mb need interface for DAOobject with identical method
//http://habrahabr.ru/post/143431/
//http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_3.html#SEC37
//http://devcolibri.com/3438

//http://devcolibri.com/149
public final class ServiceManager {

    private static ServiceManager instance;
    private final String url;
    private ConnectionSource source;

    private BasicContractDAO bcDAO;
    private DealDAO dealDAO;

    private ServiceManager(String dbDistanation) {
        this.url = "jdbc:sqlite:" + dbDistanation;
        try {
            source = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ServiceManager getInstance(String url) {
        if (instance == null) {
            instance = new ServiceManager(url);
        }
        return instance;
    }

    public ConnectionSource getSource() {
        return source;
    }

    public BasicContractDAO getBasicContractDAO() throws SQLException {
        if (bcDAO == null) {
            bcDAO = new BasicContractDAO(source, Accumulator.class);
        }

        return bcDAO;
    }


    public DealDAO getDealDAO() throws SQLException {
        if (dealDAO == null) {
            dealDAO = new DealDAO(source, Deal.class);
        }

        return dealDAO;
    }

    public static void main(String[] args) throws SQLException {
        Accumulator ac1 = new Accumulator("BNP_", Date.valueOf("2015-08-25"), Date.valueOf("2016-08-25"), "EUR", "USD", 500000d, 2, 1.3345d,
                1.33d, 1.50d);
//
//        Pivot piv1 = new Pivot("CS__", Date.valueOf("2015-10-25"), Date.valueOf("2016-10-25"), "JPY", "XAU", 10000d, 2, 10.2d,
//                10.5d, 8.1d, 13.2d);
//
        ServiceManager sm = getInstance("spivverydb");
        BasicContractDAO BCdao = sm.getBasicContractDAO();
        DealDAO Ddao = sm.getDealDAO();
//
//        BCdao.addContractToDB(ac1);
//        BCdao.addContractToDB(piv1);
//
//        Ddao.addDealToDB(new Deal(ac1, Date.valueOf("2015-08-31"), "B", 1.3356d, ac1.transactionsVolume(1.3356d)));
//        Ddao.addDealToDB(new Deal(ac1, Date.valueOf("2015-09-07"), "B", 1.3366d, ac1.transactionsVolume(1.3366d)));
//        Ddao.addDealToDB(new Deal(ac1, Date.valueOf("2015-09-14"), "B", 1.3396d, ac1.transactionsVolume(1.3396d)));
//        Ddao.addDealToDB(new Deal(ac1, Date.valueOf("2015-09-21"), "B", 1.3416d, ac1.transactionsVolume(1.3416d)));
//
//        Ddao.addDealToDB(new Deal(piv1, Date.valueOf("2015-11-01"), "S", 11d, ac1.transactionsVolume(11d)));
//        Ddao.addDealToDB(new Deal(piv1, Date.valueOf("2015-11-08"), "S", 12d, ac1.transactionsVolume(12d)));
//        Ddao.addDealToDB(new Deal(piv1, Date.valueOf("2015-11-15"), "S", 15d, ac1.transactionsVolume(15d)));
//        Ddao.addDealToDB(new Deal(piv1, Date.valueOf("2015-11-22"), "S", 18d, ac1.transactionsVolume(18d)));


        List<Accumulator> list = BCdao.queryForEq("contract_id", "BNP_ACCEURUSD25082015");
        for (Accumulator b : list) {
            System.out.println(b);
//            Ddao.addDealToDB(new Deal(b, Date.valueOf("2015-10-01"), "S", 1.3416d, ac1.transactionsVolume(1.3416d)));
//            Ddao.addDealToDB(new Deal(b, Date.valueOf("2015-11-01"), "S", 1.3416d, ac1.transactionsVolume(1.3416d)));

            List<Deal> deals = Ddao.queryForEq("contract_id", "BNP_ACCEURUSD25082015");
            for(Deal d : deals){
                System.out.println(d);
            }
        }
//        List<Deal> list1 = Ddao.getAllDeal();
//        for (Deal d : list1) {
//            System.out.println(d);
//        }

    }
}
