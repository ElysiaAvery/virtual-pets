import java.util.Arrays;
import java.util.List;
import org.sql2o.*;

public class Community {
  private int id;
  private String name;
  private String description;

  public Community(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherCommunity) {
    if(!(otherCommunity instanceof Community)) {
      return false;
    } else {
      Community newCommunity = (Community) otherCommunity;
      return this.getName().equals(newCommunity.getName()) &&
             this.getDescription().equals(newCommunity.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO communities (name, description) VALUES (:name, :description)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("description", this.description)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Community> all() {
    try(Connection con = DB.sql2o.open()) {
      String allCommunityQuery = "SELECT * FROM communities";
      return con.createQuery(allCommunityQuery).executeAndFetch(Community.class);
    }
  }

  public void addPerson(Person person) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO persons_communities (person_id, community_id) VALUES (:person_id, :community_id)";
      con.createQuery(sql)
      .addParameter("person_id", person.getId())
      .addParameter("community_id", this.getId())
      .executeUpdate();
    }
  }

  public List<Person> getPersons() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT persons.* FROM communities " +
                   "JOIN persons_communities ON (communities.id = persons_communities.community_id) " +
                   "JOIN persons ON (persons_communities.person_id = persons.id) " +
                   "WHERE communities.id = :id";
      return con.createQuery(sql)
                .addParameter("id", this.id)
                .executeAndFetch(Person.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM communities WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();

      sql = "DELETE FROM persons_communities WHERE community_id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void removePerson(Person person) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM persons_communities WHERE person_id = :id AND community_id = :community_id";
      con.createQuery(sql)
        .addParameter("id", person.getId())
        .addParameter("community_id", this.id)
        .executeUpdate();
    }
  }

}
