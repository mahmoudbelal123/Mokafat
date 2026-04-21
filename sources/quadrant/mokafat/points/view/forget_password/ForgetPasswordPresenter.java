package quadrant.mokafat.points.view.forget_password;

import android.content.Context;
import es.dmoral.toasty.Toasty;
import javax.inject.Inject;
import quadrant.mokafat.points.R;
import quadrant.mokafat.points.apiClient.ApiInterface;
import quadrant.mokafat.points.baseClass.BasePresenter;
import quadrant.mokafat.points.dagger.DaggerApplication;
import quadrant.mokafat.points.helper.Utilities;
import quadrant.mokafat.points.models.objects.forget_password.ForgetPasswordRequest;
import quadrant.mokafat.points.models.objects.forget_password.ForgetPasswordResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes.dex */
public class ForgetPasswordPresenter implements BasePresenter<ForgetPasswordView> {

    @Inject
    ApiInterface mApiInterface;

    @Inject
    Context mContext;
    ForgetPasswordView mView;

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onAttach(ForgetPasswordView view) {
        this.mView = view;
        this.mView.onAttache();
    }

    @Override // quadrant.mokafat.points.baseClass.BasePresenter
    public void onDetach() {
        this.mView = null;
    }

    public ForgetPasswordPresenter(Context context) {
        ((DaggerApplication) context).getAppComponent().inject(this);
    }

    public void forgetPasswordPresenter() {
        if (!Utilities.checkConnection(this.mContext)) {
            Toasty.info(this.mContext, this.mContext.getString(R.string.internet_check), 6000, true).show();
            return;
        }
        this.mView.showLoading();
        ForgetPasswordRequest forgetPasswordRequest = new ForgetPasswordRequest();
        forgetPasswordRequest.setUsername(this.mView.getUserName());
        this.mApiInterface.forgetPasswordObservable(forgetPasswordRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super ForgetPasswordResponse>) new Subscriber<ForgetPasswordResponse>() { // from class: quadrant.mokafat.points.view.forget_password.ForgetPasswordPresenter.1
            @Override // rx.Observer
            public final void onCompleted() {
                ForgetPasswordPresenter.this.mView.hideLoading();
            }

            @Override // rx.Observer
            public final void onError(Throwable e) {
                ForgetPasswordPresenter.this.mView.showErrorMessage(e.getMessage());
            }

            @Override // rx.Observer
            public final void onNext(ForgetPasswordResponse response) {
                ForgetPasswordPresenter.this.mView.showSuccessMessage(response.getMessage());
            }
        });
    }
}
