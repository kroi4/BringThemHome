package il.co.bringthemhome.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import il.co.bringthemhome.data.models.Kidnapped
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.data.repository.RowRepository
import il.co.bringthemhome.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RowViewModel(private val rowRepository: RowRepository): ViewModel() {

    var allKidnapped = rowRepository.getAllRows()
    var releasedKidnapped = rowRepository.getReleasedRows()
    var activitiesKidnapped = rowRepository.getActivitiesRows()

    val filteredRows = MutableLiveData<List<Row>>()


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

    fun getFilteredNameRows(
        userInput: String
    ): List<Row> {
        return rowRepository.getFilteredNameRows(
            userInput
        )
    }


    val rows: LiveData<Kidnapped>
        get() = rowRepository.rows


    fun clearRows() {
        viewModelScope.launch(Dispatchers.IO){
            rowRepository.clearRows()
        }
    }



}