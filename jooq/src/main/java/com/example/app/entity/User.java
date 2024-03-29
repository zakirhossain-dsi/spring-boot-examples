/*
 * This file is generated by jOOQ.
 */
package com.example.app.entity;


import com.example.app.entity.tables.TblUsers;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>user</code>
     */
    public static final User USER = new User();

    /**
     * The table <code>user.tbl_users</code>.
     */
    public final TblUsers TBL_USERS = TblUsers.TBL_USERS;

    /**
     * No further instances allowed
     */
    private User() {
        super("user", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            TblUsers.TBL_USERS
        );
    }
}
