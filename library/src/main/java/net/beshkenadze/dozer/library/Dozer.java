package net.beshkenadze.dozer.library;

import net.beshkenadze.dozer.library.parts.DefaultCondition;
import net.beshkenadze.dozer.library.parts.From;
import net.beshkenadze.dozer.library.parts.Group;
import net.beshkenadze.dozer.library.parts.Join;
import net.beshkenadze.dozer.library.parts.Like;
import net.beshkenadze.dozer.library.parts.Limit;
import net.beshkenadze.dozer.library.parts.Order;
import net.beshkenadze.dozer.library.parts.Select;
import net.beshkenadze.dozer.library.parts.Where;
import net.beshkenadze.dozer.library.parts.predicates.Eq;
import net.beshkenadze.dozer.library.parts.predicates.Gt;
import net.beshkenadze.dozer.library.parts.predicates.Gte;
import net.beshkenadze.dozer.library.parts.predicates.In;
import net.beshkenadze.dozer.library.parts.predicates.Lt;
import net.beshkenadze.dozer.library.parts.predicates.Lte;
import net.beshkenadze.dozer.library.parts.predicates.Ne;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 11.07.14.
 */
public class Dozer {
    private Select mSelect = new Select();
    private From mFrom = new From();
    private ArrayList<Join> mJoins = new ArrayList<Join>();
    private Where mWhere = new Where();
    private Limit mLimit = new Limit();
    private Order mOrder = new Order();
    private Group mGroup = new Group();

    public Dozer(String tableName) {
        getFrom().setTable(tableName);
    }

    public Dozer(String tableName, String pseudo) {
        getFrom().setTable(tableName, pseudo);
    }

    public Dozer() {

    }

    public Dozer select() {
        HashMap<String, String> fields = new HashMap<String, String>();
        getSelect().setFields(fields);
        return Dozer.this;
    }

    public Dozer select(String name, String pseudo) {
        getSelect().addField(name, pseudo);
        return Dozer.this;
    }

    public Dozer select(String... fields) {
        for (String field : fields) {
            getSelect().addField(field);
        }
        return Dozer.this;
    }

    public Dozer from(String tableName) {
        getFrom().setTable(tableName);
        return Dozer.this;
    }

    public Dozer from(String tableName, String pseudo) {
        getFrom().setTable(tableName, pseudo);
        return Dozer.this;
    }

    public Dozer join(String tableName, String tableField, String targetField) {
        return leftJoin(tableName, tableField, targetField);
    }

    public Dozer join(String tableName[], String tableField, String targetField) {
        return leftJoin(tableName, tableField, targetField);
    }

    public Dozer join(String type, String tableName, String tableField, String targetField) {
        addJoin(new Join(type, tableName, tableField, targetField));
        return Dozer.this;
    }

    public Dozer join(String type, String tableName[], String tableField, String targetField) {
        addJoin(new Join(type, tableName, tableField, targetField));
        return Dozer.this;
    }

    public Dozer leftJoin(String tableName, String tableField, String targetField) {
        return join(Join.LEFT, tableName, tableField, targetField);
    }

    public Dozer leftJoin(String tableName[], String tableField, String targetField) {
        return join(Join.LEFT, tableName, tableField, targetField);
    }

    public Dozer innerJoin(String tableName, String tableField, String targetField) {
        return join(Join.INNER, tableName, tableField, targetField);
    }

    public Dozer innerJoin(String tableNames[], String tableField, String targetField) {
        return join(Join.INNER, tableNames, tableField, targetField);
    }

    public Dozer in(String field, String... values) {
        return where(In.from(field, values));
    }

    public Dozer eq(String field, String value) {
        return where(Eq.from(field, value));
    }

    public Dozer ne(String field, String value) {
        return where(Ne.from(field, value));
    }

    public Dozer lt(String field, String value) {
        return where(Lt.from(field, value));
    }

    public Dozer gt(String field, String value) {
        return where(Gt.from(field, value));
    }

    public Dozer gte(String field, String value) {
        return where(Gte.from(field, value));
    }

    public Dozer lte(String field, String value) {
        return where(Lte.from(field, value));
    }

    public Dozer whereOr(DefaultCondition... conditions) {
        mWhere.addGroup(conditions);
        return Dozer.this;
    }

    public Dozer where(DefaultCondition condition) {
        mWhere.addCondition(condition);
        return Dozer.this;
    }

    public Dozer limit(int count) {
        setLimit(new Limit(String.valueOf(count)));
        return Dozer.this;
    }

    public Dozer limit(int count, int offset) {
        setLimit(new Limit(String.valueOf(count), String.valueOf(offset)));
        return Dozer.this;
    }

    public Dozer like(String target, String value) {
        where(Like.from(target, value));
        return Dozer.this;
    }

    public Dozer contains(String target, String value) {
        where(Like.from(target, "%" + value + "%"));
        return Dozer.this;
    }

    public Dozer startWith(String target, String value) {
        where(Like.from(target, value + "%"));
        return Dozer.this;
    }

    public Dozer endWith(String target, String value) {
        where(Like.from(target, "%" + value));
        return Dozer.this;
    }

    public Dozer orderBy(String field, String sort) {
        mOrder.add(field, sort);
        return Dozer.this;
    }

    public Dozer orderByAsc(String... fields) {
        for (String field : fields) {
            orderByAsc(field);
        }
        return Dozer.this;
    }

    public Dozer orderByAsc(String field) {
        return orderBy(field, "ASC");
    }

    public Dozer orderByDesc(String... fields) {
        for (String field : fields) {
            orderByDesc(field);
        }
        return Dozer.this;
    }

    public Dozer orderByDesc(String field) {
        return orderBy(field, "DESC");
    }

    public Dozer groupBy(String... fields) {
        mGroup = Group.from(fields);
        return Dozer.this;
    }

    public Select getSelect() {
        return mSelect;
    }

    public From getFrom() {
        return mFrom;
    }

    public ArrayList<Join> getJoins() {
        return mJoins;
    }

    public void addJoin(Join join) {
        mJoins.add(join);
    }

    public String getJoin() {
        StringBuilder sql = new StringBuilder();
        for (Join join : getJoins()) {
            sql.append(join);
        }
        return sql.toString();
    }

    private Where getWhere() {
        return mWhere;
    }

    public Order getOrder() {
        return mOrder;
    }

    public Group getGroup() {
        return mGroup;
    }

    public Limit getLimit() {
        return mLimit;
    }

    public void setLimit(Limit limit) {
        mLimit = limit;
    }

    public String toSql() {
        return (getSelect().toSql() + getFrom().toSql() + getJoin() + getWhere() + getGroup() + getOrder() + getLimit().toSql()).trim();
    }

    @Override
    public String toString() {
        return toSql();
    }

}
