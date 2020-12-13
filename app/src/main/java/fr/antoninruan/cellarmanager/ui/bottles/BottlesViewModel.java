package fr.antoninruan.cellarmanager.ui.bottles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BottlesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BottlesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}