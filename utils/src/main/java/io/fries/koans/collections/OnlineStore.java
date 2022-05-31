package io.fries.koans.collections;

import com.google.gson.Gson;

import java.io.InputStreamReader;

import static java.util.Objects.requireNonNull;

public class OnlineStore {
    protected static final Mall mall = new Gson().fromJson(new InputStreamReader(requireNonNull(OnlineStore.class.getClassLoader().getResourceAsStream("inventory.json"))), Mall.class);
}
