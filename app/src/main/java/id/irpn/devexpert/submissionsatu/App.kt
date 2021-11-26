package id.irpn.devexpert.submissionsatu

import android.app.Application
import id.irpn.devexpert.core.di.databaseModule
import id.irpn.devexpert.core.di.networkModule
import id.irpn.devexpert.core.di.repositoryModule
import id.irpn.devexpert.core.di.useCaseModule
import id.irpn.devexpert.submissionsatu.di.DataModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            ))
        }
    }
}