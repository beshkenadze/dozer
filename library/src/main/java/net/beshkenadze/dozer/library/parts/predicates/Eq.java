package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Eq extends DefaultCondition {
    public Eq(String target, String value) {
        super(target, " = ", value);
    }

    public static Eq from(String target, String value) {
        return new Eq(target, value);
    }
}
