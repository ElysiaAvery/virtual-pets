import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;


public class MonsterTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void monster_instantiatesCorrectly_true() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertTrue(testMonster instanceof Monster);
  }

  @Test
  public void getName_instantiatesCorrectlyWithName_true() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals("Bubbles", testMonster.getName());
  }

  @Test
  public void getPersonId_instantiatesCorrectlyWithPersonId_true() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(1, testMonster.getPersonId());
  }

  @Test
  public void equals_returnsTrueIfNameAndPersonIdAreSame_True() {
    Monster testMonster = new Monster("Bubbles", 1);
    Monster anotherMonster = new Monster("Bubbles", 1);
    assertTrue(testMonster.equals(anotherMonster));
  }

  @Test
  public void save_returnsTrueIfDescriptionAreTheSame() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.save();
    assertTrue(Monster.all().get(0).equals(testMonster));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.save();
    Monster savedMonster = Monster.all().get(0);
    assertEquals(savedMonster.getId(), testMonster.getId());
  }

  @Test
  public void all_returnsAllInstancesOfMonster_true() {
    Monster firstMonster = new Monster("Bubbles", 1);
    firstMonster.save();
    Monster secondMonster = new Monster("Sparkles", 1);
    secondMonster.save();
    assertEquals(true, Monster.all().get(0).equals(firstMonster));
    assertEquals(true, Monster.all().get(1).equals(secondMonster));
  }

  @Test
  public void find_returnsMonsterWithSameId_secondMonster() {
    Monster firstMonster = new Monster("Bubbles", 1);
    firstMonster.save();
    Monster secondMonster = new Monster("Sparkles", 1);
    secondMonster.save();
    assertEquals(Monster.find(secondMonster.getId()), secondMonster);
  }

  @Test
  public void save_savesMonsterWithPersonIdIntoDB_true() {
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    Monster testMonster = new Monster("Bubbles", testPerson.getId());
    testMonster.save();
    Monster savedMonster = Monster.find(testMonster.getId());
    assertEquals(savedMonster.getPersonId(), testPerson.getId());
  }
}
