package com.demo.architecturecomponentstalk

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent

class MeetupViewModel(application: Application) : AndroidViewModel(application) {

    private var _events: LiveData<List<MeetupEvent>>? = null
    val events: LiveData<List<MeetupEvent>>
        get() {
            if (_events == null) {
                _events = DemoApp.DB.meetupEventDao().findAll()
            }
            return _events!!
        }
}