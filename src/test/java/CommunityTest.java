import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

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
}
