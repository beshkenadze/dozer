package net.beshkenadze.dozer.library.parts;

import android.database.DatabaseUtils;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 12.07.14.
 */
public class DefaultCondition {
    public DefaultCondition(String field, String tag, String value) {
        addField(field, tag, value);
    }

    public DefaultCondition(String field, String tag, String value, boolean skipEscape) {
        addField(field, tag, value, skipEscape);
    }

    public DefaultCondition() {

    }

    public static DefaultCondition from(String target, String tag, String value) {
        return new DefaultCondition(target, tag, value);
    }

    public static DefaultCondition from(String target, String tag, String value, boolean skipEscape) {
        return new DefaultCondition(target, tag, value, skipEscape);
    }

    private ArrayList<Field> mFields = new ArrayList<Field>();

    public ArrayList<Field> getFields() {
        return mFields;
    }

    public void setFields(ArrayList<Field> fields) {
        mFields = fields;
    }

    public void addField(String field, String tag, String value) {
        addField(field, tag, value, false);
    }

    public void addField(String field, String tag, String value, boolean skipEscape) {
        if (TextUtils.isEmpty(field) || TextUtils.isEmpty(value) || TextUtils.isEmpty(tag)) return;
        mFields.add(new Field(field, tag, value, skipEscape));
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
        for (Field field : getFields()) {
            count++;
            String fieldName = field.getField();
            String tag = field.getTag();
            String value = field.getValue();

            if (fieldName.equals(value)) {
                sql.append(value);
            } else {
                sql.append(fieldName);
                sql.append(tag);
                sql.append(value);
            }
            if (count < getFields().size()) {
                sql.append(" AND ");
            }

        }
        return sql.append(" ").toString();
    }

    static public class Field {
        private String field;
        private String tag;
        private String value;

        public Field(String key, String tag, String value) {
            setField(key);
            setTag(tag);
            setValue(DatabaseUtils.sqlEscapeString(value));
        }

        public Field(String key, String tag, String value, boolean skipEscape) {
            setField(key);
            setTag(tag);
            if (!skipEscape) {
                value = DatabaseUtils.sqlEscapeString(value);
            }
            setValue(value);
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
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
