package com.example.todoapp.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.data.Repository;


public class DeleteFragment extends Fragment {


    private Repository repository;
    public DeleteFragment() {

    }

    public static Fragment newInstance() {

        return new DeleteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        repository = Repository.getRepository(getActivity().getApplication());
        repository.deleteAll();
        return view;
    }
}