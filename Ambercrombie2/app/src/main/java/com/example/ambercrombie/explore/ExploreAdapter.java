package com.example.ambercrombie.explore;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ambercrombie.R;
import com.example.ambercrombie.data.Content;
import com.example.ambercrombie.data.Explorative;
import com.example.ambercrombie.webview.WebActivity;

import java.util.List;

/**
 * Recyclerview Adapter for showing explore cards.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreHolder> {
    final private List<Explorative> exploratives;
    final private Context context;

    public ExploreAdapter(List<Explorative> exploratives, Context context){
        this.exploratives = exploratives;
        this.context = context;
    }

    @NonNull
    @Override
    public ExploreHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.holder_explore, viewGroup, false);
        return new ExploreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreHolder exploreHolder, int i) {
        Explorative explorative = exploratives.get(i);
        Glide.with(context).load(explorative.getBackgroundImage()).into(exploreHolder.image);

        if(explorative.getTopDescription() != null && !explorative.getTopDescription().equals(""))
            exploreHolder.topDescription.setText(explorative.getTopDescription());
        else
            exploreHolder.topDescription.setVisibility(View.GONE);

        if(explorative.getTitle() != null && !explorative.getTitle().equals(""))
            exploreHolder.title.setText(explorative.getTitle());
        else
            exploreHolder.title.setVisibility(View.GONE);

        if(explorative.getPromoMessage() != null && !explorative.getPromoMessage().equals(""))
            exploreHolder.promo.setText(explorative.getPromoMessage());
        else
            exploreHolder.promo.setVisibility(View.GONE);

        if(explorative.getBottomDescription() != null && !explorative.getBottomDescription().equals("")){
            exploreHolder.bottomDescription.setText(Html.fromHtml(explorative.getBottomDescription(),Html.FROM_HTML_MODE_COMPACT));
            final URLSpan[] url = exploreHolder.bottomDescription.getUrls();
            if(url != null && url.length > 0){
                exploreHolder.bottomDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,WebActivity.class);
                        intent.putExtra(WebActivity.WEB_INTENT, url[0] != null ? url[0].getURL() : "");
                        context.startActivity(intent);
                    }
                });
            }
        }
        else
            exploreHolder.bottomDescription.setVisibility(View.GONE);


        if(explorative.getContent() != null){
            for (final Content content: explorative.getContent()) {
                Button button = new Button(context);
                button.setText(content.getTitle());
                button.setTextSize(15);
                button.setTag(content.getTitle());
                if(exploreHolder.content.findViewWithTag(button.getTag()) == null){
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, WebActivity.class);
                            intent.putExtra(WebActivity.WEB_INTENT, content.getTarget());
                            context.startActivity(intent);
                        }
                    });
                    exploreHolder.content.addView(button);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return exploratives.size();
    }

    class ExploreHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView topDescription;
        private TextView title;
        private TextView promo;
        private TextView bottomDescription;
        private LinearLayout content;

        public ExploreHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.picture);
            topDescription = itemView.findViewById(R.id.topDescription);
            title = itemView.findViewById(R.id.title);
            promo = itemView.findViewById(R.id.promo);
            bottomDescription = itemView.findViewById(R.id.bottomDescription);
            content = itemView.findViewById(R.id.moreContent);
        }
    }
}
