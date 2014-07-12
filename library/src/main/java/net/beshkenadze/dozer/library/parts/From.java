package net.beshkenadze.dozer.library.parts;

import android.text.TextUtils;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class From {
    private String mTableName;
    private String mPseudo;

    public String getTable() {
        return mTableName;
    }

    public void setTable(String tableName) {
        mTableName = tableName;
    }

    public void setTable(String tableName, String pseudo) {
        mTableName = tableName;
        mPseudo = pseudo;
    }

    public String toSql() {
        return "FROM " + getTable() + (!TextUtils.isEmpty(mPseudo) ? " as " + mPseudo + " " : " ");
    }

}
