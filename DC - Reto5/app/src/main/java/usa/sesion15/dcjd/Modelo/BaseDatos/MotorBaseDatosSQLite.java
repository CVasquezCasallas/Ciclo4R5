package usa.sesion15.dcjd.Modelo.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MotorBaseDatosSQLite extends SQLiteOpenHelper {

    public MotorBaseDatosSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //******************************* TABLA FAVORITOS ******************************
        db.execSQL("CREATE TABLE favoritos (img INT, titulo TEXT, descripcion TEXT)");


        //******************************* TABLA PRODUCTOS***************************
        //Creacion de la tabla
        db.execSQL("CREATE TABLE productos (img INT, titulo TEXT, descripcion TEXT)");

        //llenar la tabla con datos
        db.execSQL("INSERT INTO productos VALUES (0,'Chaquetas deportivas','Desde: $40.000')");
        db.execSQL("INSERT INTO productos VALUES (1,'Chaquetas casuales','Desde: $60.000')");
        db.execSQL("INSERT INTO productos VALUES (2,'Chaquetas para invierno','Desde: $80.000')");


        //******************************* TABLA SERVICIOS***************************
        //Creacion de la tabla

        db.execSQL("CREATE TABLE servicios (img INT, titulo TEXT)");

        //llenar la tabla con datos
        db.execSQL("INSERT INTO servicios VALUES (0,'Diseñamos chaquetas para tu empresa.')");
        db.execSQL("INSERT INTO servicios VALUES (1,'Renovamos tu chaqueta vieja.')");
        db.execSQL("INSERT INTO servicios VALUES (2,'Cursos de diseño de chaquetas')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE productos");
        database.execSQL("DROP TABLE servicios");
        database.execSQL("DROP TABLE favoritos");
        onCreate(database);

    }
}