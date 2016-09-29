import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Person implements DatabaseManagement {
  private String name;
  private String email;
  private int id;

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherPerson){
    if (!(otherPerson instanceof Person)) {
      return false;
    } else {
      Person newPerson = (Person) otherPerson;
      return this.getName().equals(newPerson.getName()) &&
             this.getEmail().equals(newPerson.getEmail());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO persons (name, email) VALUES (:name, :email)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", this.name)
                         .addParameter("email", this.email)
                         .executeUpdate()
                         .getKey();
    }
  }

  public static List<Person> all() {
    String sql = "SELECT * FROM persons";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
                .executeAndFetch(Person.class);
    }
  }

  public static Person find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM persons WHERE id = :id";
      Person person = con.createQuery(sql)
                         .addParameter("id", id)
                         .executeAndFetchFirst(Person.class);
      return person;
    }
  }

  public List<Object> getMonsters() {
    List<Object> allMonsters = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sqlFire = "SELECT * FROM monsters where personId = :id AND type='fire';";
      List<FireMonster> fireMonsters = con.createQuery(sqlFire)
      .addParameter("id", this.id)
      .throwOnMappingFailure(false)
      .executeAndFetch(FireMonster.class);
      allMonsters.addAll(fireMonsters);

      String sqlWater = "SELECT * FROM monsters WHERE personId=:id AND type='water';";
      List<WaterMonster> waterMonsters = con.createQuery(sqlWater)
      .addParameter("id", this.id)
      .throwOnMappingFailure(false)
      .executeAndFetch(WaterMonster.class);
      allMonsters.addAll(waterMonsters);
    }
    return allMonsters;
  }

  public List<Community> getCommunities() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT communities.* FROM persons " +
                   "JOIN persons_communities ON (persons.id = persons_communities.person_id) " +
                   "JOIN communities ON (persons_communities.community_id = communities.id) " +
                   "WHERE persons.id = :id";
      return con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetch(Community.class);
    }
  }

  public void leaveCommunity(Community community) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM persons_communities WHERE community_id = :community_id AND person_id = :person_id";
      con.createQuery(sql)
         .addParameter("community_id", community.getId())
         .addParameter("person_id", this.getId())
         .executeUpdate();
    }
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM persons WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
      String joinDeleteQuery = "DELETE FROM persons_communities WHERE person_id = :personId";
      con.createQuery(joinDeleteQuery)
        .addParameter("personId", this.getId())
        .executeUpdate();
    }
  }

}
