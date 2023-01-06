package pis.projekt.utils;

import netscape.javascript.JSObject;
import org.json.JSONObject;

public class JSONHandler {
    // TODO: rozpakowac gowno co wychodzi z elastica

  public static int getCountFromJSON(JSONObject json){
      return json.getInt("count");
  }


}
