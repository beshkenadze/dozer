package net.beshkenadze.dozer.library.parts;

import android.text.TextUtils;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Group {
    private String[] mFields = new String[]{};

    public Group(String[] fields) {
        mFields = fields;
    }

    public Group() {

    }

    public static Group from(String[] fields) {
        return new Group(fields);
    }

    public String[] getFields() {
        return mFields;
    }

    @Override
    public String toString() {
        return toSql();
    }

    private String toSql() {
        if (getFields().length == 0) {
            return "";
        }
        return "GROUP BY" + " " + TextUtils.join(", ", getFields()) + " ";
    }
}
