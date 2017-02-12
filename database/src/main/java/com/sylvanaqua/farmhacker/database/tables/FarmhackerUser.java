/*
 * This file is generated by jOOQ.
*/
package com.sylvanaqua.farmhacker.database.tables;


import com.sylvanaqua.farmhacker.database.Farmhacker;
import com.sylvanaqua.farmhacker.database.Keys;
import com.sylvanaqua.farmhacker.database.tables.records.FarmhackerUserRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class FarmhackerUser extends TableImpl<FarmhackerUserRecord> {

    private static final long serialVersionUID = 1491841299;

    /**
     * The reference instance of <code>farmhacker.farmhacker_user</code>
     */
    public static final FarmhackerUser FARMHACKER_USER = new FarmhackerUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FarmhackerUserRecord> getRecordType() {
        return FarmhackerUserRecord.class;
    }

    /**
     * The column <code>farmhacker.farmhacker_user.username</code>.
     */
    public final TableField<FarmhackerUserRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.VARCHAR.length(25).nullable(false), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.password</code>.
     */
    public final TableField<FarmhackerUserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.role</code>.
     */
    public final TableField<FarmhackerUserRecord, String> ROLE = createField("role", org.jooq.impl.SQLDataType.VARCHAR.length(25), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.is_eater</code>.
     */
    public final TableField<FarmhackerUserRecord, Integer> IS_EATER = createField("is_eater", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.is_grower</code>.
     */
    public final TableField<FarmhackerUserRecord, Integer> IS_GROWER = createField("is_grower", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.is_facebook_user</code>.
     */
    public final TableField<FarmhackerUserRecord, Integer> IS_FACEBOOK_USER = createField("is_facebook_user", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>farmhacker.farmhacker_user.zip</code>.
     */
    public final TableField<FarmhackerUserRecord, Integer> ZIP = createField("zip", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>farmhacker.farmhacker_user</code> table reference
     */
    public FarmhackerUser() {
        this("farmhacker_user", null);
    }

    /**
     * Create an aliased <code>farmhacker.farmhacker_user</code> table reference
     */
    public FarmhackerUser(String alias) {
        this(alias, FARMHACKER_USER);
    }

    private FarmhackerUser(String alias, Table<FarmhackerUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private FarmhackerUser(String alias, Table<FarmhackerUserRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<FarmhackerUserRecord> getPrimaryKey() {
        return Keys.KEY_FARMHACKER_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<FarmhackerUserRecord>> getKeys() {
        return Arrays.<UniqueKey<FarmhackerUserRecord>>asList(Keys.KEY_FARMHACKER_USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FarmhackerUser as(String alias) {
        return new FarmhackerUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public FarmhackerUser rename(String name) {
        return new FarmhackerUser(name, null);
    }
}
