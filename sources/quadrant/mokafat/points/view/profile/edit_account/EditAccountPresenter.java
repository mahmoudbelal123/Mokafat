package quadrant.mokafat.points.view.profile.edit_account;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.util.CrashUtils;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountRequest;
import quadrant.mokafat.points.models.objects.edit_account.EditAccountResponse;
import quadrant.mokafat.points.view.profile.update.UpdateEmailActivity;
import quadrant.mokafat.points.view.profile.update.UpdateMobileActivity;
import quadrant.mokafat.points.view.profile.update.UpdateNameActivity;
import quadrant.mokafat.points.view.profile.update.UpdatePasswordActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class EditAccountPresenter implements BasePresenter<EditAccountView> {
    EditAccountRequest editAccountRequest;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    EditAccountView mView;
    boolean isLoaded = false;
    MultipartBody.Part body = null;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(EditAccountView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public EditAccountPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void startUpdateMobile(Context context) {
        this.editAccountRequest = new EditAccountRequest();
        this.editAccountRequest.setEmail(this.mView.getEmail());
        this.editAccountRequest.setName(this.mView.getName());
        this.editAccountRequest.setMain_image(this.mView.getMainImage());
        Intent intent = new Intent(context, (Class<?>) UpdateMobileActivity.class);
        intent.setFlags(CrashUtils.ErrorDialogData.DYNAMITE_CRASH);
        intent.putExtra("email", this.mView.getEmail());
        intent.putExtra("name", this.mView.getName());
        intent.putExtra("mobile", this.mView.getMobile());
        intent.putExtra("allow_not", this.mView.getAllowNotification());
        intent.putExtra("receive_not", this.mView.getReceiveNewOffersNotification());
        context.startActivity(intent);
    }

    public void startUpdatePasswordActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) UpdatePasswordActivity.class);
        intent.setFlags(CrashUtils.ErrorDialogData.DYNAMITE_CRASH);
        context.startActivity(intent);
    }

    public void startUpdateEmailActivity(Context context) {
        this.editAccountRequest = new EditAccountRequest();
        this.editAccountRequest.setMobile(this.mView.getMobile());
        this.editAccountRequest.setMain_image(this.mView.getMainImage());
        this.editAccountRequest.setName(this.mView.getName());
        Intent intent = new Intent(context, (Class<?>) UpdateEmailActivity.class);
        intent.setFlags(CrashUtils.ErrorDialogData.DYNAMITE_CRASH);
        intent.putExtra("mobile", this.mView.getMobile());
        intent.putExtra("name", this.mView.getName());
        intent.putExtra("email", this.mView.getEmail());
        intent.putExtra("allow_not", this.mView.getAllowNotification());
        intent.putExtra("receive_not", this.mView.getReceiveNewOffersNotification());
        context.startActivity(intent);
    }

    public void startUpdateNameActivity(Context context) {
        this.editAccountRequest = new EditAccountRequest();
        this.editAccountRequest.setEmail(this.mView.getEmail());
        this.editAccountRequest.setMobile(this.mView.getMobile());
        this.editAccountRequest.setMain_image(this.mView.getMainImage());
        Intent intent = new Intent(context, (Class<?>) UpdateNameActivity.class);
        intent.setFlags(CrashUtils.ErrorDialogData.DYNAMITE_CRASH);
        intent.putExtra("email", this.mView.getEmail());
        intent.putExtra("mobile", this.mView.getMobile());
        intent.putExtra("name", this.mView.getName());
        intent.putExtra("allow_not", this.mView.getAllowNotification());
        intent.putExtra("receive_not", this.mView.getReceiveNewOffersNotification());
        context.startActivity(intent);
    }

    public void editAccountPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            this.mView.showMessage(this.mContext.getString(R.string.internet_check));
            checkConnection(false);
            return;
        }
        this.mView.showLoading();
        this.mView.showLoadingProfileInfo();
        this.editAccountRequest = new EditAccountRequest();
        this.editAccountRequest.setName(this.mView.getName());
        this.editAccountRequest.setEmail(this.mView.getEmail());
        this.editAccountRequest.setMobile(this.mView.getMobile());
        this.editAccountRequest.setMain_image(this.mView.getMainImage());
        if (this.mView.getMainImage() != null) {
            this.editAccountRequest.setMain_image(this.mView.getMainImage());
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), this.editAccountRequest.getMain_image());
            this.body = MultipartBody.Part.createFormData("main_image", this.editAccountRequest.getName(), requestBody);
            this.mApiInterface.editProfileObservable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.editAccountRequest.getName(), this.editAccountRequest.getEmail(), this.editAccountRequest.getMobile(), this.body, this.mView.getReceiveNewOffersNotification(), this.mView.getAllowNotification()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super EditAccountResponse>) new Subscriber<EditAccountResponse>() { // from class: quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter.1
                @Override // rx.Observer
                public final void onCompleted() {
                    EditAccountPresenter.this.mView.hideLoading();
                    EditAccountPresenter.this.mView.hideLoadingProfileInfo();
                }

                @Override // rx.Observer
                public final void onError(Throwable e) {
                    EditAccountPresenter.this.mView.showErrorMessage(e.getMessage());
                    EditAccountPresenter.this.mView.hideLoading();
                    EditAccountPresenter.this.mView.hideLoadingProfileInfo();
                }

                @Override // rx.Observer
                public final void onNext(EditAccountResponse response) {
                    EditAccountPresenter.this.isLoaded = true;
                    EditAccountPresenter.this.mView.showResponse(response);
                    EditAccountPresenter.this.mView.showSuccessMessage(response.getMessage());
                }
            });
            return;
        }
        this.mApiInterface.editProfile2Observable(Utilities.retrieveUserInfoSharedPreferences(this.mContext).getToken(), this.editAccountRequest.getName(), this.editAccountRequest.getEmail(), this.editAccountRequest.getMobile(), this.mView.getReceiveNewOffersNotification(), this.mView.getAllowNotification()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super EditAccountResponse>) new Subscriber<EditAccountResponse>() { // from class: quadrant.mokafat.points.view.profile.edit_account.EditAccountPresenter.2
            @Override // rx.Observer
            public final void onCompleted() {
                EditAccountPresenter.this.mView.hideLoading();
                EditAccountPresenter.this.mView.hideLoadingProfileInfo();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                EditAccountPresenter.this.mView.showErrorMessage(e.getMessage());
                EditAccountPresenter.this.mView.hideLoading();
                EditAccountPresenter.this.mView.hideLoadingProfileInfo();
            }

            @Override // rx.Observer
            public final void onNext(EditAccountResponse response) {
                EditAccountPresenter.this.isLoaded = true;
                EditAccountPresenter.this.mView.showSuccessMessage(response.getMessage());
            }
        });
    }

    void checkConnection(boolean isConnected) {
        if (isConnected && !this.isLoaded) {
            editAccountPresenter();
            this.isLoaded = false;
            this.mView.showMessage("internet connected");
        }
        if (!isConnected && this.isLoaded) {
            this.mView.showMessage("offline");
        } else if (isConnected && this.isLoaded) {
            this.mView.showMessage("connect to internet");
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
