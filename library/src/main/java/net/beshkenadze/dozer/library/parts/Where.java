package net.beshkenadze.dozer.library.parts;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Aleksandr Beshkenadze <beshkenadze@gmail.com> on 12.07.14.
 */
public class Where {
    ArrayList<DefaultCondition> mGroups = new ArrayList<DefaultCondition>();

    private void addGroupConditions(DefaultCondition[] conditions) {
        Collections.addAll(getGroups(), conditions);
    }

    public void addCondition(DefaultCondition condition) {
        if (getGroups().size() == 0) {
            getGroups().add(condition);
        } else {
            getGroups().get(getGroups().size() - 1).getFields().addAll(condition.getFields());
        }
    }

    public void addGroup(DefaultCondition[] conditions) {
        DefaultCondition groupConditions = new DefaultCondition();
        ArrayList<DefaultCondition.Field> fields = new ArrayList<DefaultCondition.Field>();
        for (DefaultCondition condition : conditions) {
            fields.addAll(condition.getFields());
        }

        groupConditions.setFields(fields);
        getGroups().add(groupConditions);
    }

    public ArrayList<DefaultCondition> getGroups() {
        return mGroups;
    }

    public void setGroups(ArrayList<DefaultCondition> mGroups) {
        this.mGroups = mGroups;
    }

    @Override
    public String toString() {
        return toSql();
    }

    public String toSql() {
        if (getGroups().size() == 0) {
            return "";
        }
        StringBuilder sql = new StringBuilder("WHERE").append(" ");
        int count = getGroups().size();


        for (int i = 0; i < count; i++) {

            DefaultCondition group = getGroups().get(i);
            int countField = group.getFields().size();

            if (countField > 1 && count > 1) {
                sql.append("( ").append(group.toString().trim()).append(" )");
            } else {
                sql.append(group.toString().trim());
            }

            if (i < count - 1) {
                sql.append(" OR ");
            }
        }

        return sql.append(" ").toString();
    }

}
