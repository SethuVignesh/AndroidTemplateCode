package com.sethu.androidtemplatecode.rxandroid.operators.transforming;
import android.widget.TextView;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

//map operator:- transform the items emitted by an Observable by applying a function to each item
public class MapOperator {
    public static void mapOperator(final TextView textView) {
        Observable<Object> observable = FromCallableOperator.fromCallableOperator();
        observable.subscribeOn(Schedulers.newThread());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return o.toString().toUpperCase();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
//                d.dispose(); don't do because as soon as completed text view is removed
            }

            @Override
            public void onNext(Object o) {
                textView.append("  " + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                textView.append(e.toString());
            }

            @Override
            public void onComplete() {
                textView.append("   Map Operator Completed after converting each strings to uppercase strings.\n");
            }
        });
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//  MapOperator.mapOperator(textView) ;