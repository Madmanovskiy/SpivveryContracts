package Model.DAOs;

import Model.Contracts.Pivot;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Date;
import java.sql.SQLException;

//http://habrahabr.ru/post/143431/
//http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_3.html#SEC37
//http://devcolibri.com/3438
//http://devcolibri.com/149

public final class DataBaseManager {

    private static DataBaseManager instance;
    private final String url;
    private ConnectionSource source;
    private AccumulatorDAO accDAO;
    private DecumulatorDAO decDAO;
    private PivotDAO pivDAO;

    private DataBaseManager(String dbDestination) {
        this.url = "jdbc:sqlite:" + dbDestination;
        try {
            source = new JdbcConnectionSource(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseManager getInstance(String url) {
        if (instance == null) {
            instance = new DataBaseManager(url);
        }
        return instance;
    }

    public AccumulatorDAO getAccDAO() throws SQLException {
        if(accDAO == null){
            accDAO = new AccumulatorDAO(source);
        }
        return accDAO;
    }

    public DecumulatorDAO getDecDAO() throws SQLException {
        if(decDAO == null){
            decDAO = new DecumulatorDAO(source);
        }
        return decDAO;
    }

    public PivotDAO getPivDAO() throws SQLException {
        if(pivDAO == null){
            pivDAO = new PivotDAO(source);
        }
        return pivDAO;
    }


}
