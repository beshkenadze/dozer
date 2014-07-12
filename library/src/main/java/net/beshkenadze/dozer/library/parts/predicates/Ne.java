package net.beshkenadze.dozer.library.parts.predicates;

import net.beshkenadze.dozer.library.parts.DefaultCondition;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 12.07.14.
 */
public class Ne extends DefaultCondition {
    public Ne(String target, String value) {
        super(target, " != ", value);
    }

    public static Ne from(String target, String value) {
        return new Ne(target, value);
    }
}
