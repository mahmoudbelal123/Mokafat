package quadrant.mokafat.points.dagger;

import dagger.Component;
import javax.inject.Singleton;
import quadrant.mokafat.points.view.activities.tabs.PointsFragment;
import quadrant.mokafat.points.view.activities.tabs.PromosFragment;
import quadrant.mokafat.points.view.activities.tabs.VouchersFragment;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;
import quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity;
import quadrant.mokafat.points.view.change_password.ChangePasswordActivity;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordActivity;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.NearByPartnersFragment;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersFragment;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.login.LoginActivity;
import quadrant.mokafat.points.view.login.LoginPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsFragment;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsPresenter;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment;
import quadrant.mokafat.points.view.profile.update.UpdateEmailActivity;
import quadrant.mokafat.points.view.profile.update.UpdateMobileActivity;
import quadrant.mokafat.points.view.profile.update.UpdateNameActivity;
import quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity;
import quadrant.mokafat.points.view.qr_code.QrCodeActivity;
import quadrant.mokafat.points.view.qr_code.QrCodePresenter;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter;
import quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemsInsideSubsectionsActivity;
import quadrant.mokafat.points.view.settings.SettingsFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1.SingleHotDealsFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherFragment;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter;

/* JADX INFO: loaded from: classes.dex */
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class})
@Singleton
public interface AppComponent {
    void inject(PointsFragment pointsFragment);

    void inject(PromosFragment promosFragment);

    void inject(VouchersFragment vouchersFragment);

    void inject(PointsPresenter pointsPresenter);

    void inject(PromosPresenter promosPresenter);

    void inject(AllowNotificationActivity allowNotificationActivity);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(ChangePasswordPresenter changePasswordPresenter);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(ForgetPasswordPresenter forgetPasswordPresenter);

    void inject(GetItemsPresenter getItemsPresenter);

    void inject(GetNearbyDealsPresenter getNearbyDealsPresenter);

    void inject(NearByDealsFragment nearByDealsFragment);

    void inject(GetNearbyPartnersPresenter getNearbyPartnersPresenter);

    void inject(NearByPartnersFragment nearByPartnersFragment);

    void inject(AllPartnersFragment allPartnersFragment);

    void inject(AllPartnersPresenter allPartnersPresenter);

    void inject(GetVoucherTabsPresenter getVoucherTabsPresenter);

    void inject(LogOutPresenter logOutPresenter);

    void inject(LoginActivity loginActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(EditAccountPresenter editAccountPresenter);

    void inject(FavoritesPresenter favoritesPresenter);

    void inject(ProfileActivity profileActivity);

    void inject(ProfilePresenter profilePresenter);

    void inject(HomeSectionPresenter homeSectionPresenter);

    void inject(SlidesFragment slidesFragment);

    void inject(SlidesPresenter slidesPresenter);

    void inject(SectionsHomeItemsFragment sectionsHomeItemsFragment);

    void inject(SectionsHomeItemsPresenter sectionsHomeItemsPresenter);

    void inject(TabOneFragment tabOneFragment);

    void inject(TabTwoFragment tabTwoFragment);

    void inject(UpdateEmailActivity updateEmailActivity);

    void inject(UpdateMobileActivity updateMobileActivity);

    void inject(UpdateNameActivity updateNameActivity);

    void inject(UpdatePasswordActivity updatePasswordActivity);

    void inject(QrCodeActivity qrCodeActivity);

    void inject(QrCodePresenter qrCodePresenter);

    void inject(SectionPresenter sectionPresenter);

    void inject(SectionsFragment sectionsFragment);

    void inject(GetItemsSubSectionsFragment getItemsSubSectionsFragment);

    void inject(GetItemsSubSectionsPresenter getItemsSubSectionsPresenter);

    void inject(HotDealsFragment hotDealsFragment);

    void inject(HotDealsPresenter hotDealsPresenter);

    void inject(PartnersFragment partnersFragment);

    void inject(PartnersPresenter partnersPresenter);

    void inject(GetOneItemPresenter getOneItemPresenter);

    void inject(ItemFavoritePresenter itemFavoritePresenter);

    void inject(ItemsInsideSubsectionsActivity itemsInsideSubsectionsActivity);

    void inject(SettingsFragment settingsFragment);

    void inject(SingleHotDealsFragment singleHotDealsFragment);

    void inject(SingleAllBranchFragment singleAllBranchFragment);

    void inject(VendorBranchesPresenter vendorBranchesPresenter);

    void inject(GetYourVoucherFragment getYourVoucherFragment);

    void inject(GetYourVoucherPresenter getYourVoucherPresenter);
}
