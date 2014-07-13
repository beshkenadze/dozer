package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Eq extends DefaultCondition {
    public Eq(String field, String value) {
        super(field, " = ", value);
    }

    public static Eq from(String field, String value) {
        return new Eq(field, value);
    }
}
