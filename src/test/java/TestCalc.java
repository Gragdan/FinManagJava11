import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestCalc {
    private HashMap<String, String> categoryItems;

    public TestCalc(){
        this.categoryItems = categoryItems;
    }

      @Test
        public void test_OtherItem() throws IOException{
          categoryItems = new HashMap<>();
          categoryItems.put("булка", "еда");
          Calculations calculations = new Calculations();
          String item = "что-нибудь-чего-нет";
          JSONObject jsonBody1 = (JSONObject) calculations.maxCategory(item, 100l, categoryItems)
                  .get("maxCategory");
          String actual = (String) jsonBody1.get("category");
          String expected = "другое";

          Assertions.assertEquals(expected, actual);

        }
    @Test
    public void test_ValidItem() throws IOException {
        categoryItems = new HashMap<>();
        categoryItems.put("булка", "еда");
        Calculations calculations = new Calculations();
        String item = "булка";
        JSONObject jsonBody1 = (JSONObject) calculations.maxCategory(item, 100l, categoryItems)
                .get("maxCategory");
        String actual = (String) jsonBody1.get("category");
        String expected = "еда";

        Assertions.assertEquals(expected, actual);
    }


}
