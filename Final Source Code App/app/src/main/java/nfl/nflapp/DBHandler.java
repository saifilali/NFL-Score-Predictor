package nfl.nflapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = setup.workingSeason;
    private static final String TABLE_WEEKS = "week1";
    private static final String KEY_ID = "id";
    private static final String KEY_TEAM1 = "team1";
    private static final String KEY_TEAM2 = "team2";
    private static final String KEY_MARGIN = "margin";
    private static final String KEY_WEEK = "week";

    static List<String> gameByeWeekList = new ArrayList<>();
    static List<Integer> ByeWeekList = new ArrayList<>();
    Scanner input;

    public DBHandler(Context context, Scanner in) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        input = in;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_WEEKS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TEAM1 + " TEXT, "
                + KEY_TEAM2 + " TEXT, "
                + KEY_WEEK + " INTEGER, "
                + KEY_MARGIN + " INTEGER );";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEKS);

        // Creating tables again
        onCreate(db);
    }

    public void readGames() {
        if(getAllGames().isEmpty()) {
            while (input.hasNext()) {
                Game g = Game.read(input);
                addGame(g);
            }
        }
        input.close();
    }

    // Adding new game
    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM1, game.getTeam1()); // Team 1 Name
        values.put(KEY_TEAM2, game.getTeam2()); // Team 2 Name
        values.put(KEY_MARGIN, game.getMargin()); // Score Margin
        values.put(KEY_WEEK, game.getWeek());

        if(game.byeWeek()) {
            gameByeWeekList.add(game.getTeam1());
            ByeWeekList.add(game.getWeek());
        }
        else {
            // Inserting Row
            db.insert(TABLE_WEEKS, null, values);
        }
        db.close(); // Closing database connection
    }
    //Getting one game
    public Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WEEKS, new String[]{KEY_ID,
                        KEY_TEAM1, KEY_TEAM2, KEY_WEEK, KEY_MARGIN}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Game contact = new Game(cursor.getInt(3), cursor.getString(1), cursor.getString(2), cursor.getInt(4));

        // return game
        return contact;
    }

    public List<String> getAllByeWeeksGames(){

        return gameByeWeekList;
    }

    public List<Integer> getAllByeWeeks(){

        return ByeWeekList;
    }

    // Getting All Games
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_WEEKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setId(cursor.getInt(0));
                game.setTeam1(cursor.getString(1));
                game.setTeam2(cursor.getString(2));
                game.setWeek(cursor.getInt(3));
                game.setMargin(cursor.getInt(4));
        // Adding contact to list
                gameList.add(game);
            } while (cursor.moveToNext());
        }

        // return contact list
        return gameList;
    }
}