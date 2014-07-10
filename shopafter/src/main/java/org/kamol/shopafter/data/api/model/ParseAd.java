package org.kamol.shopafter.data.api.model;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("ParseAd")
public class ParseAd extends ParseObject {
  public ParseAd() {
    // A default constructor is required.
  }

  public String getTitle() {
    return getString("title");
  }

  public void setTitle(String title) {
    put("title", title);
  }

  public String getDescription() {
    return getString("description");
  }

  public void setDescription(String description) {
    put("description", description);
  }

  public String getPrice() {
    return getString("price");
  }

  public void setPrice(String price) {
    put("price", price);
  }

  public int getCategory() {
    return getInt("category");
  }

  public void setCategory(int category) {
    put("category", category);
  }

  public String getProfile() {
    return getString("profile");
  }

  public void setProfile(String profile) {
    put("profile", profile);
  }

  public JSONArray getImages() {
    return getJSONArray("images");
  }

  public void setImages(JSONArray images) {
    put("images", images);
  }
}

