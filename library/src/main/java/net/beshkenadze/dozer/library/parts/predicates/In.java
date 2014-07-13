package net.beshkenadze.dozer.library.parts.predicates;

import android.database.DatabaseUtils;
import android.text.TextUtils;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class In extends DefaultCondition {
    public In(String field, String value) {
        super(field, " IN ", value, true);
    }

    public static In from(String field, String... values) {
        String[] escapeString = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            escapeString[i] = DatabaseUtils.sqlEscapeString(values[i]);
        }
        return new In(field, "( " + TextUtils.join(", ", escapeString) + " )");
    }
}
