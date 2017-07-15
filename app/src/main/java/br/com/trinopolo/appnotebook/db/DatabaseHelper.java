package br.com.trinopolo.appnotebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by marconvcm on 08/07/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AppSqlite.db";
    private static final int DATABASE_VERSION = 2;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 1; i <= DATABASE_VERSION; i++) {
            applicaVersao(db, i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < DATABASE_VERSION; i++) {
            applicaVersao(db, i+1);
        }
    }

    private void applicaVersao(SQLiteDatabase db, int versao) {
        try {
            InputStream is = context.getAssets().open(String.format("migrations/%d.sql", versao));
            String sql = IOUtils.toString(is);

            String[] commands = sql.split(";");

            for (String command : commands) {
                db.execSQL(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
