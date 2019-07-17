
import android.app.Activity
import androidx.fragment.app.Fragment

interface BaseComponent<T> {

    fun inject(target: T)
}

interface BaseFragmentComponent<T : Fragment> : BaseComponent<T>

interface BaseActivityComponent<T : Activity> : BaseComponent<T>

