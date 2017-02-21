/*
 * This file is generated by jOOQ.
*/
package com.sylvanaqua.farmhacker.database.tables;


import com.sylvanaqua.farmhacker.database.Farmhacker;
import com.sylvanaqua.farmhacker.database.Keys;
import com.sylvanaqua.farmhacker.database.tables.records.MarketRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Market extends TableImpl<MarketRecord> {

    private static final long serialVersionUID = 1819564383;

    /**
     * The reference instance of <code>farmhacker.market</code>
     */
    public static final Market MARKET = new Market();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MarketRecord> getRecordType() {
        return MarketRecord.class;
    }

    /**
     * The column <code>farmhacker.market.id</code>.
     */
    public final TableField<MarketRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>farmhacker.market.market_name</code>.
     */
    public final TableField<MarketRecord, String> MARKET_NAME = createField("market_name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * Create a <code>farmhacker.market</code> table reference
     */
    public Market() {
        this("market", null);
    }

    /**
     * Create an aliased <code>farmhacker.market</code> table reference
     */
    public Market(String alias) {
        this(alias, MARKET);
    }

    private Market(String alias, Table<MarketRecord> aliased) {
        this(alias, aliased, null);
    }

    private Market(String alias, Table<MarketRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Farmhacker.FARMHACKER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<MarketRecord, Integer> getIdentity() {
        return Keys.IDENTITY_MARKET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MarketRecord> getPrimaryKey() {
        return Keys.KEY_MARKET_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MarketRecord>> getKeys() {
        return Arrays.<UniqueKey<MarketRecord>>asList(Keys.KEY_MARKET_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Market as(String alias) {
        return new Market(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Market rename(String name) {
        return new Market(name, null);
    }
}