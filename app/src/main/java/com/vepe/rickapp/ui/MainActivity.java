package com.vepe.rickapp.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vepe.rickapp.R;
import com.vepe.rickapp.ui.characters.CharactersFragment;
import com.vepe.rickapp.ui.episodedetail.EpisodeDetailFragment;
import com.vepe.rickapp.ui.episodes.EpisodesFragment;
import com.vepe.rickapp.ui.locations.LocationsFragment;

public class MainActivity extends AppCompatActivity implements EpisodesFragment.OnEpisodeClickListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();

        if (savedInstanceState == null)
            setFragment(EpisodesFragment.newInstance(), getString(R.string.episodes));
    }

    private void setupNavigation() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.episodes: {
                    setFragment(EpisodesFragment.newInstance(), getString(R.string.episodes));
                    return true;
                }
                case R.id.characters: {
                    setFragment(CharactersFragment.newInstance(), getString(R.string.characters));
                    return true;
                }
                case R.id.locations : {
                    setFragment(LocationsFragment.newInstance(), getString(R.string.locations));
                    return true;
                }
                default: return false;
            }
        });
    }

    private void setFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        toolbar.setTitle(title);
    }

    @Override
    public void onEpisodeClicked(int episodeId) {
        setFragment(EpisodeDetailFragment.newInstance(episodeId), getString(R.string.detail));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof EpisodeDetailFragment)
            setFragment(EpisodesFragment.newInstance(), getString(R.string.episodes));
        else super.onBackPressed();
    }
}
