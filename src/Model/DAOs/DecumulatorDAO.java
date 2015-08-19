package Model.DAOs;

import Model.Contracts.Decumulator;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maksim on 18.08.2015.
 */
public class DecumulatorDAO extends BaseDaoImpl<Decumulator, String> implements Queryable<Decumulator> {
    protected DecumulatorDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Decumulator.class);
    }

    @Override
    public List<Decumulator> getAllItems() throws SQLException {
        return this.queryForEq("type", "DEC");
    }

    @Override
    public Decumulator getItemById(String id) throws SQLException {
        return this.queryForId(id);
    }

    @Override
    public void addItem(Decumulator accumulator) throws SQLException {
        this.create(accumulator);
    }
}
