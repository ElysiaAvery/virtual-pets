import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;


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

  @Test
  public void monster_instantiatesWithHalfFullPlayLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2));
  }

  @Test
  public void monster_instantiatesWithHalfFullSleepLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2));
  }

  @Test
  public void monster_instantiatesWithHalfFoodPlayLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(testMonster.getFoodLevel(), (Monster.MAX_FOOD_LEVEL / 2));
  }

  @Test
  public void isAlive_confirmsMonsterIsAliveIfAllLevelsAboveMinimum_true() {
    Monster testMonster = new Monster("Bubbles", 1);
    assertEquals(testMonster.isAlive(), true);
  }

  @Test
  public void depleteLevels_reducesAllLevels() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.depleteLevels();
    assertEquals(testMonster.getFoodLevel(), (Monster.MAX_FOOD_LEVEL / 2) - 1);
    assertEquals(testMonster.getSleepLevel(), (Monster.MAX_SLEEP_LEVEL / 2) - 1);
    assertEquals(testMonster.getPlayLevel(), (Monster.MAX_PLAY_LEVEL / 2) - 1);
  }

  @Test
  public void isAlive_recognizesMonsterIsDeadWhenLevelsReachMinimum_false() {
    Monster testMonster = new Monster("Bubbles", 1);
    for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_FOOD_LEVEL; i++){
      testMonster.depleteLevels();
    }
    assertFalse(testMonster.isAlive());
  }

  @Test
  public void play_increasedMonsterPlayLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.play();
    assertTrue(testMonster.getPlayLevel() > (Monster.MAX_PLAY_LEVEL / 2));
  }

  @Test
  public void sleep_increasedMonsterSleepLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.sleep();
    assertTrue(testMonster.getSleepLevel() > (Monster.MAX_SLEEP_LEVEL / 2));
  }

  @Test
  public void feed_increasedMonsterFoodLevel() {
    Monster testMonster = new Monster("Bubbles", 1);
    testMonster.feed();
    assertTrue(testMonster.getFoodLevel() > (Monster.MAX_FOOD_LEVEL / 2));
  }

  @Test
  public void Monster_foodLevelCannotIncreaseBeyondMaxValue() {
    Monster testMonster = new Monster("Bubbles", 1);
    for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_FOOD_LEVEL); i++) {
      try {
        testMonster.feed();
      } catch (UnsupportedOperationException exception){ }
    }
    System.out.println(testMonster.getFoodLevel());
    assertTrue(testMonster.getFoodLevel() <= Monster.MAX_FOOD_LEVEL);
  }

  @Test(expected = UnsupportedOperationException.class)
   public void feed_throwsExceptionIfFoodLevelIsAtMaxValue(){
     Monster testMonster = new Monster("Bubbles", 1);
     for(int i = Monster.MIN_ALL_LEVELS; i <= (Monster.MAX_FOOD_LEVEL); i++){
       testMonster.feed();
     }
   }

   @Test
   public void monster_playLevelCannotIncreaseBeyondMaxValue() {
     Monster testMonster = new Monster("Bubbles", 1);
     for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_PLAY_LEVEL; i++) {
       try {
         testMonster.play();
       } catch (UnsupportedOperationException exception) { }
     }
     assertTrue(testMonster.getPlayLevel() <= Monster.MAX_PLAY_LEVEL);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void play_throwsExceptionIfPlayLevelIsAtMaxValue(){
     Monster testMonster = new Monster("Bubbles", 1);
     for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_PLAY_LEVEL; i++) {
       testMonster.play();
     }
   }

   @Test
   public void Monster_sleepLevelCannotIncreaseBeyondMaxValue() {
     Monster testMonster = new Monster("Bubbles", 1);
     for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_SLEEP_LEVEL; i++) {
       try {
         testMonster.play();
       } catch (UnsupportedOperationException exception) { }
     }
     assertTrue(testMonster.getSleepLevel() <= Monster.MAX_SLEEP_LEVEL);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void sleep_throwsExceptionIfPlayLevelIsAtMaxValue(){
     Monster testMonster = new Monster("Bubbles", 1);
     for(int i = Monster.MIN_ALL_LEVELS; i <= Monster.MAX_SLEEP_LEVEL; i++) {
       testMonster.sleep();
     }
   }

   @Test
   public void save_recordsTimeOfCreationInDatabase() {
     Monster testMonster = new Monster("Bubbles", 1);
     testMonster.save();
     Timestamp savedMonsterBirthday = Monster.find(testMonster.getId()).getBirthday();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(rightNow.getDay(), savedMonsterBirthday.getDay());
   }

   @Test
   public void sleep_recordsTimeLastSleptInDatabase() {
     Monster testMonster = new Monster("Bubbles", 1);
     testMonster.save();
     testMonster.sleep();
     Timestamp savedMonsterLastSlept = Monster.find(testMonster.getId()).getLastSlept();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedMonsterLastSlept));
   }

   @Test
   public void feed_recordsTimeLastAteInDatabase() {
     Monster testMonster = new Monster("Bubbles", 1);
     testMonster.save();
     testMonster.feed();
     Timestamp savedMonsterLastAte = Monster.find(testMonster.getId()).getLastAte();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedMonsterLastAte));
   }

   @Test
   public void play_recordsTimeLastPlayInDatabase() {
     Monster testMonster = new Monster("Bubbles", 1);
     testMonster.save();
     testMonster.play();
     Timestamp savedMonsterLastPlayed = Monster.find(testMonster.getId()).getLastPlayed();
     Timestamp rightNow = new Timestamp(new Date().getTime());
     assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedMonsterLastPlayed));
   }

}
