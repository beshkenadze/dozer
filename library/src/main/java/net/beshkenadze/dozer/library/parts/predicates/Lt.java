package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Lt extends DefaultCondition {
    public Lt(String field, String value) {
        super(field, " < ", value);
    }

    public static Lt from(String field, String value) {
        return new Lt(field, value);
    }
}
