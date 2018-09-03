package com.vepe.rickapp.ui.characters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vepe.rickapp.R;
import com.vepe.rickapp.data.model.BaseModel;
import com.vepe.rickapp.data.model.ErrorModel;
import com.vepe.rickapp.data.model.LoadingModel;
import com.vepe.rickapp.data.model.SuccessModel;
import com.vepe.rickapp.data.source.remote.model.Info;
import com.vepe.rickapp.presentation.CharactersViewModel;

import java.util.Collections;


public class CharactersFragment extends Fragment {

    private CharactersViewModel viewModel;

    private ProgressBar progress;

    private TextView error;

    private RecyclerView recycler;

    private OnFragmentInteractionListener mListener;

    public CharactersFragment() {
    }

    public static CharactersFragment newInstance() {
        return new CharactersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(this).get(CharactersViewModel.class);
            viewModel.getCharacters().observe(this, model -> {
                if (model != null) {
                    render(model);
                }
            });
            viewModel.loadCharacters();
        }
    }

    private void render(BaseModel model) {
        if (model instanceof SuccessModel) {
            Info info = ((SuccessModel) model).getInfo();
            if (info != null) {
                int index = info.getNext().split("=").length;
                int nextPage = Integer.valueOf(info.getNext().split("=")[index - 1]);
                if (nextPage > 2) {
                    ((CharacterAdapter) recycler.getAdapter()).appendItems(((SuccessModel) model).getData());
                } else {
                    recycler.setAdapter(new CharacterAdapter(((SuccessModel) model).getData()));
                }
            }
            progress.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
        } else if (model instanceof ErrorModel) {
            error.setText(((ErrorModel) model).getError().getMessage());
            error.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        } else if (model instanceof LoadingModel) {
            progress.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container, false);
        error = view.findViewById(R.id.error);
        progress = view.findViewById(R.id.progress);
        recycler = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new CharacterAdapter(Collections.emptyList()));

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemsCount = layoutManager.getItemCount();
                int visibleItemsCount = layoutManager.getChildCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (firstVisibleItem != 0 &&
                        progress.getVisibility() != View.VISIBLE &&
                        (visibleItemsCount + firstVisibleItem) >= totalItemsCount) {
                    progress.setVisibility(View.VISIBLE);
                    viewModel.loadMoreCharacters();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
