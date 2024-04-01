package il.co.bringthemhome.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import il.co.bringthemhome.data.models.Kidnapped
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.data.repository.RowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RowViewModel(private val rowRepository: RowRepository) : ViewModel() {

    var allKidnapped = rowRepository.getAllRows()
    var releasedKidnapped = rowRepository.getReleasedRows()
    var activitiesKidnapped = rowRepository.getActivitiesRows()

    fun getAllRows() {
        viewModelScope.launch(Dispatchers.IO) {
            allKidnapped = rowRepository.getAllRows()
        }
    }

    fun getReleasedRows() {
        viewModelScope.launch(Dispatchers.IO) {
            releasedKidnapped = rowRepository.getReleasedRows()
        }
    }

    fun getActivitiesRows() {
        viewModelScope.launch(Dispatchers.IO) {
            allKidnapped = rowRepository.getActivitiesRows()
        }
    }

    fun getFilteredRows(
        name: String?, minAge: String?, maxAge: String?, gender: String?, city: String?, status: String?
    ): List<Row> {
        return rowRepository.getFilteredRows(
            name, minAge, maxAge, gender, city, status
        )
    }


    val rows: LiveData<Kidnapped>
        get() = rowRepository.rows


    fun clearRows() {
        viewModelScope.launch(Dispatchers.IO) {
            rowRepository.clearRows()
        }
    }

    fun getAllCountRows(): Int {
        return rowRepository.getAllCountRows()
    }

    fun getReleasedCountRows(): Int {
        return rowRepository.getReleasedCountRows()
    }

    fun getActivitiesCountRows(): Int {
        return rowRepository.getActivitiesCountRows()
    }

//    fun getCountFilteredRows(): Int {
//        return rowRepository.getCountFilteredRows(name, minAge, maxAge, gender, city)
//    }

}