package model.persistence;

import org.json.JSONObject;

// Adapted from given JSONExample file
// Represents an object that can be written as a JSON object
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
