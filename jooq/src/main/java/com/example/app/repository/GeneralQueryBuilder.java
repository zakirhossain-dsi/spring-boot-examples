package com.example.app.repository;

import com.example.app.config.JooqConfig;
import com.example.app.dto.Order;
import com.example.app.dto.User;
import com.example.app.dto.UserOrder;
import com.example.app.entity.Tables;
import java.util.Collections;

import org.jooq.Cursor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

import static org.jooq.impl.DSL.*;

@Service
public class GeneralQueryBuilder {

    private final JooqConfig jooqConfig;
    private final DSLContext dslContext;


    public GeneralQueryBuilder(JooqConfig jooqConfig,
                               DSLContext dslContext,
                               DataSource dataSource) {
        this.jooqConfig = jooqConfig;
        this.dslContext = dslContext;
    }

    public List<User> fetchUser(){
        return  jooqConfig.getConfiguration().dsl()
                .select(
                        Tables.TBL_USERS.FIRST_NAME,
                        Tables.TBL_USERS.LAST_NAME,
                        Tables.TBL_USERS.EMAIL
                        )
                .from(Tables.TBL_USERS)
                .fetchInto(User.class);
    }

    public List<User> fetchUserLazily(){
        var query = jooqConfig.getConfiguration().dsl()
                .select(
                        Tables.TBL_USERS.FIRST_NAME,
                        Tables.TBL_USERS.LAST_NAME,
                        Tables.TBL_USERS.EMAIL
                )
                .from(Tables.TBL_USERS)
                .fetchSize(2);

        try(var cursor = query.fetchLazy()){
            while(cursor.hasNext()){
                var user = cursor.fetchNextInto(User.class);
                System.out.println(user.getFirstName());
            }
        }

        return Collections.emptyList();
    }

    public List<Order> fetchOrder(){
        return  jooqConfig.getConfiguration().dsl()
                .select(
                        field("product_name"),
                        field("order_amount"),
                        field("user_id")
                )
                .from(table(name("ORDER", "tbl_orders")))
                .fetchInto(Order.class);
    }

    public List<UserOrder> fetchUserOrder(){
        return  jooqConfig.getConfiguration().dsl()
                .select(
                        field("first_name"),
                        field("product_name")
                )
                .from(table(name("USER", "tbl_users")).as("user"))
                .join(table(name("ORDER", "tbl_orders")).as("order"))
                .on(field("user.id").eq(field("order.user_id")))
                .fetchInto(UserOrder.class);
    }

    public String fetchUserOrderInString(){
        return  jooqConfig.getConfiguration().dsl()
                .select(
                        field("first_name"),
                        field("product_name")
                )
                .from(table(name("USER", "tbl_users")).as("user"))
                .join(table(name("ORDER", "tbl_orders")).as("order"))
                .on(field("user.id").eq(field("order.user_id")))
                .fetch().formatJSON();
    }

    public String fetchOrdersByUser(String firstName){
        return  jooqConfig.getConfiguration().dsl()
                .select(
                        field("product_name"),
                        field("order_amount")
                )
                .from(table(name("ORDER", "tbl_orders")))
                .where(field("user_id").in(
                        jooqConfig.getConfiguration().dsl().select(field("id"))
                        .from(table(name("USER", "tbl_users")))
                                .where((field("first_name").eq(firstName)))
                                .fetch()
                ))
                .fetch().formatJSON();
    }
}
