package com.sethu.androidtemplatecode.rxandroid.operators.creating;
import com.sethu.androidtemplatecode.application.MainApplication;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.model.User;
import com.sethu.androidtemplatecode.retrofitapiinterface.UserApiInterface;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//create an Observable from scratch by calling observer methods programmatically

public class CreateOperator {
    public static Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(final ObservableEmitter e) throws Exception {
        //here we can have any operations like network calls ,database , observables like just etc...
        // and pass result using onNext using observer emitter member
        MainApplicationComponent component = MainApplication.getMainApplicationComponent();
        final UserApiInterface userApiInterface = component.getRetrofitMultiUserConverterInstance().create(UserApiInterface.class);
        Call<List<User>> call = userApiInterface.getUsersInfo();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User user : response.body()) {
                    e.onNext(user.getFirstName()+ " "+ user.getUserId());
                }
                e.onComplete();
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
               e.onError(t);
            }
        });
        }
    });
}
//TODO 1 check for background process in oreo
//todo 2 check for blocking ui when required
//todo 3 check for cursor adapter and rxandroid
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//     CreateOperator.observable.subscribeOn(Schedulers.newThread())
//             .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Object>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Object o) {
//            textView.append("  "+o);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onComplete() {
//
//        }
//    });

