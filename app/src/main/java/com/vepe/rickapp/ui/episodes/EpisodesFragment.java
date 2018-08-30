package com.vepe.rickapp.ui.episodes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.vepe.rickapp.presentation.EpisodesViewModel;

import java.util.Collections;

public class EpisodesFragment extends Fragment {

    private OnEpisodeClickListener listener;

    private ProgressBar progress;

    private TextView error;

    private RecyclerView recycler;

    private EpisodesViewModel viewModel;

    public EpisodesFragment() {
    }

    public static EpisodesFragment newInstance() {
        return new EpisodesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(EpisodesViewModel.class);

            viewModel.getEpisodes().observe(this, model -> {
                if (model != null) {
                    render(model);
                }
            });

            viewModel.loadEpisodes();
        }
    }

    private void render(@NonNull BaseModel model) {
        if (model instanceof SuccessModel) {
            recycler.setAdapter(new EpisodesAdapter(((SuccessModel) model).getData(), listener));
            progress.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
        }
        else if (model instanceof ErrorModel) {
            error.setText(((ErrorModel) model).getError().getMessage());
            error.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        }
        else if (model instanceof LoadingModel) {
            progress.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_episodes, container, false);
        progress = root.findViewById(R.id.progress);
        error = root.findViewById(R.id.error);
        recycler = root.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new EpisodesAdapter(Collections.emptyList(), listener));
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEpisodeClickListener) {
            listener = (OnEpisodeClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEpisodeClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnEpisodeClickListener {

        void onEpisodeClicked(int episodeId);
    }
}
