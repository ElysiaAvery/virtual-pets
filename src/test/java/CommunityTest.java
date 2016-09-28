import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CommunityTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Community_instantiatesCorrectly_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    assertTrue(testCommunity instanceof Community);
  }

  @Test
  public void getName_instantiatesWithName_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    assertEquals("Water Enthusiasts", testCommunity.getName());
  }

  @Test
  public void getDescription_instantiatesWithDescription_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    assertEquals("LOVERS OF ALL THINGS WATER MUNSTERS", testCommunity.getDescription());
  }

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreTheSame_true() {
    Community firstCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    Community secondCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    assertTrue(firstCommunity.equals(secondCommunity));
  }

  @Test
  public void save_insertsInstanceOfCommunityIntoDatabase_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    testCommunity.save();
    assertTrue(Community.all().get(0).equals(testCommunity));
  }

  @Test
  public void all_returnsAllInstancesOfCommunity_true() {
    Community firstCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    firstCommunity.save();
    Community secondCommunity = new Community("Fire Enthusiasts", "LOVERS OF ALL THINGS FYRE MUNSTERS");
    secondCommunity.save();
    assertEquals(true, Community.all().get(0).equals(firstCommunity));
    assertEquals(true, Community.all().get(1).equals(secondCommunity));
  }

  @Test
  public void addPerson_addsPersonToCommunity_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    testCommunity.save();
    Person testPerson = new Person("Bob Ross", "bob@ross.com");
    testPerson.save();
    testCommunity.addPerson(testPerson);
    Person savedPerson = testCommunity.getPersons().get(0);
    assertTrue(testPerson.equals(savedPerson));
  }

  @Test
  public void getPersons_returnsAllInstancesOfPerson_true() {
    Community testCommunity = new Community("Water Enthusiasts", "LOVERS OF ALL THINGS WATER MUNSTERS");
    testCommunity.save();
    Person firstPerson = new Person("Bob Ross", "bob@ross.com");
    firstPerson.save();
    Person secondPerson = new Person("Bob Ross", "bob@ross.com");
    secondPerson.save();
    testCommunity.addPerson(firstPerson);
    testCommunity.addPerson(secondPerson);
    List savedPersons = testCommunity.getPersons();
    assertEquals(savedPersons.size(), 2);
  }
}
