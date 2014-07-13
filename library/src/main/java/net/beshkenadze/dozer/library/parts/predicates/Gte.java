package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Gte extends DefaultCondition {
    public Gte(String target, String value) {
        super(target, " >= ", value);
    }

    public static Gte from(String target, String value) {
        return new Gte(target, value);
    }
}
