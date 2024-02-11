package il.co.bringthemhome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import il.co.bringthemhome.data.repository.RowRepository

class RowViewModelFactory(private val rowRepository: RowRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RowViewModel(rowRepository) as T
    }
}