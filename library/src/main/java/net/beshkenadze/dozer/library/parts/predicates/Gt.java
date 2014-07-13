package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Gt extends DefaultCondition {
    public Gt(String field, String value) {
        super(field, " > ", value);
    }

    public static Gt from(String field, String value) {
        return new Gt(field, value);
    }
}
