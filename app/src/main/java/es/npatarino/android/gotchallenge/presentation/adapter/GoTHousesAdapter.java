package es.npatarino.android.gotchallenge.presentation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.presentation.activity.GoTCharactersActivity;
import es.npatarino.android.gotchallenge.presentation.model.GoTCharacterModel;

public class GoTHousesAdapter extends GoTBaseAdapter<GoTCharacterModel.GoTHouseModel> {

    public GoTHousesAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotHouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotHouseViewHolder viewHolder = (GotHouseViewHolder) holder;
        configureViewHolderAtPosition(viewHolder, position);
    }

    @Override
    public void configureViewHolderAtPosition(RecyclerView.ViewHolder viewHolder, final int position) {
        final GotHouseViewHolder houseViewHolder = (GotHouseViewHolder) viewHolder;

        houseViewHolder.render(mData.get(position));

        houseViewHolder.backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = buildIntentForHolderAtPosition(houseViewHolder, position);

                houseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public Intent buildIntentForHolderAtPosition(RecyclerView.ViewHolder viewHolder, int position) {
        final GotHouseViewHolder houseViewHolder = (GotHouseViewHolder) viewHolder;

        Intent intent = new Intent((houseViewHolder).itemView.getContext(), GoTCharactersActivity.class);
        intent.putExtra("id", mData.get(position).getId());
        intent.putExtra("name", mData.get(position).getName());
        intent.putExtra("imageUrl", mData.get(position).getImageUrl());

        return intent;
    }

    class GotHouseViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotViewHolder";

        private ImageView backgroundImageView;

        public GotHouseViewHolder(View itemView) {
            super(itemView);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.img_background);
        }

        public void render(final GoTCharacterModel.GoTHouseModel house) {
            if (house == null)
                return;

            String url = house.getImageUrl();
            if (!TextUtils.isEmpty(url))
                Picasso.with(mActivity)
                        .load(url)
                        .into(backgroundImageView);
        }
    }

}
