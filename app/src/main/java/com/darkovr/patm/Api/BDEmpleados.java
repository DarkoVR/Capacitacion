package com.darkovr.patm.Api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marco on 28/02/18.
 */

public class BDEmpleados extends SQLiteOpenHelper {

    String sql_puesto = "create table puesto(cvepuesto integer primary key,puesto varchar(100));";
    String sql_empleado = "create table empleado(cveemp integer primary key, nomemp varchar(50),apepat varchar(50)," +
            "apemat varchar(50),fechanac date,emailemp varchar(100),cvepuesto int,cveusr int," +
            "CONSTRAINT empleado_pk FOREIGN KEY (cvepuesto) references puesto(cvepuesto));";

    public BDEmpleados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_puesto);
        db.execSQL(sql_empleado);

        db.execSQL("insert into puesto (puesto) values ('Gerente'),('Otro puesto')");
        db.execSQL("insert into empleado(nomemp,apepat,apemat,fechanac,emailemp,cvepuesto,cveusr) values " +
                "('Marco','Vazquez','Ruelas','1996-05-11','marco@mail.com',1,100),"+
                "('Zabdiel','Garcia','Mauricio','1996-02-10','zabdi@mail.com',1,101),"+
                "('Juan','Hernandez','Ojeda','1996-05-05','juan@mail.com',1,102),"+
                "('Laura','Rodriguez','Perez','1996-07-29','lari@mail.com',1,103),"+
                "('Pedro','Pica','Piedra','1696-05-23','piedra@mail.com',1,104),"+
                "('Fulanito','Fulanez','Fulancio','1962-10-01','fulano@mail.com',1,105),"+
                "('Sutano','Sutanez','Sutancio','1796-09-02','sutanito@mail.com',1,106)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
