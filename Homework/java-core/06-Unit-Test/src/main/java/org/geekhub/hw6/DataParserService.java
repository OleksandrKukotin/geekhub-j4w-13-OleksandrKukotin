package org.geekhub.hw6;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;

public class DataParserService {

    private final Gson gson;

    public DataParserService(Gson gson) {
        this.gson = gson;
    }

    public String parseJsonAsCatFactString(byte[] json) {
        return gson.fromJson(new String(json, StandardCharsets.UTF_8), JsonObject.class).get("fact").getAsString();
    }
}
