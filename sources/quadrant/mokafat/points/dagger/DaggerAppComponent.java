package quadrant.mokafat.points.dagger;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.view.activities.tabs.PointsFragment;
import quadrant.mokafat.points.view.activities.tabs.PointsFragment_MembersInjector;
import quadrant.mokafat.points.view.activities.tabs.PromosFragment;
import quadrant.mokafat.points.view.activities.tabs.PromosFragment_MembersInjector;
import quadrant.mokafat.points.view.activities.tabs.VouchersFragment;
import quadrant.mokafat.points.view.activities.tabs.VouchersFragment_MembersInjector;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.points_logic.PointsPresenter_MembersInjector;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter;
import quadrant.mokafat.points.view.activities.tabs.tabs_container.promos_logic.PromosPresenter_MembersInjector;
import quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity;
import quadrant.mokafat.points.view.allow_notification.AllowNotificationActivity_MembersInjector;
import quadrant.mokafat.points.view.change_password.ChangePasswordActivity;
import quadrant.mokafat.points.view.change_password.ChangePasswordActivity_MembersInjector;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter;
import quadrant.mokafat.points.view.change_password.ChangePasswordPresenter_MembersInjector;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordActivity;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordActivity_MembersInjector;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter;
import quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter_MembersInjector;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetItemsPresenter_MembersInjector;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.GetNearbyDealsPresenter_MembersInjector;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab1.NearByDealsFragment_MembersInjector;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.GetNearbyPartnersPresenter_MembersInjector;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.NearByPartnersFragment;
import quadrant.mokafat.points.view.get_location_map.near_by_tabs.near_by_tabs_adapter.tab2.NearByPartnersFragment_MembersInjector;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersFragment;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersFragment_MembersInjector;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter;
import quadrant.mokafat.points.view.get_partners.partners_tabs.AllPartnersPresenter_MembersInjector;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter;
import quadrant.mokafat.points.view.get_vouchers_tab_setting.get_all_items.GetVoucherTabsPresenter_MembersInjector;
import quadrant.mokafat.points.view.log_out.LogOutPresenter;
import quadrant.mokafat.points.view.log_out.LogOutPresenter_MembersInjector;
import quadrant.mokafat.points.view.login.LoginActivity;
import quadrant.mokafat.points.view.login.LoginActivity_MembersInjector;
import quadrant.mokafat.points.view.login.LoginPresenter;
import quadrant.mokafat.points.view.login.LoginPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter;
import quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter;
import quadrant.mokafat.points.view.profile.favorite.FavoritesPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity;
import quadrant.mokafat.points.view.profile.get_profile.ProfileActivity_MembersInjector;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter;
import quadrant.mokafat.points.view.profile.get_profile.ProfilePresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter;
import quadrant.mokafat.points.view.profile.get_profile.get_home_sections.HomeSectionPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesFragment_MembersInjector;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter;
import quadrant.mokafat.points.view.profile.get_profile.slides.SlidesPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsFragment;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsFragment_MembersInjector;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsPresenter;
import quadrant.mokafat.points.view.profile.sections_items.SectionsHomeItemsPresenter_MembersInjector;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabOneFragment_MembersInjector;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment;
import quadrant.mokafat.points.view.profile.tabs_profile.tabs.TabTwoFragment_MembersInjector;
import quadrant.mokafat.points.view.profile.update.UpdateEmailActivity;
import quadrant.mokafat.points.view.profile.update.UpdateEmailActivity_MembersInjector;
import quadrant.mokafat.points.view.profile.update.UpdateMobileActivity;
import quadrant.mokafat.points.view.profile.update.UpdateMobileActivity_MembersInjector;
import quadrant.mokafat.points.view.profile.update.UpdateNameActivity;
import quadrant.mokafat.points.view.profile.update.UpdateNameActivity_MembersInjector;
import quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity;
import quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity_MembersInjector;
import quadrant.mokafat.points.view.qr_code.QrCodeActivity;
import quadrant.mokafat.points.view.qr_code.QrCodeActivity_MembersInjector;
import quadrant.mokafat.points.view.qr_code.QrCodePresenter;
import quadrant.mokafat.points.view.qr_code.QrCodePresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionPresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionsFragment;
import quadrant.mokafat.points.view.sections.get_all_sections.SectionsFragment_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsFragment_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_items_for_sub_sections.GetItemsSubSectionsPresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsFragment_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_hot_deals.HotDealsPresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersFragment;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersFragment_MembersInjector;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter;
import quadrant.mokafat.points.view.sections.get_section.section_tabs.get_specefic_section.get_partners.PartnersPresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter;
import quadrant.mokafat.points.view.sections.inside_items.GetOneItemPresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter;
import quadrant.mokafat.points.view.sections.inside_items.ItemFavoritePresenter_MembersInjector;
import quadrant.mokafat.points.view.sections.inside_items.ItemsInsideSubsectionsActivity;
import quadrant.mokafat.points.view.sections.inside_items.ItemsInsideSubsectionsActivity_MembersInjector;
import quadrant.mokafat.points.view.settings.SettingsFragment;
import quadrant.mokafat.points.view.settings.SettingsFragment_MembersInjector;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1.SingleHotDealsFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab1.SingleHotDealsFragment_MembersInjector;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.SingleAllBranchFragment_MembersInjector;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter;
import quadrant.mokafat.points.view.single_partner_details.single_partner_tabs.tab2.VendorBranchesPresenter_MembersInjector;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherFragment;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherFragment_MembersInjector;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter;
import quadrant.mokafat.points.view.vouchers_tiers.GetYourVoucherPresenter_MembersInjector;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class DaggerAppComponent implements AppComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MembersInjector<AllPartnersFragment> allPartnersFragmentMembersInjector;
    private MembersInjector<AllPartnersPresenter> allPartnersPresenterMembersInjector;
    private MembersInjector<AllowNotificationActivity> allowNotificationActivityMembersInjector;
    private MembersInjector<ChangePasswordActivity> changePasswordActivityMembersInjector;
    private MembersInjector<ChangePasswordPresenter> changePasswordPresenterMembersInjector;
    private MembersInjector<EditAccountPresenter> editAccountPresenterMembersInjector;
    private MembersInjector<FavoritesPresenter> favoritesPresenterMembersInjector;
    private MembersInjector<ForgetPasswordActivity> forgetPasswordActivityMembersInjector;
    private MembersInjector<ForgetPasswordPresenter> forgetPasswordPresenterMembersInjector;
    private MembersInjector<GetItemsPresenter> getItemsPresenterMembersInjector;
    private MembersInjector<GetItemsSubSectionsFragment> getItemsSubSectionsFragmentMembersInjector;
    private MembersInjector<GetItemsSubSectionsPresenter> getItemsSubSectionsPresenterMembersInjector;
    private MembersInjector<GetNearbyDealsPresenter> getNearbyDealsPresenterMembersInjector;
    private MembersInjector<GetNearbyPartnersPresenter> getNearbyPartnersPresenterMembersInjector;
    private MembersInjector<GetOneItemPresenter> getOneItemPresenterMembersInjector;
    private MembersInjector<GetVoucherTabsPresenter> getVoucherTabsPresenterMembersInjector;
    private MembersInjector<GetYourVoucherFragment> getYourVoucherFragmentMembersInjector;
    private MembersInjector<GetYourVoucherPresenter> getYourVoucherPresenterMembersInjector;
    private MembersInjector<HomeSectionPresenter> homeSectionPresenterMembersInjector;
    private MembersInjector<HotDealsFragment> hotDealsFragmentMembersInjector;
    private MembersInjector<HotDealsPresenter> hotDealsPresenterMembersInjector;
    private MembersInjector<ItemFavoritePresenter> itemFavoritePresenterMembersInjector;
    private MembersInjector<ItemsInsideSubsectionsActivity> itemsInsideSubsectionsActivityMembersInjector;
    private MembersInjector<LogOutPresenter> logOutPresenterMembersInjector;
    private MembersInjector<LoginActivity> loginActivityMembersInjector;
    private MembersInjector<LoginPresenter> loginPresenterMembersInjector;
    private MembersInjector<NearByDealsFragment> nearByDealsFragmentMembersInjector;
    private MembersInjector<NearByPartnersFragment> nearByPartnersFragmentMembersInjector;
    private MembersInjector<PartnersFragment> partnersFragmentMembersInjector;
    private MembersInjector<PartnersPresenter> partnersPresenterMembersInjector;
    private MembersInjector<PointsFragment> pointsFragmentMembersInjector;
    private MembersInjector<PointsPresenter> pointsPresenterMembersInjector;
    private MembersInjector<ProfileActivity> profileActivityMembersInjector;
    private MembersInjector<ProfilePresenter> profilePresenterMembersInjector;
    private MembersInjector<PromosFragment> promosFragmentMembersInjector;
    private MembersInjector<PromosPresenter> promosPresenterMembersInjector;
    private Provider<AllPartnersPresenter> provideAllPartnersPresenterProvider;
    private Provider<String> provideBaseUrlStringProvider;
    private Provider<ChangePasswordPresenter> provideChangePasswordPresenterProvider;
    private Provider<Context> provideContextProvider;
    private Provider<EditAccountPresenter> provideEditAccountPresenterProvider;
    private Provider<FavoritesPresenter> provideFavoritesPresenterProvider;
    private Provider<ForgetPasswordPresenter> provideForgetPasswordPresenterProvider;
    private Provider<GetItemsPresenter> provideGetItemsPresenterProvider;
    private Provider<GetItemsSubSectionsPresenter> provideGetItemsSubsectionsPresenterProvider;
    private Provider<GetNearbyPartnersPresenter> provideGetNearbyPartnersPresenterProvider;
    private Provider<GetOneItemPresenter> provideGetOneItemPresenterProvider;
    private Provider<GetYourVoucherPresenter> provideGetYourVouchersPresenterProvider;
    private Provider<Converter.Factory> provideGsonConverterProvider;
    private Provider<HomeSectionPresenter> provideHomeSectionsPresenterProvider;
    private Provider<HotDealsPresenter> provideHotDealsSectionPresenterProvider;
    private Provider<ItemFavoritePresenter> provideItemFavoritePresenterProvider;
    private Provider<LogOutPresenter> provideLogOutPresenterProvider;
    private Provider<LoginPresenter> provideMainPresenterProvider;
    private Provider<GetNearbyDealsPresenter> provideNearbyDealsPresenterProvider;
    private Provider<PartnersPresenter> providePartnersPresenterProvider;
    private Provider<PointsPresenter> providePointsPresenterProvider;
    private Provider<ProfilePresenter> provideProfilePresenterProvider;
    private Provider<PromosPresenter> providePromosPresenterProvider;
    private Provider<QrCodePresenter> provideQrCodePresenterProvider;
    private Provider<Retrofit> provideRetrofitProvider;
    private Provider<SectionPresenter> provideSectionPresenterProvider;
    private Provider<SectionsHomeItemsPresenter> provideSectionsHomeItemsPresenterProvider;
    private Provider<SlidesPresenter> provideSlidesPresenterProvider;
    private Provider<ApiInterface> provideUsdaApiProvider;
    private Provider<VendorBranchesPresenter> provideVendorBranchesPresenterProvider;
    private Provider<GetVoucherTabsPresenter> provideVoucherTabsPresenterProvider;
    private MembersInjector<QrCodeActivity> qrCodeActivityMembersInjector;
    private MembersInjector<QrCodePresenter> qrCodePresenterMembersInjector;
    private MembersInjector<SectionPresenter> sectionPresenterMembersInjector;
    private MembersInjector<SectionsFragment> sectionsFragmentMembersInjector;
    private MembersInjector<SectionsHomeItemsFragment> sectionsHomeItemsFragmentMembersInjector;
    private MembersInjector<SectionsHomeItemsPresenter> sectionsHomeItemsPresenterMembersInjector;
    private MembersInjector<SettingsFragment> settingsFragmentMembersInjector;
    private MembersInjector<SingleAllBranchFragment> singleAllBranchFragmentMembersInjector;
    private MembersInjector<SingleHotDealsFragment> singleHotDealsFragmentMembersInjector;
    private MembersInjector<SlidesFragment> slidesFragmentMembersInjector;
    private MembersInjector<SlidesPresenter> slidesPresenterMembersInjector;
    private MembersInjector<TabOneFragment> tabOneFragmentMembersInjector;
    private MembersInjector<TabTwoFragment> tabTwoFragmentMembersInjector;
    private MembersInjector<UpdateEmailActivity> updateEmailActivityMembersInjector;
    private MembersInjector<UpdateMobileActivity> updateMobileActivityMembersInjector;
    private MembersInjector<UpdateNameActivity> updateNameActivityMembersInjector;
    private MembersInjector<UpdatePasswordActivity> updatePasswordActivityMembersInjector;
    private MembersInjector<VendorBranchesPresenter> vendorBranchesPresenterMembersInjector;
    private MembersInjector<VouchersFragment> vouchersFragmentMembersInjector;

    private DaggerAppComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideContextProvider = DoubleCheck.provider(AppModule_ProvideContextFactory.create(builder.appModule));
        this.provideMainPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideMainPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.loginActivityMembersInjector = LoginActivity_MembersInjector.create(this.provideMainPresenterProvider);
        this.provideBaseUrlStringProvider = NetworkModule_ProvideBaseUrlStringFactory.create(builder.networkModule);
        this.provideGsonConverterProvider = DoubleCheck.provider(NetworkModule_ProvideGsonConverterFactory.create(builder.networkModule));
        this.provideRetrofitProvider = DoubleCheck.provider(NetworkModule_ProvideRetrofitFactory.create(builder.networkModule, this.provideBaseUrlStringProvider, this.provideGsonConverterProvider));
        this.provideUsdaApiProvider = DoubleCheck.provider(NetworkModule_ProvideUsdaApiFactory.create(builder.networkModule, this.provideRetrofitProvider));
        this.loginPresenterMembersInjector = LoginPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideProfilePresenterProvider = DoubleCheck.provider(PresenterModule_ProvideProfilePresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.profileActivityMembersInjector = ProfileActivity_MembersInjector.create(this.provideProfilePresenterProvider);
        this.profilePresenterMembersInjector = ProfilePresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideChangePasswordPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideChangePasswordPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.changePasswordActivityMembersInjector = ChangePasswordActivity_MembersInjector.create(this.provideChangePasswordPresenterProvider);
        this.changePasswordPresenterMembersInjector = ChangePasswordPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideForgetPasswordPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideForgetPasswordPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.forgetPasswordActivityMembersInjector = ForgetPasswordActivity_MembersInjector.create(this.provideForgetPasswordPresenterProvider);
        this.forgetPasswordPresenterMembersInjector = ForgetPasswordPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideQrCodePresenterProvider = DoubleCheck.provider(PresenterModule_ProvideQrCodePresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.qrCodeActivityMembersInjector = QrCodeActivity_MembersInjector.create(this.provideQrCodePresenterProvider);
        this.qrCodePresenterMembersInjector = QrCodePresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideEditAccountPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideEditAccountPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.provideLogOutPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideLogOutPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.tabOneFragmentMembersInjector = TabOneFragment_MembersInjector.create(this.provideEditAccountPresenterProvider, this.provideLogOutPresenterProvider, this.provideProfilePresenterProvider);
        this.editAccountPresenterMembersInjector = EditAccountPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.logOutPresenterMembersInjector = LogOutPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideSectionPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideSectionPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.sectionsFragmentMembersInjector = SectionsFragment_MembersInjector.create(this.provideSectionPresenterProvider);
        this.sectionPresenterMembersInjector = SectionPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideHotDealsSectionPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideHotDealsSectionPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.hotDealsFragmentMembersInjector = HotDealsFragment_MembersInjector.create(this.provideHotDealsSectionPresenterProvider);
        this.hotDealsPresenterMembersInjector = HotDealsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideGetItemsSubsectionsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideGetItemsSubsectionsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.getItemsSubSectionsFragmentMembersInjector = GetItemsSubSectionsFragment_MembersInjector.create(this.provideGetItemsSubsectionsPresenterProvider);
        this.getItemsSubSectionsPresenterMembersInjector = GetItemsSubSectionsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.providePartnersPresenterProvider = DoubleCheck.provider(PresenterModule_ProvidePartnersPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.partnersFragmentMembersInjector = PartnersFragment_MembersInjector.create(this.providePartnersPresenterProvider);
        this.partnersPresenterMembersInjector = PartnersPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideNearbyDealsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideNearbyDealsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.provideGetItemsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideGetItemsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.nearByDealsFragmentMembersInjector = NearByDealsFragment_MembersInjector.create(this.provideNearbyDealsPresenterProvider, this.provideGetItemsPresenterProvider);
        this.getNearbyDealsPresenterMembersInjector = GetNearbyDealsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideGetYourVouchersPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideGetYourVouchersPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.getYourVoucherFragmentMembersInjector = GetYourVoucherFragment_MembersInjector.create(this.provideGetYourVouchersPresenterProvider);
        this.getYourVoucherPresenterMembersInjector = GetYourVoucherPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.providePromosPresenterProvider = DoubleCheck.provider(PresenterModule_ProvidePromosPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.promosFragmentMembersInjector = PromosFragment_MembersInjector.create(this.providePromosPresenterProvider);
        this.promosPresenterMembersInjector = PromosPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.providePointsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvidePointsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.pointsFragmentMembersInjector = PointsFragment_MembersInjector.create(this.providePointsPresenterProvider);
        this.pointsPresenterMembersInjector = PointsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideVoucherTabsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideVoucherTabsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.vouchersFragmentMembersInjector = VouchersFragment_MembersInjector.create(this.provideVoucherTabsPresenterProvider);
        this.getVoucherTabsPresenterMembersInjector = GetVoucherTabsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideAllPartnersPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideAllPartnersPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.allPartnersFragmentMembersInjector = AllPartnersFragment_MembersInjector.create(this.provideAllPartnersPresenterProvider);
        this.allPartnersPresenterMembersInjector = AllPartnersPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideHomeSectionsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideHomeSectionsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.provideSlidesPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideSlidesPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.slidesFragmentMembersInjector = SlidesFragment_MembersInjector.create(this.provideHomeSectionsPresenterProvider, this.provideSlidesPresenterProvider);
        this.slidesPresenterMembersInjector = SlidesPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.homeSectionPresenterMembersInjector = HomeSectionPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.updateNameActivityMembersInjector = UpdateNameActivity_MembersInjector.create(this.provideEditAccountPresenterProvider);
        this.updateMobileActivityMembersInjector = UpdateMobileActivity_MembersInjector.create(this.provideEditAccountPresenterProvider);
        this.updateEmailActivityMembersInjector = UpdateEmailActivity_MembersInjector.create(this.provideEditAccountPresenterProvider);
        this.provideGetNearbyPartnersPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideGetNearbyPartnersPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.nearByPartnersFragmentMembersInjector = NearByPartnersFragment_MembersInjector.create(this.provideGetNearbyPartnersPresenterProvider);
        this.getNearbyPartnersPresenterMembersInjector = GetNearbyPartnersPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideItemFavoritePresenterProvider = DoubleCheck.provider(PresenterModule_ProvideItemFavoritePresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.provideGetOneItemPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideGetOneItemPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.itemsInsideSubsectionsActivityMembersInjector = ItemsInsideSubsectionsActivity_MembersInjector.create(this.provideItemFavoritePresenterProvider, this.provideGetOneItemPresenterProvider);
        this.itemFavoritePresenterMembersInjector = ItemFavoritePresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.getOneItemPresenterMembersInjector = GetOneItemPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.updatePasswordActivityMembersInjector = UpdatePasswordActivity_MembersInjector.create(this.provideChangePasswordPresenterProvider);
        this.singleHotDealsFragmentMembersInjector = SingleHotDealsFragment_MembersInjector.create(this.provideGetItemsSubsectionsPresenterProvider);
        this.settingsFragmentMembersInjector = SettingsFragment_MembersInjector.create(this.provideLogOutPresenterProvider, this.provideProfilePresenterProvider);
        this.getItemsPresenterMembersInjector = GetItemsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideFavoritesPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideFavoritesPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.tabTwoFragmentMembersInjector = TabTwoFragment_MembersInjector.create(this.provideFavoritesPresenterProvider, this.provideItemFavoritePresenterProvider);
        this.favoritesPresenterMembersInjector = FavoritesPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideVendorBranchesPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideVendorBranchesPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.singleAllBranchFragmentMembersInjector = SingleAllBranchFragment_MembersInjector.create(this.provideVendorBranchesPresenterProvider);
        this.vendorBranchesPresenterMembersInjector = VendorBranchesPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.provideSectionsHomeItemsPresenterProvider = DoubleCheck.provider(PresenterModule_ProvideSectionsHomeItemsPresenterFactory.create(builder.presenterModule, this.provideContextProvider));
        this.sectionsHomeItemsFragmentMembersInjector = SectionsHomeItemsFragment_MembersInjector.create(this.provideSectionsHomeItemsPresenterProvider);
        this.sectionsHomeItemsPresenterMembersInjector = SectionsHomeItemsPresenter_MembersInjector.create(this.provideUsdaApiProvider, this.provideContextProvider);
        this.allowNotificationActivityMembersInjector = AllowNotificationActivity_MembersInjector.create(this.provideEditAccountPresenterProvider);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(LoginActivity loginActivity) {
        this.loginActivityMembersInjector.injectMembers(loginActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(LoginPresenter loginPresenter) {
        this.loginPresenterMembersInjector.injectMembers(loginPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ProfileActivity profileActivity) {
        this.profileActivityMembersInjector.injectMembers(profileActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ProfilePresenter profilePresenter) {
        this.profilePresenterMembersInjector.injectMembers(profilePresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ChangePasswordActivity changePasswordActivity) {
        this.changePasswordActivityMembersInjector.injectMembers(changePasswordActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ChangePasswordPresenter changePasswordPresenter) {
        this.changePasswordPresenterMembersInjector.injectMembers(changePasswordPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ForgetPasswordActivity forgetPasswordActivity) {
        this.forgetPasswordActivityMembersInjector.injectMembers(forgetPasswordActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ForgetPasswordPresenter forgetPasswordPresenter) {
        this.forgetPasswordPresenterMembersInjector.injectMembers(forgetPasswordPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(QrCodeActivity qrCodeActivity) {
        this.qrCodeActivityMembersInjector.injectMembers(qrCodeActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(QrCodePresenter qrCodePresenter) {
        this.qrCodePresenterMembersInjector.injectMembers(qrCodePresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(TabOneFragment tabOneFragment) {
        this.tabOneFragmentMembersInjector.injectMembers(tabOneFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(EditAccountPresenter editAccountPresenter) {
        this.editAccountPresenterMembersInjector.injectMembers(editAccountPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(LogOutPresenter logOutPresenter) {
        this.logOutPresenterMembersInjector.injectMembers(logOutPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SectionsFragment sectionsFragment) {
        this.sectionsFragmentMembersInjector.injectMembers(sectionsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SectionPresenter sectionPresenter) {
        this.sectionPresenterMembersInjector.injectMembers(sectionPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(HotDealsFragment hotDealsFragment) {
        this.hotDealsFragmentMembersInjector.injectMembers(hotDealsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(HotDealsPresenter hotDealsPresenter) {
        this.hotDealsPresenterMembersInjector.injectMembers(hotDealsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetItemsSubSectionsFragment getItemsSubSectionsFragment) {
        this.getItemsSubSectionsFragmentMembersInjector.injectMembers(getItemsSubSectionsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetItemsSubSectionsPresenter getItemsSubSectionsPresenter) {
        this.getItemsSubSectionsPresenterMembersInjector.injectMembers(getItemsSubSectionsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PartnersFragment partnersFragment) {
        this.partnersFragmentMembersInjector.injectMembers(partnersFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PartnersPresenter partnersPresenter) {
        this.partnersPresenterMembersInjector.injectMembers(partnersPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(NearByDealsFragment nearByDealsFragment) {
        this.nearByDealsFragmentMembersInjector.injectMembers(nearByDealsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetNearbyDealsPresenter getNearbyDealsPresenter) {
        this.getNearbyDealsPresenterMembersInjector.injectMembers(getNearbyDealsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetYourVoucherFragment getYourVoucherFragment) {
        this.getYourVoucherFragmentMembersInjector.injectMembers(getYourVoucherFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetYourVoucherPresenter getYourVoucherPresenter) {
        this.getYourVoucherPresenterMembersInjector.injectMembers(getYourVoucherPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PromosFragment PromosFragment) {
        this.promosFragmentMembersInjector.injectMembers(PromosFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PromosPresenter promosPresenter) {
        this.promosPresenterMembersInjector.injectMembers(promosPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PointsFragment pointsFragment) {
        this.pointsFragmentMembersInjector.injectMembers(pointsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(PointsPresenter pointsPresenter) {
        this.pointsPresenterMembersInjector.injectMembers(pointsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(VouchersFragment vouchersFragment) {
        this.vouchersFragmentMembersInjector.injectMembers(vouchersFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetVoucherTabsPresenter getVoucherTabsPresenter) {
        this.getVoucherTabsPresenterMembersInjector.injectMembers(getVoucherTabsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(AllPartnersFragment allPartnersFragment) {
        this.allPartnersFragmentMembersInjector.injectMembers(allPartnersFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(AllPartnersPresenter allPartnersPresenter) {
        this.allPartnersPresenterMembersInjector.injectMembers(allPartnersPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SlidesFragment slidesFragment) {
        this.slidesFragmentMembersInjector.injectMembers(slidesFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SlidesPresenter slidesPresenter) {
        this.slidesPresenterMembersInjector.injectMembers(slidesPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(HomeSectionPresenter homeSectionPresenter) {
        this.homeSectionPresenterMembersInjector.injectMembers(homeSectionPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(UpdateNameActivity updateNameActivity) {
        this.updateNameActivityMembersInjector.injectMembers(updateNameActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(UpdateMobileActivity updateMobileActivity) {
        this.updateMobileActivityMembersInjector.injectMembers(updateMobileActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(UpdateEmailActivity updateEmailActivity) {
        this.updateEmailActivityMembersInjector.injectMembers(updateEmailActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(NearByPartnersFragment nearByPartnersFragment) {
        this.nearByPartnersFragmentMembersInjector.injectMembers(nearByPartnersFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetNearbyPartnersPresenter getNearbyPartnersPresenter) {
        this.getNearbyPartnersPresenterMembersInjector.injectMembers(getNearbyPartnersPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ItemsInsideSubsectionsActivity itemsInsideSubsectionsActivity) {
        this.itemsInsideSubsectionsActivityMembersInjector.injectMembers(itemsInsideSubsectionsActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(ItemFavoritePresenter itemFavoritePresenter) {
        this.itemFavoritePresenterMembersInjector.injectMembers(itemFavoritePresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetOneItemPresenter getOneItemPresenter) {
        this.getOneItemPresenterMembersInjector.injectMembers(getOneItemPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(UpdatePasswordActivity updatePasswordActivity) {
        this.updatePasswordActivityMembersInjector.injectMembers(updatePasswordActivity);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SingleHotDealsFragment singleHotDealsFragment) {
        this.singleHotDealsFragmentMembersInjector.injectMembers(singleHotDealsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SettingsFragment settingsFragment) {
        this.settingsFragmentMembersInjector.injectMembers(settingsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(GetItemsPresenter getItemsPresenter) {
        this.getItemsPresenterMembersInjector.injectMembers(getItemsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(TabTwoFragment tabTwoFragment) {
        this.tabTwoFragmentMembersInjector.injectMembers(tabTwoFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(FavoritesPresenter favoritesPresenter) {
        this.favoritesPresenterMembersInjector.injectMembers(favoritesPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SingleAllBranchFragment singleAllBranchFragment) {
        this.singleAllBranchFragmentMembersInjector.injectMembers(singleAllBranchFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(VendorBranchesPresenter vendorBranchesPresenter) {
        this.vendorBranchesPresenterMembersInjector.injectMembers(vendorBranchesPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SectionsHomeItemsFragment sectionsHomeItemsFragment) {
        this.sectionsHomeItemsFragmentMembersInjector.injectMembers(sectionsHomeItemsFragment);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(SectionsHomeItemsPresenter sectionsHomeItemsPresenter) {
        this.sectionsHomeItemsPresenterMembersInjector.injectMembers(sectionsHomeItemsPresenter);
    }

    @Override // quadrant.mokafat.points.dagger.AppComponent
    public void inject(AllowNotificationActivity allowNotificationActivity) {
        this.allowNotificationActivityMembersInjector.injectMembers(allowNotificationActivity);
    }

    public static final class Builder {
        private AppModule appModule;
        private NetworkModule networkModule;
        private PresenterModule presenterModule;

        private Builder() {
        }

        public AppComponent build() {
            if (this.appModule == null) {
                throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
            }
            if (this.presenterModule == null) {
                this.presenterModule = new PresenterModule();
            }
            if (this.networkModule == null) {
                this.networkModule = new NetworkModule();
            }
            return new DaggerAppComponent(this);
        }

        public Builder appModule(AppModule appModule) {
            this.appModule = (AppModule) Preconditions.checkNotNull(appModule);
            return this;
        }

        public Builder networkModule(NetworkModule networkModule) {
            this.networkModule = (NetworkModule) Preconditions.checkNotNull(networkModule);
            return this;
        }

        public Builder presenterModule(PresenterModule presenterModule) {
            this.presenterModule = (PresenterModule) Preconditions.checkNotNull(presenterModule);
            return this;
        }
    }
}
