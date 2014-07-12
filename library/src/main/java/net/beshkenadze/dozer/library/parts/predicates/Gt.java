package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Gt extends DefaultCondition {
    public Gt(String target, String value) {
        super(target, " > ", value);
    }

    public static Gt from(String target, String value) {
        return new Gt(target, value);
    }
}
