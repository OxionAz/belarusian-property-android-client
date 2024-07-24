package com.oxionaz.belarussian_property.model.source.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    static final String NAME = "BelarussianProperty"; // we will add the .db extension
    static final int VERSION = 1;
}