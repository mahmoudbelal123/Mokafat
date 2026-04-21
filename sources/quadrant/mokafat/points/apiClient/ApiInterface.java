package quadrant.mokafat.points.apiClient;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import okhttp3.MultipartBody;
import quadrant.mokafat.points.models.objects.change_password.ChangePasswordRequest;
import quadrant.mokafat.points.models.objects.change_password.ChangePasswordResponse;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.models.objects.favorite.AllFavoriteResponse;
import quadrant.mokafat.points.models.objects.favorite.FavoriteResponse;
import quadrant.mokafat.points.models.objects.forget_password.ForgetPasswordRequest;
import quadrant.mokafat.points.models.objects.forget_password.ForgetPasswordResponse;
import quadrant.mokafat.points.models.objects.get_branches.DataObject;
import quadrant.mokafat.points.models.objects.get_branches.GetBranchesResponse;
import quadrant.mokafat.points.models.objects.get_branches.get_branches_by_id.GetBranchesByBranchIdResponse;
import quadrant.mokafat.points.models.objects.get_items.GetItemsResponse;
import quadrant.mokafat.points.models.objects.get_items.get_items_by_id.DataForItem;
import quadrant.mokafat.points.models.objects.get_points.GetPointsResponse;
import quadrant.mokafat.points.models.objects.get_sections.GetSectionsResponse;
import quadrant.mokafat.points.models.objects.get_sections.get_items_for_sub_section.GetItems;
import quadrant.mokafat.points.models.objects.get_sections.get_sectons_by_id.GetSectionsBySectionIdResponse;
import quadrant.mokafat.points.models.objects.get_vendors.GetVendorsResponse;
import quadrant.mokafat.points.models.objects.get_vendors.get_vendors_by_id.GetVendorsByVendorIdResponse;
import quadrant.mokafat.points.models.objects.get_vouchers.GetYourVouchersResponse;
import quadrant.mokafat.points.models.objects.get_vouchers_tab.GetVouchersTabResponse;
import quadrant.mokafat.points.models.objects.log_out.LogOutResponse;
import quadrant.mokafat.points.models.objects.login.loginRequest;
import quadrant.mokafat.points.models.objects.login.loginResponse;
import quadrant.mokafat.points.models.objects.profile.ProfileResponse;
import quadrant.mokafat.points.models.objects.redemptions.GetRedemptionsResponse;
import quadrant.mokafat.points.models.objects.slides.GetSlidesResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface ApiInterface {
    @POST(EndPoints.CHANGE_PASSWORD)
    Observable<ChangePasswordResponse> changePasswordObservable(@Header("token") String str, @Body ChangePasswordRequest changePasswordRequest);

    @POST(EndPoints.EDIT_PROFILE)
    Observable<EditAccountResponse> editProfile2Observable(@Header("token") String str, @Query("name") String str2, @Query("email") String str3, @Query("mobile") String str4, @Query("push_notification") int i, @Query("receive_new_offers") int i2);

    @POST(EndPoints.EDIT_PROFILE)
    @Multipart
    Observable<EditAccountResponse> editProfileObservable(@Header("token") String str, @Query("name") String str2, @Query("email") String str3, @Query("mobile") String str4, @Part MultipartBody.Part part, @Query("push_notification") int i, @Query("receive_new_offers") int i2);

    @POST(EndPoints.FORGET_PASSWORD)
    Observable<ForgetPasswordResponse> forgetPasswordObservable(@Body ForgetPasswordRequest forgetPasswordRequest);

    @GET("items/favorites")
    Observable<AllFavoriteResponse> getAllFavoritesObservable(@Header("token") String str, @Query("page") String str2);

    @GET("branches")
    Observable<DataObject> getBranchesByVendorIdObservable(@Header("token") String str, @Query("vendor_id") String str2);

    @GET("branches")
    Observable<GetBranchesByBranchIdResponse> getBranchesCityForVendorObservable(@Header("token") String str, @Query("vendor_id") String str2, @Query("grouped") String str3);

    @GET("branches")
    Observable<GetBranchesResponse> getBranchesObservable(@Header("token") String str);

    @GET(EndPoints.GET_ITEMS_BY_ITEM_ID)
    Observable<DataForItem> getItemsByItemIdObservable(@Header("token") String str, @Path("id") String str2);

    @GET("items")
    Observable<GetItemsResponse> getItemsForHomeSectionsObservable(@Header("token") String str, @Query("parent_section_id") String str2);

    @GET("items")
    Observable<GetItems> getItemsForSubSection(@Header("token") String str, @Query("section_id") String str2);

    @GET("items")
    Observable<GetItems> getItemsForSubSectionPage(@Header("token") String str, @Query("section_id") String str2, @Query("page") String str3);

    @GET("items")
    Observable<GetItems> getItemsForVendorObservable(@Header("token") String str, @Query("vendor_id") String str2);

    @GET("items")
    Observable<GetItemsResponse> getItemsObservable(@Header("token") String str, @Query("vendor_id") List<Integer> list);

    @GET("branches")
    Observable<quadrant.mokafat.points.models.objects.get_branches.test_get_branches.GetBranchesResponse> getNearestBranchesObservable(@Header("token") String str, @Query("lat") String str2, @Query("long") String str3);

    @GET("branches")
    Observable<DataObject> getNearestBranchesObservable2(@Header("token") String str, @Query("latitude") String str2, @Query("longitude") String str3);

    @GET(EndPoints.GET_PARTNERS_FOR_SECTION)
    Observable<GetVendorsResponse> getPartnersForSection(@Header("token") String str, @Query("section_id") String str2);

    @GET(EndPoints.GET_PARTNERS_FOR_SECTION)
    Observable<GetSectionsResponse> getPartnersForSections(@Header("token") String str);

    @GET(EndPoints.GET_POINTS_HISTORY)
    Observable<GetPointsResponse> getPointsObservable(@Header("token") String str);

    @GET(EndPoints.GET_PROFILE)
    Observable<ProfileResponse> getProfileObservable(@Header("token") String str);

    @GET(EndPoints.GET_REDEMPTIONS_HISTORY)
    Observable<GetRedemptionsResponse> getRedemptionsObservable(@Header("token") String str);

    @GET(EndPoints.GET_SECTIONS_BY_SECTION_ID)
    Observable<GetSectionsBySectionIdResponse> getSectionsBySectionIdObservable(@Header("token") String str, @Path("id") String str2);

    @GET(EndPoints.GET_SECTIONS)
    Observable<GetSectionsResponse> getSectionsObservable(@Header("token") String str);

    @GET(EndPoints.GET_SLIDES)
    Observable<GetSlidesResponse> getSlidesObservable(@Header("token") String str);

    @GET(EndPoints.GET_VENDORS_BY_VENDOR_ID)
    Observable<GetVendorsByVendorIdResponse> getVendorsByVendorIdObservable(@Header("token") String str, @Path("id") long j);

    @GET(EndPoints.GET_VENDORS)
    Observable<GetVendorsResponse> getVendorsObservable(@Header("token") String str);

    @GET(EndPoints.GET_VOUCHERS_HISTORY)
    Observable<GetVouchersTabResponse> getVouchersObservable(@Header("token") String str);

    @GET(EndPoints.GET_TIERS)
    Observable<GetYourVouchersResponse> getYourVouchersObservable(@Header("token") String str);

    @POST(EndPoints.LOGIN)
    Observable<loginResponse> loginObservable(@Body loginRequest loginrequest);

    @GET(EndPoints.LOGOUT)
    Observable<LogOutResponse> logoutObservable(@Header("token") String str);

    @POST("items/favorites")
    Observable<FavoriteResponse> makeItemFavoriteObservable(@Header("token") String str, @Query(FirebaseAnalytics.Param.ITEM_ID) String str2);
}
