package com.toggl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toggl.architecture.AppAction
import com.toggl.architecture.AppState
import com.toggl.architecture.coordinators.AuthCoordinator
import com.toggl.architecture.core.Store
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var store: Store<AppState, AppAction>

    @Inject
    lateinit var authCoordinator: AuthCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        (applicationContext as TogglApplication).appComponent.inject(this)

        if (savedInstanceState == null) {
            authCoordinator.start(this)
        }
    }
}
