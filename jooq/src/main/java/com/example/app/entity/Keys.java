/*
 * This file is generated by jOOQ.
 */
package com.example.app.entity;


import com.example.app.entity.tables.TblUsers;
import com.example.app.entity.tables.records.TblUsersRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * user.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TblUsersRecord> KEY_TBL_USERS_PRIMARY = Internal.createUniqueKey(TblUsers.TBL_USERS, DSL.name("KEY_tbl_users_PRIMARY"), new TableField[] { TblUsers.TBL_USERS.ID }, true);
}