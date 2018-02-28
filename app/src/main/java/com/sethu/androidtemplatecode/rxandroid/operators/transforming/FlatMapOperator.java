package com.sethu.androidtemplatecode.rxandroid.operators.transforming;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

//flat map operator:- transform the items emitted by an Observable into Observables,
// then flatten the emissions from those into a single Observable

public class FlatMapOperator {
    public static Observable getFlatMapOperator() {
        return (
                Observable.just(1, 2, 3, 4).flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        return Observable.just(2 * integer);
                    }
                })
        );
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    Observable<Integer> observable =FlatMapOperator.getFlatMapOperator();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Integer>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Integer s) {
//            textView.append("  "+s );
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append(" error in merge operator "+e);
//        }
//
//        @Override
//        public void onComplete() {
//            textView.append("  Flat Map operator complete \n");
//        }
//    });