package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Lte extends DefaultCondition {
    public Lte(String field, String value) {
        super(field, " <= ", value);
    }

    public static Lte from(String field, String value) {
        return new Lte(field, value);
    }
}
