package dimas_ok.shoppinglist.activities

import android.app.Application
import dimas_ok.shoppinglist.db.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this) }
}