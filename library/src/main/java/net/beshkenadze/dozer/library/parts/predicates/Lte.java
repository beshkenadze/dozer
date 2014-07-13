package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Lte extends DefaultCondition {
    public Lte(String target, String value) {
        super(target, " <= ", value);
    }

    public static Lte from(String target, String value) {
        return new Lte(target, value);
    }
}
