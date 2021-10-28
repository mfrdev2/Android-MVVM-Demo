package com.frabbi.mvvmdemo.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frabbi.mvvmdemo.model.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlacesRepository {
    private static NicePlacesRepository INSTANCE;
    private static List<NicePlace> list = new ArrayList<>();

    public static NicePlacesRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new NicePlacesRepository();
        }
        return INSTANCE;
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        databaseDataReady();
        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(
                list
        );
        return data;
    }
    private static void databaseDataReady(){
        list.add(new NicePlace("#","#"));
    }
}
