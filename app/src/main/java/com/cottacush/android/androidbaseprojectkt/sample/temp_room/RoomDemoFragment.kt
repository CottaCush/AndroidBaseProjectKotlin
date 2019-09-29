/*
package com.cottacush.android.androidbaseprojectkt.sample.temp_room


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.*
import com.cottacush.android.androidbaseprojectkt.R
import timber.log.Timber


class RoomDemoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_demo, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(
            context!!.applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val dao = db.userDao()
        val usersToInsert = mutableListOf(
            User(1, "1", "11"),
            User(2, "2", "22"),
            User(3, "3", "33")
        ).toTypedArray()

        Thread {
            dao.nukeTable()
            dao.insertAll(*usersToInsert)
            Timber.d("Inserted stuff ----------")


        }.start()

        dao.getAll().observe(this, Observer { list ->
            Timber.d("Atilo Atide ----------")
            list.forEach {
                Timber.d("Observed Data: $it" )
            }
        })


    }



}


//---------

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun nukeTable()
}

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}*/
