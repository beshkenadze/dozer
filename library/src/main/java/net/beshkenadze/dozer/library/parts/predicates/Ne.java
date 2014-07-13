package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 12.07.14.
 */
public class Ne extends DefaultCondition {
    public Ne(String field, String value) {
        super(field, " != ", value);
    }

    public static Ne from(String field, String value) {
        return new Ne(field, value);
    }
}
