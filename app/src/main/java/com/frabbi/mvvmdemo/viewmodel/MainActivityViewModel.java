package com.frabbi.mvvmdemo.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frabbi.mvvmdemo.model.NicePlace;
import com.frabbi.mvvmdemo.repositories.NicePlacesRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean>  progressUpdate = new MutableLiveData<>();
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlacesRepository mRepository;
    public void initialize(){
        mRepository = NicePlacesRepository.getInstance();
        mNicePlaces = mRepository.getNicePlaces();
    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }

    public void addNewValue(final NicePlace nicePlace){
        progressUpdate.setValue(true);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                List<NicePlace> currentList = mNicePlaces.getValue();
                currentList.add(nicePlace);
                mNicePlaces.postValue(currentList);
                progressUpdate.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<Boolean> getProgressUpdate() {
        return progressUpdate;
    }
}
