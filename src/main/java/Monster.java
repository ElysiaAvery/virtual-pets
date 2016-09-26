import java.util.List;
import org.sql2o.*;

public class Monster {
  private String name;
  private int id;
  private int personId;
  private int foodLevel;
  private int sleepLevel;
  private int playLevel;

  public static final int MAX_FOOD_LEVEL = 3;
  public static final int MAX_SLEEP_LEVEL = 8;
  public static final int MAX_PLAY_LEVEL = 12;
  public static final int MIN_ALL_LEVELS = 0;

  public Monster(String name, int personId) {
    this.name = name;
    this.personId = personId;
    this.foodLevel = MAX_FOOD_LEVEL / 2;
    this.sleepLevel = MAX_SLEEP_LEVEL / 2;
    this.playLevel = MAX_PLAY_LEVEL / 2;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public int getPersonId() {
    return personId;
  }

  public int getFoodLevel() {
    return foodLevel;
  }

  public int getSleepLevel() {
    return sleepLevel;
  }

  public int getPlayLevel() {
    return playLevel;
  }

  @Override
  public boolean equals(Object otherMonster) {
    if (!(otherMonster instanceof Monster)) {
      return false;
    } else {
      Monster newMonster = (Monster) otherMonster;
      return this.getName().equals(newMonster.getName()) &&
             this.getPersonId() == newMonster.getPersonId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO monsters (name, personId) VALUES (:name, :personId)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", name)
                         .addParameter("personId", personId)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<Monster> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM monsters";
      return con.createQuery(sql).executeAndFetch(Monster.class);
    }
  }

  public static Monster find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM monsters where id = :id";
      Monster monster = con.createQuery(sql)
                           .addParameter("id", id)
                           .executeAndFetchFirst(Monster.class);
    return monster;
    }
  }

  public boolean isAlive() {
    if (foodLevel <= MIN_ALL_LEVELS ||
        playLevel <= MIN_ALL_LEVELS ||
        sleepLevel <= MIN_ALL_LEVELS) {
          return false;
        }
      return true;
  }

  public void depleteLevels() {
    playLevel--;
    foodLevel--;
    sleepLevel--;
  }

  public void play() {
    playLevel++;
  }

  public void sleep() {
    sleepLevel++;
  }

  public void feed() {
    foodLevel++;
  }

}
