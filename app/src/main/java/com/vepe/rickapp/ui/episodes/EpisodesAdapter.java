package com.vepe.rickapp.ui.episodes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vepe.rickapp.R;
import com.vepe.rickapp.data.model.EpisodeObject;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolder> {

    private List<EpisodeObject> data;

    @Nullable
    private EpisodesFragment.OnEpisodeClickListener listener;

    EpisodesAdapter(List<EpisodeObject> data, @Nullable EpisodesFragment.OnEpisodeClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_episode, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.episode);
        }

        void bind(EpisodeObject episode, EpisodesFragment.OnEpisodeClickListener listener) {
            name.setText(episode.getEpisodeCode() + " - " + episode.getName());

            itemView.setOnClickListener(view -> listener.onEpisodeClicked(episode.getId()));
        }
    }
}
