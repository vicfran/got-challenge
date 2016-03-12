package es.npatarino.android.gotchallenge.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.interactor.GoTInteractor;
import es.npatarino.android.gotchallenge.presentation.adapter.GoTCharactersAdapter;
import es.npatarino.android.gotchallenge.presentation.model.mapper.GoTCharacterModelMapper;

public class GoTCharactersFragment extends Fragment {

    private static final String TAG = "CharactersListFragment";

    public GoTCharactersFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar progressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        final GoTCharactersAdapter adapter = new GoTCharactersAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // TODO : just for testing the architecture, manage better
        List<GoTCharacter> characters = GoTInteractor.getCharacters();

        adapter.addAll(GoTCharacterModelMapper.transform(characters));

        adapter.notifyDataSetChanged();
        progressBar.hide();

        return rootView;
    }
}
