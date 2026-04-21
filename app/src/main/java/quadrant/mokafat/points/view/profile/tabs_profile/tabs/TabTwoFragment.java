package quadrant.mokafat.points.view.profile.tabs_profile.tabs;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.models.objects.favorite.AllFavoriteResponse;
import quadrant.mokafat.points.models.objects.favorite.DataObjectDetails;
import quadrant.mokafat.points.models.objects.favorite.FavoriteResponse;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;
import quadrant.mokafat.points.view.profile.favorite.FavoritesView;
import quadrant.mokafat.points.view.profile.favorite.FavoritessAdapter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView;

/* JADX INFO: loaded from: classes.dex */
public class TabTwoFragment extends Fragment implements FavoritesView, ItemFavoriteView {

    @Inject
    FavoritesPresenter favoritesPresenter;
    FavoritessAdapter favoritessAdapter;

    @Inject
    ItemFavoritePresenter itemFavoritePresenter;
    private ProgressBar progressBarLoadFavorites;
    private RecyclerView recyclerViewFavorites;
    private View view;
    int page = 1;
    public DataObjectDetails items = null;
    List<DataObjectDetails> list = new ArrayList();

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_tab_two, container, false);
        this.recyclerViewFavorites = (RecyclerView) this.view.findViewById(R.id.recycler_view_favorites);
        this.progressBarLoadFavorites = (ProgressBar) this.view.findViewById(R.id.progress_bar_favorites);
        this.recyclerViewFavorites = (RecyclerView) this.view.findViewById(R.id.recycler_view_favorites);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.recyclerViewFavorites.setLayoutManager(mLayoutManager);
        this.recyclerViewFavorites.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewFavorites.setLayoutManager(mLayoutManager);
        ((DaggerApplication) getActivity().getApplication()).getAppComponent().inject(this);
        this.favoritesPresenter.onAttach((FavoritesView) this);
        this.itemFavoritePresenter.onAttach((ItemFavoriteView) this);
        this.favoritesPresenter.getFavorites(this.page);
        return this.view;
    }

    @Override // quadrant.mokafat.points.baseClass.BaseView
    public void onAttache() {
    }

    @Override // quadrant.mokafat.points.view.profile.favorite.FavoritesView
    public void showResponse(AllFavoriteResponse allFavoriteResponse) {
        for (int i = 0; i < allFavoriteResponse.getData().size(); i++) {
            this.list.add(allFavoriteResponse.getData().get(i));
        }
        if (allFavoriteResponse.getLinks().getNext() != null) {
            this.page++;
            this.favoritesPresenter.getFavorites(this.page);
        } else {
            this.favoritessAdapter = new FavoritessAdapter(this.list, getActivity(), new FavoritessAdapter.OnItemClickListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment.1
                @Override // quadrant.mokafat.points.view.profile.favorite.FavoritessAdapter.OnItemClickListener
                public void onItemClick(DataObjectDetails item) {
                    TabTwoFragment.this.items = item;
                    TabTwoFragment.this.itemFavoritePresenter.makeItemFavorite();
                }
            });
            this.recyclerViewFavorites.setAdapter(this.favoritessAdapter);
        }
    }

    @Override // quadrant.mokafat.points.view.profile.favorite.FavoritesView
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, 0).show();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public String getItemId() {
        return this.items.getAttributes().getItem_id();
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public void showFavorResponse(FavoriteResponse favoriteResponse) {
        View parentLayout = getActivity().findViewById(android.R.id.content);
        Snackbar.make(parentLayout, favoriteResponse.getMessage(), 0).setAction("Done", new View.OnClickListener() { // from class: quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
        this.page = 1;
        this.list = new ArrayList();
        this.favoritesPresenter.getFavorites(this.page);
    }

    @Override // quadrant.mokafat.points.view.sections.inside_items.ItemFavoriteView
    public void showErrorMessage(String error) {
        Toast.makeText(getActivity(), error, 0).show();
    }
}
