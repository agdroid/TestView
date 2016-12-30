package com.example.andre.testview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 30.12.2016.
 */

public class DummyData {

  public static List<ListItem> getListData() {
      List<ListItem> data = new ArrayList<ListItem>();

      for (int i = 0; i < 20; i++) {
          ListItem item = new ListItem();
          item.setTitle("Zeile " + i);
          data.add(item);
      }

      return data;

  }
}
