package czh.itemlayout

import android.app.Application

class MyAPP : Application() {

    override fun onCreate() {
        super.onCreate()
        DensityUtils.setDensity(this)
        instance=this
    }

    companion object {
        lateinit var instance: MyAPP
    }
}
