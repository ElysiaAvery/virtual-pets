import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;


public class PersonTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void person_instantiatesCorrectly_true() {
    Person testPerson = new Person("Henry", "henry@henry.com");
    assertTrue(testPerson instanceof Person);
  }

  @Test
  public void getName_personInstantiatesWithName_Henry() {
    Person testPerson = new Person("Henry", "henry@henry.com");
    assertEquals("Henry", testPerson.getName());
  }

  @Test
  public void getEmail_personInstantiatesWithEmail_String() {
    Person testPerson = new Person("Henry", "henry@henry.com");
    assertEquals("henry@henry.com", testPerson.getEmail());
  }

  @Test
  public void equals_returnsTrueIfNameAndEmailAreSame_True() {
    Person firstPerson = new Person("henry", "henry@henry.com");
    Person secondPerson = new Person("henry", "henry@henry.com");
    assertTrue(firstPerson.equals(secondPerson));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Person() {
    Person testPerson = new Person("henry", "henry@henry.com");
    testPerson.save();
    assertTrue(Person.all().get(0).equals(testPerson));
  }

  @Test
  public void all_returnsAllInstancesOfPerson_true() {
    Person firstPerson = new Person("henry", "henry@henry.com");
    firstPerson.save();
    Person secondPerson = new Person("henry", "henry@henry.com");
    secondPerson.save();
    assertEquals(true, Person.all().get(0).equals(firstPerson));
    assertEquals(true, Person.all().get(1).equals(secondPerson));
  }

  @Test
  public void save_assignsIdToObject() {
    Person firstPerson = new Person("henry", "henry@henry.com");
    firstPerson.save();
    Person savedPerson = Person.all().get(0);
    assertEquals(firstPerson.getId(), savedPerson.getId());
  }

  @Test
  public void find_returnsPersonWithSameId_secondPerson() {
    Person firstPerson = new Person("henry", "henry@henry.com");
    firstPerson.save();
    Person secondPerson = new Person("henry", "henry@henry.com");
    secondPerson.save();
    assertEquals(Person.find(secondPerson.getId()), secondPerson);
  }

  @Test
  public void getMonsters_retrievesAllMonstersFromDatabase_monstersList() {
    Person testPerson = new Person("Henry", "henry@henry.com");
    testPerson.save();
    Monster firstMonster = new Monster("Bubbles", testPerson.getId());
    firstMonster.save();
    Monster secondMonster = new Monster("Spud", testPerson.getId());
    secondMonster.save();
    Monster[] monsters = new Monster[] { firstMonster, secondMonster };
    assertTrue(testPerson.getMonsters().containsAll(Arrays.asList(monsters)));
  }

}
