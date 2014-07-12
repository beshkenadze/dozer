package net.beshkenadze.dozer.library.parts;

import java.util.HashMap;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Order {
    private final HashMap<String, String> mFields = new HashMap<String, String>();

    public Order(String field, String sort) {
        getFields().put(field, sort);
    }

    public Order() {

    }

    public void add(String field, String sort) {
        getFields().put(field, sort);
    }

    public static Order from(String field, String sort) {
        return new Order(field, sort);
    }

    public HashMap<String, String> getFields() {
        return mFields;
    }

    @Override
    public String toString() {
        return toSql();
    }

    private String toSql() {
        if (getFields().size() == 0) {
            return "";
        }
        StringBuilder sql = new StringBuilder("ORDER BY").append(" ");
        int count = getFields().size();
        int i = 0;

        for (String field : getFields().keySet()) {
            String sort = getFields().get(field);
            sql.append(field).append(" ").append(sort);

            if (i < count - 1) {
                sql.append(", ");
            }
            i++;
        }

        return sql.append(" ").toString();
    }

}
