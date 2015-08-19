package Model.DAOs;

import Model.Contracts.Pivot;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maksim on 18.08.2015.
 */
public class PivotDAO extends BaseDaoImpl<Pivot, String> implements Queryable<Pivot>{
    protected PivotDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Pivot.class);
    }

    @Override
    public List<Pivot> getAllItems() throws SQLException {
        return this.queryForEq("type", "PIV");
    }

    @Override
    public Pivot getItemById(String id) throws SQLException {
        return this.queryForId(id);
    }

    @Override
    public void addItem(Pivot accumulator) throws SQLException {
        this.create(accumulator);
    }
}
