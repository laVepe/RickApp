package com.vepe.rickapp.ui.episodedetail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vepe.rickapp.R;
import com.vepe.rickapp.data.model.BaseModel;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.ErrorModel;
import com.vepe.rickapp.data.model.LoadingModel;
import com.vepe.rickapp.data.model.SuccessModel;
import com.vepe.rickapp.presentation.EpisodesViewModel;

import java.util.List;

public class EpisodeDetailFragment extends Fragment {

    public EpisodeDetailFragment() {
    }

    private TextView text;

    private ProgressBar progress;

    private TextView error;

    public static EpisodeDetailFragment newInstance(int episodeId) {
        EpisodeDetailFragment fragment = new EpisodeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("EPISODE_ID", episodeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private EpisodesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int episodeId;
        if (getArguments() != null) {
            episodeId = getArguments().getInt("EPISODE_ID");

            if (getActivity() != null) {
                viewModel = ViewModelProviders.of(getActivity()).get(EpisodesViewModel.class);
                viewModel.getEpisode().observe(this, model -> {
                    if (model != null) {
                        render(model);
                    }
                });
                viewModel.loadEpisodeDetail(episodeId);
            }
        }
    }

    private void render(BaseModel model) {
        if (model instanceof SuccessModel) {
            progress.setVisibility(View.GONE);
            error.setVisibility(View.GONE);

            if (((SuccessModel) model).getData().isEmpty())
                return;

            EpisodeObject episode = ((List<EpisodeObject>) ((SuccessModel) model).getData()).get(0);
            String builder = episode.getName() +
                    "\n" +
                    episode.getEpisodeCode() +
                    "\n" +
                    "aired at " +
                    episode.getAirDate();

            text.setText(builder);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frg_episode_detail, container, false);
        text = root.findViewById(R.id.detail);
        progress = root.findViewById(R.id.progress);
        error = root.findViewById(R.id.error);
        return root;
    }
}
