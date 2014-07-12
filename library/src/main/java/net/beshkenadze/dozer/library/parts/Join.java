package net.beshkenadze.dozer.library.parts;

import android.text.TextUtils;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Join {
    public static final String LEFT = "LEFT";
    public static final String INNER = "INNER";
    private final String mType;
    private final String mTableName;
    private final String mTablePseudo;
    private final String mTableField;
    private final String mTargetField;

    public Join(String type, String tableName, String tableField, String targetField) {
        mType = type;
        mTableName = tableName;
        mTablePseudo = "";
        mTableField = tableField;
        mTargetField = targetField;
    }

    public Join(String type, String tableName[], String tableField, String targetField) {
        mType = type;
        if (tableName.length > 1) {
            mTableName = tableName[0];
            mTablePseudo = tableName[1];
        } else {
            mTableName = tableName[0];
            mTablePseudo = "";
        }
        mTableField = tableField;
        mTargetField = targetField;
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        if (TextUtils.isEmpty(mType) || TextUtils.isEmpty(mTableName)
                || TextUtils.isEmpty(mTableField) || TextUtils.isEmpty(mTargetField)) {
            return "";
        }
        return mType + " " + "JOIN" + " " + mTableName + (!TextUtils.isEmpty(mTablePseudo)
                ? " as " + mTablePseudo + " " : " ")
                + "ON" + " " + "( " + (!TextUtils.isEmpty(mTablePseudo)
                ? mTablePseudo : mTableName) + "." + mTableField + " = " + mTargetField + " )" + " ";
    }
}
