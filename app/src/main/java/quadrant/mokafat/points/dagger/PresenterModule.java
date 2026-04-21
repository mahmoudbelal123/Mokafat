package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.login.LoginPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsPresenter;
import quadrant.mokafat.points.view.qr_code.QrCodePresenter;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter;
import quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter;

/* JADX INFO: loaded from: classes.dex */
@Module
public class PresenterModule {
    @Provides
    @Singleton
    LoginPresenter provideMainPresenter(Context context) {
        return new LoginPresenter(context);
    }

    @Provides
    @Singleton
    ProfilePresenter provideProfilePresenter(Context context) {
        return new ProfilePresenter(context);
    }

    @Provides
    @Singleton
    EditAccountPresenter provideEditAccountPresenter(Context context) {
        return new EditAccountPresenter(context);
    }

    @Provides
    @Singleton
    ChangePasswordPresenter provideChangePasswordPresenter(Context context) {
        return new ChangePasswordPresenter(context);
    }

    @Provides
    @Singleton
    QrCodePresenter provideQrCodePresenter(Context context) {
        return new QrCodePresenter(context);
    }

    @Provides
    @Singleton
    LogOutPresenter provideLogOutPresenter(Context context) {
        return new LogOutPresenter(context);
    }

    @Provides
    @Singleton
    SectionPresenter provideSectionPresenter(Context context) {
        return new SectionPresenter(context);
    }

    @Provides
    @Singleton
    HotDealsPresenter provideHotDealsSectionPresenter(Context context) {
        return new HotDealsPresenter(context);
    }

    @Provides
    @Singleton
    GetItemsSubSectionsPresenter provideGetItemsSubsectionsPresenter(Context context) {
        return new GetItemsSubSectionsPresenter(context);
    }

    @Provides
    @Singleton
    PartnersPresenter providePartnersPresenter(Context context) {
        return new PartnersPresenter(context);
    }

    @Provides
    @Singleton
    GetNearbyDealsPresenter provideNearbyDealsPresenter(Context context) {
        return new GetNearbyDealsPresenter(context);
    }

    @Provides
    @Singleton
    GetYourVoucherPresenter provideGetYourVouchersPresenter(Context context) {
        return new GetYourVoucherPresenter(context);
    }

    @Provides
    @Singleton
    PromosPresenter providePromosPresenter(Context context) {
        return new PromosPresenter(context);
    }

    @Provides
    @Singleton
    PointsPresenter providePointsPresenter(Context context) {
        return new PointsPresenter(context);
    }

    @Provides
    @Singleton
    GetVoucherTabsPresenter provideVoucherTabsPresenter(Context context) {
        return new GetVoucherTabsPresenter(context);
    }

    @Provides
    @Singleton
    AllPartnersPresenter provideAllPartnersPresenter(Context context) {
        return new AllPartnersPresenter(context);
    }

    @Provides
    @Singleton
    SlidesPresenter provideSlidesPresenter(Context context) {
        return new SlidesPresenter(context);
    }

    @Provides
    @Singleton
    GetNearbyPartnersPresenter provideGetNearbyPartnersPresenter(Context context) {
        return new GetNearbyPartnersPresenter(context);
    }

    @Provides
    @Singleton
    ForgetPasswordPresenter provideForgetPasswordPresenter(Context context) {
        return new ForgetPasswordPresenter(context);
    }

    @Provides
    @Singleton
    HomeSectionPresenter provideHomeSectionsPresenter(Context context) {
        return new HomeSectionPresenter(context);
    }

    @Provides
    @Singleton
    ItemFavoritePresenter provideItemFavoritePresenter(Context context) {
        return new ItemFavoritePresenter(context);
    }

    @Provides
    @Singleton
    GetOneItemPresenter provideGetOneItemPresenter(Context context) {
        return new GetOneItemPresenter(context);
    }

    @Provides
    @Singleton
    GetItemsPresenter provideGetItemsPresenter(Context context) {
        return new GetItemsPresenter(context);
    }

    @Provides
    @Singleton
    FavoritesPresenter provideFavoritesPresenter(Context context) {
        return new FavoritesPresenter(context);
    }

    @Provides
    @Singleton
    VendorBranchesPresenter provideVendorBranchesPresenter(Context context) {
        return new VendorBranchesPresenter(context);
    }

    @Provides
    @Singleton
    SectionsHomeItemsPresenter provideSectionsHomeItemsPresenter(Context context) {
        return new SectionsHomeItemsPresenter(context);
    }
}
