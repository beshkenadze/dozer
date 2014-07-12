package net.beshkenadze.dozer.library.parts;

import android.text.TextUtils;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Limit {
    private final String mLimit;
    private final String mOffset;

    public Limit() {
        mOffset = null;
        mLimit = null;
    }

    public Limit(String limit) {
        mLimit = limit;
        mOffset = null;
    }

    public Limit(String limit, String offset) {
        mLimit = limit;
        mOffset = offset;
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        if (TextUtils.isEmpty(mLimit) && TextUtils.isEmpty(mOffset)) return "";
        StringBuilder sql = new StringBuilder("LIMIT");
        sql.append(" ");
        sql.append(mLimit);

        if (!TextUtils.isEmpty(mOffset)) {
            sql.append(", ");
            sql.append(mOffset);
        }
        return sql.toString();
    }
}
