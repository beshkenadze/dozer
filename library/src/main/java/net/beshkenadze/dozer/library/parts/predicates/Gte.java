package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Gte extends DefaultCondition {
    public Gte(String field, String value) {
        super(field, " >= ", value);
    }

    public static Gte from(String field, String value) {
        return new Gte(field, value);
    }
}
