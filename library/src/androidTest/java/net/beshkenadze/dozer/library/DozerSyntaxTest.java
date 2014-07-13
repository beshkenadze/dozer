package net.beshkenadze.dozer.library;

import net.beshkenadze.dozer.library.parts.Like;
import net.beshkenadze.dozer.library.parts.predicates.Eq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.Assertions.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class DozerSyntaxTest {
    private String mTableName;
    private String mLeftJoinTableName;
    private String mLeftJoinTableName2;

    @Before
    public void setup() {
        mTableName = "Countries";
        mLeftJoinTableName = "Languages";
        mLeftJoinTableName2 = "Peoples";
    }

    @Test
    public void testConstructTableName() throws Exception {
        assertThat(new Dozer(mTableName).toSql()).isEqualToIgnoringCase("select * from countries");
    }

    @Test
    public void testConstructPseudoTableName() throws Exception {
        assertThat(new Dozer(mTableName, "c").toSql()).isEqualToIgnoringCase("select * from countries as c");
    }

    @Test
    public void testFrom() throws Exception {
        assertThat(new Dozer().from(mTableName).toSql()).isEqualToIgnoringCase("select * from countries");
    }

    @Test
    public void testFromPseudo() throws Exception {
        assertThat(new Dozer().from(mTableName, "c").toSql()).isEqualToIgnoringCase("select * from countries as c");
    }

    @Test
    public void testSelect() throws Exception {
        assertThat(new Dozer(mTableName).select().toSql()).isEqualToIgnoringCase("select * from countries");
    }

    @Test
    public void testSelectSingleField() throws Exception {
        assertThat(new Dozer(mTableName).select("id").toSql()).isEqualToIgnoringCase("select id from countries");
    }

    @Test
    public void testSelectSinglePseudoField() throws Exception {
        assertThat(new Dozer(mTableName).select("name", "country_name").toSql()).isEqualToIgnoringCase("select name as country_name from countries");
    }

    @Test
    public void testSelectMultipleField() throws Exception {
        assertThat(new Dozer(mTableName).select(new String[]{"id", "name"}).toSql()).isEqualToIgnoringCase("select id, name from countries");
    }

    @Test
    public void testSingleJoin() throws Exception {
        Dozer dozer = new Dozer(mTableName);
        dozer.select(mTableName.concat(".").concat("*")).join(mLeftJoinTableName,
                "country_id",
                mTableName.concat(".").concat("id"));

        assertThat(dozer.toSql()).isEqualToIgnoringCase("select countries.* from countries left join languages on ( languages.country_id = countries.id )");
    }

    @Test
    public void testSinglePseudoJoin() throws Exception {
        Dozer dozer = new Dozer(mTableName);
        dozer.select(mTableName.concat(".").concat("*"))
                .join(new String[]{mLeftJoinTableName, "l"},
                        "country_id",
                        mTableName.concat(".").concat("id"));

        assertThat(dozer.toSql()).isEqualToIgnoringCase("select countries.* from countries left join languages as l on ( l.country_id = countries.id )");
    }

    @Test
    public void testMultipleJoin() throws Exception {
        Dozer dozer = new Dozer(mTableName);
        dozer.leftJoin(mLeftJoinTableName,
                "country_id",
                mTableName.concat(".").concat("id"))
                .leftJoin(mLeftJoinTableName2,
                        "language_id",
                        mLeftJoinTableName.concat(".").concat("id"));

        assertThat(dozer.toSql()).isEqualToIgnoringCase(
                "select * from countries " +
                        "left join languages on ( languages.country_id = countries.id )" +
                        " " +
                        "left join peoples on ( peoples.language_id = languages.id )"
        );
    }

    @Test
    public void testInnerJoin() throws Exception {
        Dozer dozer = new Dozer(mTableName);
        dozer.select(mTableName.concat(".").concat("*")).innerJoin(mLeftJoinTableName,
                "country_id",
                mTableName.concat(".").concat("id"));

        assertThat(dozer.toSql()).isEqualToIgnoringCase("select countries.* from countries inner join languages on ( languages.country_id = countries.id )");
    }

    @Test
    public void testPseudoInnerJoin() throws Exception {
        Dozer dozer = new Dozer(mTableName);
        dozer.select(mTableName.concat(".").concat("*")).innerJoin(new String[]{mLeftJoinTableName, "l"},
                "country_id",
                mTableName.concat(".").concat("id"));

        assertThat(dozer.toSql()).isEqualToIgnoringCase("select countries.* from countries inner join languages as l on ( l.country_id = countries.id )");
    }

    @Test
    public void testIn() throws Exception {
        assertThat(new Dozer().from(mTableName).in("id", new String[]{"1", "2"}).toSql()).isEqualToIgnoringCase("select * from countries where id in ( '1', '2' )");
    }

    @Test
    public void testEq() throws Exception {
        assertThat(new Dozer().from(mTableName).eq("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id = '1'");
    }

    @Test
    public void testNe() throws Exception {
        assertThat(new Dozer().from(mTableName).ne("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id != '1'");
    }

    @Test
    public void testGt() throws Exception {
        assertThat(new Dozer().from(mTableName).gt("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id > '1'");
    }

    @Test
    public void testLt() throws Exception {
        assertThat(new Dozer().from(mTableName).lt("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id < '1'");
    }

    @Test
    public void testGte() throws Exception {
        assertThat(new Dozer().from(mTableName).gte("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id >= '1'");
    }

    @Test
    public void testLte() throws Exception {
        assertThat(new Dozer().from(mTableName).lte("id", "1").toSql()).isEqualToIgnoringCase("select * from countries where id <= '1'");
    }

    @Test
    public void testAndWhere() throws Exception {
        assertThat(new Dozer().from(mTableName).eq("id", "1").like("name", "Russia").toSql()).isEqualToIgnoringCase("select * from countries where id = '1' and name like 'russia'");
    }

    @Test
    public void testOrWhere() throws Exception {
        Dozer dozer = new Dozer().from(mTableName)
                .whereOr(Eq.from("id", "1"))
                .whereOr(Like.from("name", "russia"));
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where id = '1' or name like 'russia'");
    }

    @Test
    public void testOrMultipleWhere() throws Exception {
        Dozer dozer = new Dozer().from(mTableName)
                .whereOr(Eq.from("id", "1"), Like.from("name", "russia"))
                .whereOr(Eq.from("id", "1"), Like.from("name", "russia"));
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where ( id = '1' and name like 'russia' ) or ( id = '1' and name like 'russia' )");
    }

    @Test
    public void testContains() throws Exception {
        assertThat(new Dozer().from(mTableName).contains("name", "Rus").toSql()).isEqualToIgnoringCase("select * from countries where name like '%Rus%'");
    }


    @Test
    public void testStartWith() throws Exception {
        assertThat(new Dozer().from(mTableName).startWith("name", "Rus").toSql()).isEqualToIgnoringCase("select * from countries where name like 'Rus%'");
    }

    @Test
    public void testEndWith() throws Exception {
        assertThat(new Dozer().from(mTableName).endWith("name", "ssia").toSql()).isEqualToIgnoringCase("select * from countries where name like '%ssia'");
    }

    @Test
    public void testOrderBy() throws Exception {
        Dozer dozer = new Dozer().from(mTableName).eq("id", "1").orderByAsc("id").orderByDesc("name");
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where id = '1' order by id asc, name desc");
    }

    @Test
    public void testMultipleFieldsOrderBy() throws Exception {
        Dozer dozer = new Dozer().from(mTableName).eq("id", "1").orderByAsc("id", "name");
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where id = '1' order by id asc, name asc");
    }

    @Test
    public void testMultipleFieldsOrderByDesc() throws Exception {
        Dozer dozer = new Dozer().from(mTableName).eq("id", "1").orderByDesc("id", "name");
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where id = '1' order by id desc, name desc");
    }

    @Test
    public void testGroupBy() throws Exception {
        Dozer dozer = new Dozer().from(mTableName).eq("id", "1").like("name", "Russia").groupBy("id", "name");
        assertThat(dozer
                .toSql()).isEqualToIgnoringCase("select * from countries where id = '1' and name like 'russia' group by id, name");
    }

    @Test
    public void testLimit() throws Exception {
        assertThat(new Dozer(mTableName).limit(1).toSql()).isEqualToIgnoringCase("select * from countries limit 1");
    }

    @Test
    public void testOffset() throws Exception {
        assertThat(new Dozer(mTableName).limit(1, 1).toSql()).isEqualToIgnoringCase("select * from countries limit 1, 1");
    }
}