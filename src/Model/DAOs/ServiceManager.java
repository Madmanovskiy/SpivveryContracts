package Model.DAOs;

import Model.Contracts.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private ServiceManager(String url) {
        this.url = url;
        try{
            source = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ServiceManager getInstance(String url) {
        if (instance == null) {
            instance = new ServiceManager(url);
        }
        return instance;
    }

    public GenericRawResults<String[]> getAllContractsFromDB(){
        GenericRawResults<String[]> results = null;
        try{
            Dao<BasicContract, String> dao = DaoManager.createDao(source, BasicContract.class);
             results = dao.queryRaw("SELECT * FROM Contracts");
                int i = 0;
        }catch (SQLException e){

        }

        return results;
    }

    public static void main(String[] args) {
        List<Deal>deals = new ArrayList<>();

        Accumulator ac1 = new Accumulator(BasicContract.Bank.BNP_, Date.valueOf("2015-08-25"), Date.valueOf("2016-08-25"), BasicContract.Assets.EUR, BasicContract.Assets.USD, 500000d, 2, 1.3345d);
        ac1.setStrike(new Strike(ac1, 1.3320d, 1.3798d));

        Pivot piv1 = new Pivot(BasicContract.Bank.CS__, Date.valueOf("2015-10-25"), Date.valueOf("2016-10-25"), BasicContract.Assets.JPY, BasicContract.Assets.XAU, 10000d, 2, 10.2d);
        piv1.setStrike(new Strike(piv1, 9d, 11d));

        deals.add(new Deal(0, ac1, Date.valueOf("2015-08-31"), Deal.DealsType.BUY, 1.3356d, ac1.transactionsVolume(1.3356d)));
        deals.add(new Deal(0, ac1, Date.valueOf("2015-09-07"), Deal.DealsType.BUY, 1.3366d, ac1.transactionsVolume(1.3366d)));
        deals.add(new Deal(0, ac1, Date.valueOf("2015-09-14"), Deal.DealsType.BUY, 1.3396d, ac1.transactionsVolume(1.3396d)));
        deals.add(new Deal(0, ac1, Date.valueOf("2015-09-21"), Deal.DealsType.BUY, 1.3416d, ac1.transactionsVolume(1.3416d)));

        deals.add(new Deal(0, piv1, Date.valueOf("2015-11-01"), Deal.DealsType.SELL, 11d, ac1.transactionsVolume(11d)));
        deals.add(new Deal(0, piv1, Date.valueOf("2015-11-08"), Deal.DealsType.SELL, 12d, ac1.transactionsVolume(12d)));
        deals.add(new Deal(0, piv1, Date.valueOf("2015-11-15"), Deal.DealsType.SELL, 15d, ac1.transactionsVolume(15d)));
        deals.add(new Deal(0,piv1, Date.valueOf("2015-11-22"), Deal.DealsType.SELL, 18d, ac1.transactionsVolume(18d)));

        try{
            ServiceManager sm = ServiceManager.getInstance("jdbc:sqlite:spivverydb");
//            sm.addContractToDB(ac1);
//            sm.addContractToDB(piv1);

            for (Deal deal : deals){
//                sm.addDealToDB(deal);
            }

            System.out.println("_______");
            System.out.println("_______");
            for (String[] stArr : sm.getAllContractsFromDB()){
                for (String s : stArr){
                    System.out.print(s + " ");
                }
                System.out.println();

            }

        }finally{}
//         catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


}
