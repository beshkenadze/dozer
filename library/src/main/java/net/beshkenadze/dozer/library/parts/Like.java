package net.beshkenadze.dozer.library.parts;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 08.03.14.
 */
public class Like extends DefaultCondition {
    public Like(String target, String value) {
        super(target, " LIKE ", value);
    }

    public static Like from(String target, String value) {
        return new Like(target, value);
    }
}
