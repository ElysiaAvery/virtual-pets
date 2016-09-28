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

}
