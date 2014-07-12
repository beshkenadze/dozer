package net.beshkenadze.dozer.library.parts;

import android.database.DatabaseUtils;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 12.07.14.
 */
public class DefaultCondition {
    public DefaultCondition(String target, String tag, String value) {
        addField(target, tag, value);
    }

    public DefaultCondition() {

    }

    public static DefaultCondition from(String target, String tag, String value) {
        return new DefaultCondition(target, tag, value);
    }

    private ArrayList<Field> mFields = new ArrayList<Field>();

    public ArrayList<Field> getFields() {
        return mFields;
    }

    public void setFields(ArrayList<Field> fields) {
        mFields = fields;
    }

    public void addField(String value, String tag, String key) {
        if (TextUtils.isEmpty(value) || TextUtils.isEmpty(key) || TextUtils.isEmpty(tag)) return;
        mFields.add(new Field(key, tag, value));
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        if (getFields().size() == 0) {
            return " 1 ";
        }
        int count = 0;
        StringBuilder sql = new StringBuilder("");
        for (Field field :  getFields()) {
            count++;
            String key = field.getKey();
            String tag = field.getTag();
            String value = field.getValue();

            if (key.equals(value)) {
                sql.append(value);
            } else {
                sql.append(value);
                sql.append(tag);
                sql.append(DatabaseUtils.sqlEscapeString(key));
            }
            if (count < getFields().size()) {
                sql.append(" AND ");
            }

        }
        return sql.append(" ").toString();
    }

    static public class Field {
        private String key;
        private String tag;
        private String value;

        public Field(String key, String tag, String value) {
            setKey(key);
            setTag(tag);
            setValue(value);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
