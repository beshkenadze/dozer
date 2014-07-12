package net.beshkenadze.dozer.library.parts;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Select {
    private HashMap<String, String> mFields = new HashMap<String, String>();

    public Select() {

    }

    public HashMap<String, String> getFields() {
        return mFields;
    }

    public void setFields(HashMap<String, String> fields) {
        mFields = fields;
    }

    public void addField(String value) {
        if (TextUtils.isEmpty(value)) return;
        mFields.put(value, value);
    }


    public void addField(String value, String key) {
        if (TextUtils.isEmpty(value) || TextUtils.isEmpty(key)) return;
        mFields.put(key, value);
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        if (mFields.size() == 0) {
            addField("*");
        }
        int count = 0;
        StringBuilder sql = new StringBuilder("SELECT");
        sql.append(" ");
        for (Map.Entry<String, String> entry : getFields().entrySet()) {
            count++;
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equals(value)) {
                sql.append(value);
            } else {
                sql.append(value);
                sql.append(" as ");
                sql.append(key);
            }
            if (count < getFields().size()) {
                sql.append(", ");
            }

        }
        return sql.append(" ").toString();
    }
}
