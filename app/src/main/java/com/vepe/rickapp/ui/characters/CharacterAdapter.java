package com.vepe.rickapp.ui.characters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vepe.rickapp.R;
import com.vepe.rickapp.data.model.CharacterObject;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<CharacterObject> data;

//    @Nullable
//    private CharacterAdapter.OnEpisodeClickListener listener;

    CharacterAdapter(List<CharacterObject> data/*, @Nullable CharacterAdapter.OnEpisodeClickListener listener*/) {
        this.data = data;
//        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_character, parent, false);
        return new CharacterAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));//, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void appendItems(List<CharacterObject> newData) {
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.character_name);
            image = itemView.findViewById(R.id.character_image);
        }

        void bind(CharacterObject character/*, EpisodesFragment.OnEpisodeClickListener listener*/) {
            name.setText(character.getName());
            Glide.with(itemView.getContext())
                    .load(character.getImage())
                    .apply(new RequestOptions().override(500, 500))
                    .into(image);

//            itemView.setOnClickListener(view -> listener.onEpisodeClicked(character.getId()));
        }
    }
}