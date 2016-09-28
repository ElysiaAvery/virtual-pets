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

}
