package com.example.healthy.BD;

public class Constants {
    public static final String ListKey = "list_key";

    // создание базы данных для блокнота
    public static final String _id = "_id";
    public static final String TableName = "WritingNote";
    public static final String Title = "title";
    public static final String Disc = "DiscTitle";
    public static final String DBName = "db.db";
    // не забывать менять версию при изменениях
    public static final int DBVersion = 1;

    public static final String TableStructure =
    "CREATE TABLE " + TableName + " (" + _id + " INTEGER PRIMARY KEY," +
    Title + " TEXT," +
    Disc + " TEXT)";

    public static final String TableDrop = "DROP TABLE IF EXISTS " + TableName;
}
